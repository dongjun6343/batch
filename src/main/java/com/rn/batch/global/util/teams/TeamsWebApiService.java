package com.rn.batch.global.util.teams;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamsWebApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public <T> T requestPost(Object requestDto, Class<T> responseType, String baseUrl) {
        try {
            String requestJson = objectMapper.writeValueAsString(requestDto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

            // 쿼리 파라미터를 추가하지 않고 url로 발송할 경우 에러발생! => 쿼리 파라미터로 추가
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("api-version", "2016-06-01")
                    .queryParam("sp", "/triggers/manual/run")
                    .queryParam("sv", "1.0")
                    .queryParam("sig", "kFFV2B59L55WVm3qaZMrtF6waH5JXspZqBnuoTtYn1k");

            String url = builder.toUriString();
            log.info("URL: {}", url);
            log.info("HEADER : {}", headers);

            ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);

            log.info("exchange.getStatusCode : {}", exchange.getStatusCode());
            log.info("exchange.getBody : {}", exchange.getBody());

            return exchange.getBody();
        } catch (JsonProcessingException e) {
            log.error("requestPost error", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("error !!! ", e);
            throw new RuntimeException(e);
        }
    }
}
