package com.rn.batch.delivery.customer.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.rn.batch.delivery.customer.entity.Customer;
import lombok.Data;

@Data
public class DeliveryLoginInfoResponseDto {
    private String bizUnitSeq;
    private String loginId;
    private String loginPw;
    private Customer customer;

    @QueryProjection
    public DeliveryLoginInfoResponseDto(String bizUnitSeq, String loginId, String loginPw, Customer customer) {
        this.bizUnitSeq = bizUnitSeq;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.customer = customer;
    }
}
