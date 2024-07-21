package com.rn.batch.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 쿠팡이츠 스토어 아이디
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryP0006ResponseDto {

    private String errYn;
    private String errMsg;
    private List<StoreList> outP0006 = new ArrayList<>();

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StoreList {
        private String storeName;
        private String repName;
        private String storeId;
        private String bizNo;
    }
}
