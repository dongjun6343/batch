package com.rn.batch.delivery.yogiyo.entity;

// YO_BILL

import com.rn.batch.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "YO_BILL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class YogiyoBill extends BaseEntity {

    @EmbeddedId
    private YogiyoBillId id;

    @Column(name = "FIN_AMT")
    private Long finAmt;

    @Column(name = "ORDER_AMT")
    private Long orderAmt;

    @Column(name = "DELIVERY_FEE")
    private Long deliveryFee;

    @Column(name = "RESTAURANT_SAVE")
    private Long restaurantSave;

    @Column(name = "CONTRACT_COMMISSION_FEE")
    private Long contractCommissionFee;

    @Column(name = "PAYMENT_COMMISSION_FEE")
    private Long paymentCommissionFee;

    @Column(name = "TML_AMT")
    private Long tmlAmt;

    @Column(name = "PLC_AMT")
    private Long plcAmt;

    @Column(name = "MON_AMT")
    private Long monAmt;

    @Column(name = "MINUS_VAT")
    private Long minusVat;

    @Column(name = "UNC_AMT")
    private Long uncAmt;

    @Column(name = "OFFLINE_AMT")
    private Long offlineAmt;

    @Builder
    public YogiyoBill(String bizUnitSeq, String paymentDt, String sedateStartDt, String sedateEndDt, Long finAmt, Long orderAmt, Long deliveryFee, Long restaurantSave,
                      Long contractCommissionFee, Long paymentCommissionFee, Long tmlAmt, Long plcAmt, Long monAmt, Long minusVat, Long uncAmt, Long offlineAmt) {

        YogiyoBillId id = YogiyoBillId.builder()
                .bizUnitSeq(bizUnitSeq)
                .paymentDt(paymentDt)
                .sedateStartDt(sedateStartDt)
                .sedateEndDt(sedateEndDt)
                .build();

        this.id = id;
        this.finAmt = finAmt;
        this.orderAmt = orderAmt;
        this.deliveryFee = deliveryFee;
        this.restaurantSave = restaurantSave;
        this.contractCommissionFee = contractCommissionFee;
        this.paymentCommissionFee = paymentCommissionFee;
        this.tmlAmt = tmlAmt;
        this.plcAmt = plcAmt;
        this.monAmt = monAmt;
        this.minusVat = minusVat;
        this.uncAmt = uncAmt;
        this.offlineAmt = offlineAmt;
    }
}
