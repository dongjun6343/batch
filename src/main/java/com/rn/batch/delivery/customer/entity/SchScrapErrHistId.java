package com.rn.batch.delivery.customer.entity;

import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.code.SvcCd;
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
public class SchScrapErrHistId implements Serializable {

    @Column(name = "BIZ_UNIT_SEQ", length = 20, nullable = false)
    private String bizUnitSeq;

    @Column(name = "ORG_CD", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrgCd orgCd;

    @Column(name = "SVC_CD", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private SvcCd svcCd;

    @Builder
    public SchScrapErrHistId(String bizUnitSeq, OrgCd orgCd, SvcCd svcCd) {
        this.bizUnitSeq = bizUnitSeq;
        this.orgCd = orgCd;
        this.svcCd = svcCd;
    }
}
