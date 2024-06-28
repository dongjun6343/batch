package com.rn.batch.api.service.impl;

import com.rn.batch.api.scrap.ApiScraper;
import com.rn.batch.api.scrap.ConvertUtils;
import com.rn.batch.api.service.ApiScrapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
//@Profile("!local")
public class ApiScrapServiceImpl implements ApiScrapService {
    @Override
    public <T> T requestScrapData(Object requestDto, Class<T> responseType) {
        String requestJson = ConvertUtils.objectToJson(requestDto);
        String responseJson = ApiScraper.startEngine(requestJson);
        return ConvertUtils.convertToObject(responseJson, responseType);
    }
}
