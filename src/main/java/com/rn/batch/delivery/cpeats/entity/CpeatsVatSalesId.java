package com.rn.batch.delivery.cpeats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class CpeatsVatSalesId implements Serializable {

    @Column(name = "BIZ_UNIT_SEQ")
    private String bizUnitSeq;

    @Column(name = "ORDER_NO")
    private String orderNo;

    @Column(name = "STORE_ID")
    private String storeId;

    @Column(name = "RECOGNITION_DATE")
    private String recognitionDate;

    @Builder
    public CpeatsVatSalesId(String bizUnitSeq, String orderNo, String storeId, String recognitionDate) {
        this.bizUnitSeq = bizUnitSeq;
        this.orderNo = orderNo;
        this.storeId = storeId;
        this.recognitionDate = recognitionDate;
    }
}
