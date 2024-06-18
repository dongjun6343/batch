package com.rn.batch.delivery.yogiyo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class YogiyoBillId implements Serializable {

    @Column(name = "BIZ_UNIT_SEQ")
    private String bizUnitSeq;

    @Column(name = "PAYMENT_DT")
    private String paymentDt;

    @Column(name = "SEDATE_START_DT")
    private String sedateStartDt;

    @Column(name = "SEDATE_END_DT")
    private String sedateEndDt;

    @Builder
    public YogiyoBillId(String bizUnitSeq, String paymentDt, String sedateStartDt, String sedateEndDt) {
        this.bizUnitSeq = bizUnitSeq;
        this.paymentDt = paymentDt;
        this.sedateStartDt = sedateStartDt;
        this.sedateEndDt = sedateEndDt;
    }
}
