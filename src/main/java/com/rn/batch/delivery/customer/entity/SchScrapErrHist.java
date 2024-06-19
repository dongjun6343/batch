package com.rn.batch.delivery.customer.entity;

import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.code.SvcCd;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SCH_SCRAP_ERR_HIST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SchScrapErrHist {

    // bizUnitSeq, orgCd, svcCd, status, regDate, modDate : idx

    @EmbeddedId
    private SchScrapErrHistId id;

    @Column(name = "ERR_MSG", length = 2000, nullable = false)
    private String errMsg;

    @Column(name = "STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BatchStatus status;

    @Column(name = "BIZ_NO", length = 30)
    private String bizNo;

    @Column(name = "LOGIN_ID", length = 30)
    private String loginId;

    @Column(name = "LOGIN_PW", length = 30)
    private String loginPw;

    @Column(name = "REG_USER_ID", length = 20)
    private String regUserId;

    @Column(name = "MOD_USER_ID", length = 20)
    private String modUserId;

    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate; // 등록날짜

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate; // 수정날짜

    @Builder
    public SchScrapErrHist(String bizUnitSeq, OrgCd orgCd, SvcCd svcCd, BatchStatus status,
                           String bizNo, String loginId, String loginPw,
                           String errMsg, String regUserId, String modUserId) {
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
        this.regUserId = regUserId;
        this.modUserId = modUserId;
    }
}

