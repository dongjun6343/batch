package com.rn.batch.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * M0001 : 부가세 신고내역
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryM0001ResponseDto {
    private String errYn;
    private String errMsg;
    private DeliveryVat outM0001;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryVat {
        private String errYn;
        private String errMsg;
        private String bizNo;
        private List<VatSales> list_1 = new ArrayList<>();
        private List<VatPurchases> list_2 = new ArrayList<>();

        // 쿠팡이츠 자동매출집계
        private List<CpeatsVat> list = new ArrayList<>();
    }


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VatSales {
        private String salesDt;
        private String salesCnt;
        private String onlineCredit;
        private String onlinePhone;
        private String onlineEtc;
        private String offlineCredit;
        private String offlineCash;
        private String selfDisc;
        private String pchAmt;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VatPurchases {
        private String writeDt;
        private String sendDt;
        private String apprNo;
        private String itemNm;
        private String totalAmt;
        private String supAmt;
        private String taxAmt;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Store {
        private String storeName;           // 상호명
        private String storeId;         // 상호id
        private String bizNo;           // 사업자번호
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CpeatsVat {
        private String selectDt;
        private String creditAmt;
        private String cashAmt;
        private String etcAmt;
        private String discountAmt;
        private String dspsblAmt;
        private String total;
    }
}
