package com.rn.batch.api.scrap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

/**
 *
 */
public class ApiScraper {
    public static final String TIME_OUT_MINUTES = "20";
    public static final String ENGINE_CALL_JAR_PATH = "/var/apps/infotech/ift-engineCall-linux.jar";
    public static final String SO_FILE_PATH = "/var/apps/infotech/libiftCoreEngine.so";
    private static Logger LOGGER = LoggerFactory.getLogger(ApiScraper.class);
    public static String PARAM_ERR_YN = "errYn";
    public static String PARAM_ERR_MSG = "errMsg";
    public static String S_YES = "Y";
    public static String S_NO = "N";
    private static String ERR_MSG_DEFAULT = "[SCRAPER-900] 처리중 오류가 발생하였습니다.";

    public ApiScraper() {
    }

    public Map<String, Object> startScrap(Map<String, Object> paramMap) {
        if (paramMap != null && !paramMap.isEmpty()) {
            Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
            String inJson = gson.toJson(paramMap);
            return this.startScrap(inJson);
        } else {
            return new LinkedHashMap<String, Object>() {
                private static final long serialVersionUID = 1L;

                {
                    this.put(ApiScraper.PARAM_ERR_MSG, ApiScraper.ERR_MSG_DEFAULT);
                }
            };
        }
    }

    public Map<String, Object> startScrap(String param) {
        String inJson = param;
        String sResult = "";
        LinkedTreeMap<String, Object> resultMap = new LinkedTreeMap();
        resultMap.put(PARAM_ERR_YN, S_YES);
        resultMap.put(PARAM_ERR_MSG, S_YES);

        try {
            sResult = startEngine(inJson);
            LOGGER.debug("outJsonText: " + sResult);
            if (sResult == null || 0 == sResult.length()) {
                throw new Exception(ERR_MSG_DEFAULT);
            }

            if (sResult.startsWith("[ERROR-")) {
                resultMap.put(PARAM_ERR_MSG, sResult);
            } else {
                resultMap = jsonToMap(sResult);
            }
        } catch (Exception var6) {
            resultMap.put(PARAM_ERR_MSG, var6.getLocalizedMessage());
            LOGGER.warn("{}\r\n{}", var6.getLocalizedMessage(), sResult);
        }

        return resultMap;
    }

    public static LinkedTreeMap<String, Object> jsonToMap(String json) {
        Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
        Type type = (new TypeToken<LinkedTreeMap<String, Object>>() {
        }).getType();
        return (LinkedTreeMap) gson.fromJson(json, type);
    }

    public static String startEngine(final String inJson) {
        LOGGER.info(">>> LOADING INFOTECH ENGINE : inJson={}", inJson);
        String outJson = "";
        String timeOutMinutes = TIME_OUT_MINUTES;
        String engineCallJarPath = ENGINE_CALL_JAR_PATH;
        String soFilePath = SO_FILE_PATH;
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                Process process = null;
                BufferedWriter bufferWriter = null;
                BufferedReader bufferedReader = null;
                FileWriter fileWriter = null;
                File file = null;
                UUID uuid = UUID.randomUUID();
                String filePath = "/tmp/";
                String inJsonBase64 = "";
                String outJson = "";
                String line = "";

                try {
                    JsonObject tmpJson = (JsonObject) (new Gson()).fromJson(inJson, JsonObject.class);
                    byte[] targetBytes = tmpJson.toString().getBytes();
                    Base64.Encoder encoder = Base64.getEncoder();
                    byte[] encodedBytes = encoder.encode(targetBytes);
                    inJsonBase64 = new String(encodedBytes);
                    if (inJson.length() > 90000) {
                        file = new File("/tmp");
                        if (!file.isDirectory()) {
                            file.mkdir();
                        }

                        fileWriter = new FileWriter(filePath + uuid);
                        bufferWriter = new BufferedWriter(fileWriter);
                        bufferWriter.write(inJsonBase64);
                        bufferWriter.flush();
                        bufferWriter.close();
                        bufferWriter = null;
                        fileWriter.close();
                        fileWriter = null;
                        inJsonBase64 = "IFT_INPUT_FILE:" + filePath + uuid;
                    }

                    String cmd = String.format("java -Dconfig.lib.path.linux=%s -Dconfig.ext.timeout=%s -jar %s '%s'", soFilePath, timeOutMinutes, engineCallJarPath, inJsonBase64);
                    ApiScraper.LOGGER.debug(cmd);
                    process = Runtime.getRuntime().exec(cmd);
                    bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                    System.out.println("@@@@@@-011 -- Processing.....");

                    while ((line = bufferedReader.readLine()) != null) {
                        outJson = outJson + line;
                        outJson = outJson + "\r\n";
                    }

                    bufferedReader.close();
                    process.destroy();
                    if (outJson.indexOf("ENGINE[OUTJSON]") > -1) {
                        outJson = outJson.split("ENGINE\\[OUTJSON\\]")[1];
                    } else {
                        outJson = "[ERROR-002] enginCallException";
                    }
                    LOGGER.debug("<<< LOADING INFOTECH ENGINE : outJson={}", outJson);
                } catch (Exception var19) {
                    System.out.println("@@@@@@-015 :" + var19.getLocalizedMessage());
                    ApiScraper.LOGGER.error(var19.getMessage());
                } finally {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }

                    if (process != null) {
                        process.destroy();
                    }

                    if (bufferWriter != null) {
                        bufferWriter.close();
                    }

                    if (fileWriter != null) {
                        fileWriter.close();
                    }

                }

                if (outJson == null || 0 == outJson.length()) {
                    outJson = "[ERROR-003] outJson null or length 0";
                }
                return outJson;
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try {
            Future<String> future = executorService.submit(callable);
            outJson = (String) future.get(Long.parseLong("5"), TimeUnit.MINUTES);
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
        } catch (Exception var11) {
            if (var11.getMessage() != null) {
                outJson = "[ERROR-004] " + var11.getMessage();
            } else {
                outJson = "[ERROR-005] Unkown error or ift-engine running timeout";
            }

            LOGGER.error("out Json : {}, var11.getLocalizedMessage() : {}", outJson, var11.getLocalizedMessage());
        } finally {
            if (!executorService.isShutdown()) {
                System.out.println("@@@@@@-022");
                executorService.shutdown();
            }

        }

        return outJson;
    }
}
