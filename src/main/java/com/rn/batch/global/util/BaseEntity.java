package com.rn.batch.global.util;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(name = "REG_USER_ID", updatable = false)
    private String regUserId = "rn-batch";

    @LastModifiedBy
    @Column(name = "MOD_USER_ID")
    private String modUserId = "rn-batch";
}
