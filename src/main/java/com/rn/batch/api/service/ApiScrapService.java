package com.rn.batch.api.service;

public interface ApiScrapService {
    public <T> T requestScrapData(Object requestDto, Class<T> responseType);
}
