package com.rn.batch.delivery.cpeats.entity;

import com.rn.batch.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 CREATE TABLE `CE_STORE` (
 `ID` bigint(20) NOT NULL AUTO_INCREMENT,
 `BIZ_NO` varchar(255) NOT NULL,
 `STORE_ID` varchar(255) NOT NULL,
 `STORE_NAME` varchar(255) DEFAULT NULL,
 `REP_NAME` varchar(255) DEFAULT NULL,
 `REG_USER_ID` varchar(20) DEFAULT NULL,
 `MOD_USER_ID` varchar(20) DEFAULT NULL,
 `REG_DATE` datetime(6) DEFAULT NULL,
 `MOD_DATE` datetime(6) DEFAULT NULL,
 PRIMARY KEY (`ID`),
 UNIQUE KEY `STORE_SEQ` (`BIZ_NO`,`STORE_ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 todo : 부가세 시 storeId insert하도록 수정, 기존 스토어아이디 m0001 삭제.
 */

@Getter
@Table(name = "CE_STORE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CpeatsStore extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BIZ_UNIT_SEQ", nullable = false, unique = true)
    private String bizUnitSeq;

    @Column(name = "BIZ_NO", nullable = false, unique = true)
    private String bizNo;

    @Column(name = "STORE_ID", nullable = false, unique = true)
    private String storeId;

    @Column(name = "STORE_NAME")
    private String storeName;

    @Column(name = "REP_NAME")
    private String repName;

    @Builder
    public CpeatsStore(String bizUnitSeq, String bizNo, String storeId, String storeName, String repName) {
        this.bizUnitSeq = bizUnitSeq;
        this.bizNo = bizNo;
        this.storeId = storeId;
        this.storeName = storeName;
        this.repName = repName;
    }
}
