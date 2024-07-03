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

/**
 * 요기요 주문
 * P0001 (매출내역)
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "YO_ORDR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class YogiyoOrder {

    @EmbeddedId
    private YogiyoOrderId id;

    @Column(name = "TRNS_DATE")
    private String trnsDate; // orderDt + orderTm -> trnsDate

    @Column(name = "ORDER_STTS")
    private String orderDiv;

    @Column(name = "SHOP_NM")
    private String storeName;

    @Column(name = "ADDR")
    private String address;

    @Column(name = "TEL_NO")
    private String telNo;

    @Column(name = "ORDER_PRICE")
    private String orderAmt;

    @Column(name = "DLVR_PRICE")
    private String deliveryAmt;

    @Column(name = "PRCHS_TYPE")
    private String payMet;

    @Column(name = "ONWER_SALE_PRICE")
    private String discntAmt;

    @Column(name = "YGY_SALE_SPPRT_PRICE")
    private String headDiscntAmt;

    @Column(name = "REG_USER_ID")
    private String regUserId = "rn-batch";

    @Column(name = "MOD_USER_ID")
    private String modUserId = "rn-batch";

    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

    @Builder
    public YogiyoOrder(String orderNo, String bizUnitSeq, String trnsDate, String orderDiv, String storeName, String address, String telNo,
                       String orderAmt, String deliveryAmt, String payMet, String discntAmt, String headDiscntAmt) {

        YogiyoOrderId id = YogiyoOrderId.builder()
                .orderNo(orderNo)
                .bizUnitSeq(bizUnitSeq)
                .build();

        this.id = id;
        this.trnsDate = trnsDate;
        this.orderDiv = orderDiv;
        this.storeName = storeName;
        this.address = address;
        this.telNo = telNo;
        this.orderAmt = orderAmt;
        this.deliveryAmt = deliveryAmt;
        this.payMet = payMet;
        this.discntAmt = discntAmt;
        this.headDiscntAmt = headDiscntAmt;
    }
}
