package com.rn.batch.delivery.cpeats.entity;


import com.rn.batch.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CE_VAT_SALES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CpeatsVatSales extends BaseEntity {

    @EmbeddedId
    private CpeatsVatSalesId id;

    @Column(name = "RECOG_DT")
    private String recogDt;

    @Column(name = "CREDIT_PAY_AMOUNT")
    private Long creditPayAmount = 0L;

    @Column(name = "CASH_PAY_AMOUNT")
    private Long cashPayAmount = 0L;

    @Column(name = "ETC_PAY_AMOUNT")
    private Long etcPayAmount = 0L;

    @Column(name = "ST_DISC_PAY_AMOUNT")
    private Long stDiscPayAmount = 0L;

    @Column(name = "CREDIT_REFUND")
    private Long creditRefund = 0L;

    @Column(name = "CASH_REFUND")
    private Long cashRefund = 0L;

    @Column(name = "ETC_REFUND")
    private Long etcRefund = 0L;

    @Column(name = "ST_DISC_REFUND")
    private Long stDiscRefund = 0L;

    @Builder
    public CpeatsVatSales(String bizUnitSeq, String orderNo, String storeId, String recognitionDate, String recogDt,
                          Long creditPayAmount, Long cashPayAmount, Long etcPayAmount, Long stDiscPayAmount, Long creditRefund,
                          Long cashRefund, Long etcRefund, Long stDiscRefund) {

        CpeatsVatSalesId id = CpeatsVatSalesId.builder()
                .bizUnitSeq(bizUnitSeq)
                .orderNo(orderNo)
                .storeId(storeId)
                .recognitionDate(recognitionDate)
                .build();
        this.id = id;
        this.recogDt = recogDt;
        this.creditPayAmount = creditPayAmount;
        this.cashPayAmount = cashPayAmount;
        this.etcPayAmount = etcPayAmount;
        this.stDiscPayAmount = stDiscPayAmount;
        this.creditRefund = creditRefund;
        this.cashRefund = cashRefund;
        this.etcRefund = etcRefund;
        this.stDiscRefund = stDiscRefund;
    }
}
