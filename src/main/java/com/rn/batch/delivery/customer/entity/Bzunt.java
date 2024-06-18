package com.rn.batch.delivery.customer.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "BZUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Bzunt {

    @Id
    @Column(name = "BIZ_UNIT_SEQ", nullable = false, length = 20)
    private String bizUnitSeq; // 사업장순번 : 사업장순번

    @Column(name = "USER_ID", nullable = false)
    private String userId; // 사용자아이디 : 사용자아이디

    @Column(name = "BIZ_UNIT_NM", length = 50)
    private String bizUnitName; // 사업장명 : 사업장명

    @Column(name = "BIZ_NO", length = 13)
    private String bizNo; // 사업자번호 : 사업자번호

    @Column(name = "BIZ_STS_CD", length = 10)
    private String bizStsCd; // 사업상태코드 : 00 - 정상, 01 - 폐업, 02 - 휴업, 90 - 미등록사업자

    @Column(name = "VAT_TYPE_CD", length = 10)
    private String vatTypeCd; // 부가가치세유형코드 : 01 - 일반과세자, 02 - 간이과세자 등

    @Column(name = "VAT_TYPE_NM", length = 20)
    private String vatTypeName; // 부가가치세유형한글명 : 부가가치세유형코드에 해당되는 한글명

    @Column(name = "CLOSE_DT", length = 10)
    private String closeDt; // 폐업일 : 폐업자인 경우 표시 (yyyymmdd)

    @Column(name = "VAT_TYPE_CHANGE_DT", length = 10)
    private String vatTypeChangeDt; // 부가세유형변경일자 : 과세유형이 변경된 경우 표시 (yyyymmdd)

    @Column(name = "SRCH_DT", length = 10)
    private String srchDt; // 조회일자 : 조회를 요청한 일자

    @Column(name = "REG_USER_ID", length = 20)
    private String regUserId; // 등록사용자아이디 : 등록사용자아이디

    @Column(name = "MOD_USER_ID", length = 20)
    private String modUserId; // 수정사용자아이디 : 수정사용자아이디

    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate; // 등록날짜 : 등록날짜

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate; // 수정날짜 : 수정날짜
}
