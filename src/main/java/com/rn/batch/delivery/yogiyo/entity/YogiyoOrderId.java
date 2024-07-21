package com.rn.batch.delivery.yogiyo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Deprecated
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class YogiyoOrderId implements Serializable {
    @Column(name = "ORDR_SEQ")
    private String orderNo;

    @Column(name = "BIZ_UNIT_SEQ")
    private String bizUnitSeq;

    @Builder
    public YogiyoOrderId(String orderNo, String bizUnitSeq) {
        this.orderNo = orderNo;
        this.bizUnitSeq = bizUnitSeq;
    }
}
