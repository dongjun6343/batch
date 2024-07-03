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

// YO_VAT_BILL
// 요기요 매입
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "YO_VAT_BILL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class YogiyoVatBill {

    @EmbeddedId
    private YogiyoVatBillId id;

    @Column(name = "QUARTER")
    private String quarter;

    @Column(name = "WRITE_DATE")
    private String writeDate;

    @Column(name = "SEND_DATE")
    private String sendDate;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "SUMMARY")
    private Long summary = 0L;

    @Column(name = "SUPPLY_PRICE")
    private Long supplyPrice = 0L;

    @Column(name = "TAX")
    private Long tax = 0L;

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
    public YogiyoVatBill(String bizUnitSeq, String bizNo, String acceptNumber, String calcYear, String quarter, String writeDate, String sendDate, String productName, Long summary, Long supplyPrice, Long tax) {

        YogiyoVatBillId id = YogiyoVatBillId
                .builder()
                .bizUnitSeq(bizUnitSeq)
                .bizNo(bizNo)
                .acceptNumber(acceptNumber)
                .calcYear(calcYear)
                .build();

        this.id = id;
        this.quarter = quarter;
        this.writeDate = writeDate;
        this.sendDate = sendDate;
        this.productName = productName;
        this.summary = summary;
        this.supplyPrice = supplyPrice;
        this.tax = tax;
    }
}
