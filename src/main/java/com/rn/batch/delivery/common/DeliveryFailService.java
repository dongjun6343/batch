package com.rn.batch.delivery.common;

import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.code.SvcCd;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;
import com.rn.batch.delivery.customer.entity.SchScrapErrHist;
import com.rn.batch.delivery.customer.repository.SchScrapErrHistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryFailService {

    private final SchScrapErrHistRepository schScrapErrHistRepository;

    @Transactional
    public void save(DeliveryLoginInfoResponseDto deliveryRequestDto, String errMessage, SvcCd svcCd, OrgCd orgCd) {
        SchScrapErrHist schScrapErrHist = SchScrapErrHist.builder()
                .bizUnitSeq(deliveryRequestDto.getBizUnitSeq())
                .orgCd(orgCd)
                .svcCd(SvcCd.M0001)
                .bizNo(deliveryRequestDto.getCustomer().getBizNo())
                .status(BatchStatus.FAILED)
                .errMsg(errMessage)
                .loginId(deliveryRequestDto.getLoginId())
                .loginPw(deliveryRequestDto.getLoginPw())
                .regUserId("api")
                .modUserId("api")
                .build();
        schScrapErrHistRepository.save(schScrapErrHist);
        log.warn("DeliveryFailService.save - OrgCd : {} , SvcCd : {} , errMsg : {} , bizUnitSeq : {} ",
                orgCd, svcCd, errMessage, deliveryRequestDto.getBizUnitSeq());
    }
}
