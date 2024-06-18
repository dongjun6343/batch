package com.rn.batch.delivery.yogiyo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class YogiyoVatSalesId implements Serializable {

    @Column(name = "BIZ_UNIT_SEQ")
    private String bizUnitSeq;

    @Column(name = "BIZ_NO")
    private String bizNo;

    @Column(name = "CALC_YEAR")
    private String calcYear;

    @Column(name = "CALC_MONTH")
    private String calcMonth;

    @Builder
    public YogiyoVatSalesId(String bizUnitSeq, String bizNo, String calcYear, String calcMonth) {
        this.bizUnitSeq = bizUnitSeq;
        this.bizNo = bizNo;
        this.calcYear = calcYear;
        this.calcMonth = calcMonth;
    }
}
