package com.rn.batch.delivery.yogiyo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class YogiyoVatBillId implements Serializable {

    @Column(name = "BIZ_UNIT_SEQ")
    private String bizUnitSeq;

    @Column(name = "BIZ_NO")
    private String bizNo;

    @Column(name = "ACCEPT_NUMBER")
    private String acceptNumber;

    @Column(name = "CALC_YEAR")
    private String calcYear;

    @Builder
    public YogiyoVatBillId(String bizUnitSeq, String bizNo, String acceptNumber, String calcYear) {
        this.bizUnitSeq = bizUnitSeq;
        this.bizNo = bizNo;
        this.acceptNumber = acceptNumber;
        this.calcYear = calcYear;
    }
}
