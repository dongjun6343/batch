package com.rn.batch.delivery.customer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SCRAP_ORG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ScrapOrg {
    @EmbeddedId
    private ScrapOrgId id;

    @Column(name = "SVC_CLAS_CD", nullable = false, length = 10)
    private String svcClasCd; // 서비스구분코드: 기본, 개인, 기업, 빠른

    @Column(name = "LOGIN_TYPE_CD", length = 10)
    private String loginTypeCd; // 로그인유형코드: ID, CERT

    @Column(name = "ENC_YN", length = 1)
    private String encYn; // 인코딩여부

    @Column(name = "CERT_NM", length = 200)
    private String certNm; // 인증서명

    @Column(name = "LOGIN_ID", length = 100)
    private String loginId; // 로그인아이디

    @Column(name = "LOGIN_PW", length = 512)
    private String loginPw; // 로그인암호

    @Column(name = "TXAGNC_ID", length = 20)
    private String txagncId; // 세무사아이디

    @Column(name = "TXAGNC_PW", length = 512)
    private String txagncPw; // 세무사암호

    @Column(name = "LOGIN_ERR_YN", length = 1)
    private String loginErrYn; // 로그인오류여부

    @Column(name = "LOGIN_ERR_DATE")
    private LocalDate loginErrDate; // 로그인오류날짜

    @Column(name = "LOGIN_ERR_JOB_SEQ", length = 20)
    private String loginErrJobSeq; // 로그인오류작업순번

    @Column(name = "YS_FAST_LOGIN_YN", length = 1)
    private String ysFastLoginYn; // 여신간편로그인여부

    @Column(name = "USE_YN", length = 1)
    private String useYn; // 사용여부

    @Column(name = "CHK_ADMIN_ID", length = 20)
    private String chkAdminId; // 아이디 체크 담당자 ID

    @Column(name = "REG_USER_ID", length = 20)
    private String regUserId; // 등록사용자아이디

    @Column(name = "MOD_USER_ID", length = 20)
    private String modUserId; // 수정사용자아이디

    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate; // 등록날짜

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate; // 수정날짜
}
