package com.rn.batch.delivery.common;

import com.rn.batch.api.code.MallCd;
import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.code.SvcCd;
import com.rn.batch.api.dto.DeliveryM0001RequestDto;
import com.rn.batch.api.dto.DeliveryM0001ResponseDto;
import com.rn.batch.api.scrap.ApiScrapDeliveryM000X;
import com.rn.batch.delivery.cpeats.service.CpeatsVatService;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;
import com.rn.batch.delivery.customer.repository.CustomerRepository;
import com.rn.batch.delivery.yogiyo.service.YogiyoVatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final ApiScrapDeliveryM000X apiScrapDeliveryM000X;
    private final CustomerRepository customerRepository;
    private final YogiyoVatService yogiyoVatService;
    private final CpeatsVatService cpeatsVatService;

    private final DeliveryFailService deliveryFailService;

    public void vatHist(MallCd mallCd, OrgCd orgCd) {

        List<DeliveryLoginInfoResponseDto> baedalLoginInfo = customerRepository.findDeliveryLoginInfo("", orgCd);

        log.info("[{}] Scraping start!!! ", orgCd);
        for (DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto : baedalLoginInfo) {
            DeliveryM0001RequestDto requestDto = DeliveryM0001RequestDto.builder()
                    .mallCd(mallCd)
                    .userId(deliveryLoginInfoResponseDto.getLoginId())
                    .userPw(deliveryLoginInfoResponseDto.getLoginPw())
                    .dateFrom("20240101")
                    .dateTo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                    .bizNo(deliveryLoginInfoResponseDto.getCustomer().getBizNo())
                    .build();
            DeliveryM0001ResponseDto m0001 = apiScrapDeliveryM000X.getM0001(requestDto);

            if ("Y".equals(m0001.getErrYn()) || "Y".equals(m0001.getOutM0001().getErrYn())) {
                log.warn("m0001.getErrMsg : {} ", m0001.getErrMsg());
                deliveryFailService.save(deliveryLoginInfoResponseDto, m0001.getErrMsg().isEmpty() ? m0001.getOutM0001().getErrMsg() : m0001.getErrMsg(), SvcCd.M0001, OrgCd.yogiyo);
            } else {

                if (OrgCd.yogiyo == orgCd) {
                    yogiyoVatService.save(deliveryLoginInfoResponseDto, m0001);
                } else if (OrgCd.cpeats == orgCd) {
                    cpeatsVatService.save(deliveryLoginInfoResponseDto, m0001);
                }
            }
        }
    }
}
