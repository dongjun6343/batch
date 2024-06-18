package com.rn.batch.delivery.customer.entity;

import com.rn.batch.api.code.OrgCd;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class ScrapOrgId implements Serializable {
    @Column(name = "BIZ_UNIT_SEQ", nullable = false, length = 20)
    private String bizUnitSeq; // 사업장순번 : 사업장순번

    @Column(name = "ORG_ID", nullable = false, length = 20)
    private String orgId; // 기관아이디 : 기관아이디 (card, bank, cardsales, hometax)

    @Column(name = "ORG_CD")
    @Enumerated(EnumType.STRING)
    private OrgCd orgCd; // 기관코드 : 은행코드, 카드사코드
}
