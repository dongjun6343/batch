package com.rn.batch.delivery.customer.repository.custom;

import com.rn.batch.api.code.OrgCd;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<DeliveryLoginInfoResponseDto> findDeliveryLoginInfo(String custStatus, OrgCd orgCd);
}
