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
@Table(name = "CUSTOMER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Customer {

    @Id
    @Column(name = "CUST_SEQ", nullable = false, length = 20)
    private String custSeq; // 고객별 순번 - PK

    @Column(name = "APP_USER_ID", nullable = false, length = 30)
    private String appUserId; // App고객인 경우 사용자아이디

    @Column(name = "CNTCT_SEQ", length = 20)
    private String cntctSeq; // 상담요청 키 - CONTACT

    @Column(name = "AGREE_SEQ", length = 20)
    private String agreeSeq; // 수임동의 키 - AGREE_FILES

    @Column(name = "CORP_OR_PERS", length = 1)
    private String corpOrPers; // C:법인, P:개인

    @Column(name = "TAX_TYPE", length = 10)
    private String taxType; // 01:일반, 02:간이, 03:면세 등

    @Column(name = "BIZ_NM", length = 100)
    private String bizNm; // 업체명 또는 이름

    @Column(name = "BIZ_NO", length = 13)
    private String bizNo; // 사업자번호 또는 주민번호

    @Column(name = "RPRS_NM", length = 100)
    private String rprsNm; // 대표자명

    @Column(name = "ALIAS", length = 100)
    private String alias; // 그룹관리(별칭)

    @Column(name = "LABEL", length = 100)
    private String label; // 구분을 위한 표시

    @Column(name = "DSCM_DT", length = 100)
    private String dscmDt; // 개업일자

    @Column(name = "CFB_DT", length = 8)
    private String cfbDt; // 폐업일자

    @Column(name = "DSMS_DT", length = 8)
    private String dsmsDt; // 해임일자

    @Column(name = "BC_NM", length = 500)
    private String bcNm; // 업태

    @Column(name = "ITEM_NM", length = 500)
    private String itemNm; // 업종

    @Column(name = "WHTX_DUTY_CL_NM", length = 20)
    private String whtxDutyClNm; // 원천징수의무구분

    @Column(name = "BIZ_TYPE_RESTAURANT_YN", length = 1)
    private String bizTypeRestaurantYn; // 음식점업 해당 여부

    @Column(name = "VEHICLE_DEDUCTION_YN", length = 1)
    private String vehicleDeductionYn; // 공제차량 보유 여부

    @Column(name = "PAYROLL_YN", nullable = false, length = 1)
    private String payrollYn; // 인건비지원여부

    @Column(name = "YE_TAX_ADJ_YN", length = 1)
    private String yeTaxAdjYn; // 연말정산지원여부

    @Column(name = "TOTAL_EMPLOYEES")
    private Integer totalEmployees; // 직원 수

    @Column(name = "AGENCY_YN", length = 1)
    private String agencyYn; // 기존세무사유무

    @Column(name = "AGENCY_NAME", length = 100)
    private String agencyName; // 기존 세무대리인 상호

    @Column(name = "AGENCY_TEL_1", length = 100)
    private String agencyTel1; // 기존 세무대리인 연락처 1

    @Column(name = "AGENCY_TEL_2", length = 100)
    private String agencyTel2; // 기존 세무대리인 연락처 2

    @Column(name = "BACKUP_FILE_KIND", length = 1)
    private String backupFileKind; // 백업파일 종류

    @Column(name = "BACKUP_FILE_REQ_PROCESS", length = 2)
    private String backupFileReqProcess; // 백업파일 수령 진행상황

    @Column(name = "BACKUP_FILE_MOD_DATE")
    private LocalDateTime backupFileModDate; // 백업파일 수령 진행일시

    @Column(name = "S_MIG_YN", length = 1)
    private String sMigYn; // S마이그레이션 여부

    @Column(name = "DOUZONE_YN", length = 1)
    private String douzoneYn; // 더존 여부

    @Column(name = "CUS_CODE_CRT_YN", length = 1)
    private String cusCodeCrtYn; // 고객코드 생성 여부

    @Column(name = "CUS_FOLDER_CRT_YN", length = 1)
    private String cusFolderCrtYn; // 고객사 폴더 생성 여부

    @Column(name = "TRANSFER_USER_ID", length = 50)
    private String transferUserId; // 이관담당자 ID

    @Column(name = "TRANSFER_MEMO", length = 200)
    private String transferMemo; // 이관자료 메모

    @Column(name = "SEMULOVE_CODE", length = 4)
    private String semuloveCode; // 세무사랑 코드

    @Column(name = "ANNUAL_SALES", length = 100)
    private String annualSales; // 연매출규모

    @Column(name = "ANNUAL_BILLS", length = 100)
    private String annualBills; // 연매입규모

    @Column(name = "SALES_TYPE", length = 100)
    private String salesType; // 매출형태

    @Column(name = "REG_CARD_YN", length = 1)
    private String regCardYn; // 국세청 카드등록여부

    @Column(name = "EMPLOYEE_YN", length = 1)
    private String employeeYn; // 직원유무

    @Column(name = "PAPER_TAX_INVOICE_YN", length = 1)
    private String paperTaxInvoiceYn; // 종이세금계산서 여부

    @Column(name = "VAT_YN", length = 1)
    private String vatYn; // 부가세 신고 여부

    @Column(name = "DOUBLE_ENTRY_YN", length = 1)
    private String doubleEntryYn; // 복식부기유무

    @Column(name = "PAYROLL_CHECK_YN", length = 1)
    private String payrollCheckYn; // 인건비

    @Column(name = "BIZ_ACCOUNT_YN", length = 1)
    private String bizAccountYn; // 홈택스 사업용계좌 등록여부 확인

    @Column(name = "BIZ_CARD_YN", length = 1)
    private String bizCardYn; // 홈택스 사업용신용카드 등록여부 확인

    @Column(name = "SEND_MANUAL_YN", length = 1)
    private String sendManualYn; // 리드넘버 이용가이드 발송 여부

    @Column(name = "COMMISSION_CHECK_YN", length = 1)
    private String commissionCheckYn; // 수수료 책정 정확성 확인

    @Column(name = "CHECK_LIST_TAX_1", nullable = false, length = 1)
    private String checkListTax1; // 겸영사업자 여부

    @Column(name = "CHECK_LIST_TAX_2", nullable = false, length = 1)
    private String checkListTax2; // 사업용신용카드 등록여부

    @Column(name = "CHECK_LIST_TAX_3", nullable = false, length = 1)
    private String checkListTax3; // 수기 세금계산서 사용여부

    @Column(name = "CHECK_LIST_TAX_4", nullable = false, length = 1)
    private String checkListTax4; // 플랫폼 매출 유무

    @Column(name = "CHECK_LIST_TAX_5", nullable = false, length = 1)
    private String checkListTax5; // 자산 유무

    @Column(name = "RENT")
    private Integer rent; // 임대료

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status; // 관리상태

    @Column(name = "DIVISION", nullable = false, length = 1)
    private String division; // 관리구분

    @Column(name = "CUST_STATUS", length = 1)
    private String custStatus; // 고객상태

    @Column(name = "STATUS_DETAIL", length = 1)
    private String statusDetail; // 상세 상태

    @Column(name = "REPORT_YEAR", length = 4)
    private String reportYear; // 신고년도

    @Column(name = "REPORT_STAGE", length = 5)
    private String reportStage; // 신고기수

    @Column(name = "REPORT_TYPE", length = 1)
    private String reportType; // 신고유형

    @Column(name = "V_TAX_YN", nullable = false, length = 1)
    private String vTaxYn; // 부가가치세 관리 여부

    @Column(name = "I_TAX_YN", nullable = false, length = 1)
    private String iTaxYn; // 종합소득세 관리 여부

    @Column(name = "C_TAX_YN", nullable = false, length = 1)
    private String cTaxYn; // 법인세 관리 여부

    @Column(name = "F_TAX_YN", nullable = false, length = 1)
    private String fTaxYn; // 면세사업장현황신고 관리 여부

    @Column(name = "ALIAS2", length = 100)
    private String alias2; // 기존 BIZ_NM 복사

    @Column(name = "REG_USER_ID", length = 50)
    private String regUserId; // 등록사용자아이디: 관리자 ID

    @Column(name = "MOD_USER_ID", length = 50)
    private String modUserId; // 수정사용자아이디: 관리자 ID

    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate; // 등록날짜

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate; // 수정날짜

    @Column(name = "FC_PAYROLL_YN", nullable = false, length = 1)
    private String fcPayrollYn; // 첫콜 - 인건비 지원 여부 YN

    @Column(name = "FC_PAYROLL_MANAGER", length = 20)
    private String fcPayrollManager; // 첫콜 - 인건비 담당자 ID

    @Column(name = "FC_PAYROLL_STATUS", nullable = false, length = 3)
    private String fcPayrollStatus; // 첫콜 - 인건비 상태 (N: 미진행 W: 진행중 C: 완료)

    @Column(name = "FC_PAYROLL_CLOSE_DATE")
    private LocalDateTime fcPayrollCloseDate; // 첫콜 - 인건비 마감 일시

    @Column(name = "FC_TAX_MANAGER", length = 20)
    private String fcTaxManager; // 첫콜 - 세무상담 담당자 ID

    @Column(name = "FC_TAX_STATUS", nullable = false, length = 3)
    private String fcTaxStatus; // 첫콜 - 세무상담 상태 (N: 미진행 W: 진행중 C: 완료)

    @Column(name = "FC_TAX_CLOSE_DATE")
    private LocalDateTime fcTaxCloseDate; // 첫콜 - 세무상담 마감 일시

    @Column(name = "CELL_ID")
    private Long cellId; // 외래키 필드
}
