package com.rn.batch.delivery.yogiyo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// YO_VAT_SALES
// 요기요 VAT 매출

@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "YO_VAT_SALES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class YogiyoVatSales {

    @EmbeddedId
    private YogiyoVatSalesId id;

    @Column(name = "QUARTER")
    private String quarter;

    @Column(name = "CNT")
    private Long cnt = 0L;

    @Column(name = "ONLINE_CARD")
    private Long onlineCard = 0L;

    @Column(name = "ONLINE_HP")
    private Long onlineHp = 0L;

    @Column(name = "OFFLINE_CARD")
    private Long offlineCard = 0L;

    @Column(name = "OFFLINE_CASH")
    private Long offlineCash = 0L;

    @Column(name = "ONLINE_ETC")
    private Long onlineEtc = 0L;

    @Column(name = "SUM_REST_DISCOUNT")
    private Long sumRestDiscount = 0L;

    @Column(name = "FIGURES")
    private Long figures = 0L;

    @Column(name = "REG_USER_ID")
    private String regUserId = "api";

    @Column(name = "MOD_USER_ID")
    private String modUserId = "api";

    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

    @Builder
    public YogiyoVatSales(String bizUnitSeq, String bizNo, String calcYear, String calcMonth, String quarter, Long cnt, Long onlineCard, Long onlineHp,
                          Long offlineCard, Long offlineCash, Long onlineEtc, Long sumRestDiscount, Long figures) {

        YogiyoVatSalesId id = YogiyoVatSalesId.builder()
                .bizUnitSeq(bizUnitSeq)
                .bizNo(bizNo)
                .calcYear(calcYear)
                .calcMonth(calcMonth)
                .build();

        this.id = id;
        this.quarter = quarter;
        this.cnt = cnt;
        this.onlineCard = onlineCard;
        this.onlineHp = onlineHp;
        this.offlineCard = offlineCard;
        this.offlineCash = offlineCash;
        this.onlineEtc = onlineEtc;
        this.sumRestDiscount = sumRestDiscount;
        this.figures = figures;
    }
}
