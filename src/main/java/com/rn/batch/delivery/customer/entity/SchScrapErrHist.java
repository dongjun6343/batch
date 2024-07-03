package com.rn.batch.delivery.customer.entity;

import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.code.SvcCd;
import com.rn.batch.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SCH_SCRAP_ERR_HIST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SchScrapErrHist extends BaseEntity {

    // bizUnitSeq, orgCd, svcCd, status, regDate, modDate : idx

    @EmbeddedId
    private SchScrapErrHistId id;

    @Column(name = "ERR_MSG", length = 2000, nullable = false)
    private String errMsg;

    @Column(name = "STATUS", length = 20)
    private String status;

    @Column(name = "BIZ_NO", length = 30)
    private String bizNo;

    @Column(name = "LOGIN_ID", length = 30)
    private String loginId;

    @Column(name = "LOGIN_PW", length = 30)
    private String loginPw;

    @Builder
    public SchScrapErrHist(String bizUnitSeq, OrgCd orgCd, SvcCd svcCd, String status,
                           String bizNo, String loginId, String loginPw, String errMsg) {
        SchScrapErrHistId id = SchScrapErrHistId.builder()
                .bizUnitSeq(bizUnitSeq)
                .orgCd(orgCd)
                .svcCd(svcCd)
                .build();
        this.id = id;
        this.errMsg = errMsg;
        this.status = status;
        this.bizNo = bizNo;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}

