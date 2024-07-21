package com.rn.batch.delivery.common;

import com.rn.batch.api.code.MallCd;
import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.code.SvcCd;
import com.rn.batch.api.dto.DeliveryM0001RequestDto;
import com.rn.batch.api.dto.DeliveryM0001ResponseDto;
import com.rn.batch.api.dto.DeliveryP0006RequestDto;
import com.rn.batch.api.dto.DeliveryP0006ResponseDto;
import com.rn.batch.api.scrap.ApiScrapDeliveryM000X;
import com.rn.batch.api.scrap.ApiScrapDeliveryP000X;
import com.rn.batch.delivery.cpeats.entity.CpeatsStore;
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
    private final ApiScrapDeliveryP000X apiScrapDeliveryP000X;

    public void scrapStoreId(MallCd mallCd, OrgCd orgCd) {
        List<DeliveryLoginInfoResponseDto> baedalLoginInfo = customerRepository.findDeliveryLoginInfo("", orgCd);
        for (DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto : baedalLoginInfo) {
            DeliveryP0006RequestDto p0006RequestDto = DeliveryP0006RequestDto.builder()
                    .mallCd(mallCd)
                    .userId(deliveryLoginInfoResponseDto.getLoginId())
                    .userPw(deliveryLoginInfoResponseDto.getLoginPw())
                    .dateFrom(LocalDate.now().getYear() + "0101")
                    .dateTo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                    .build();
            DeliveryP0006ResponseDto p0006 = apiScrapDeliveryP000X.getP0006(p0006RequestDto);
            if ("Y".equals(p0006.getErrYn())) {
                log.warn("p0006.getErrMsg : {} ", p0006.getErrMsg());
            } else {
                log.info("p0006.saveStoreList start!! bizUnitSeq : {} ", deliveryLoginInfoResponseDto.getBizUnitSeq());
                cpeatsVatService.saveStoreList(p0006, deliveryLoginInfoResponseDto.getBizUnitSeq());
            }
        }
    }

    public void scrapVatHist(MallCd mallCd, OrgCd orgCd) {

        List<DeliveryLoginInfoResponseDto> baedalLoginInfo = customerRepository.findDeliveryLoginInfo("", orgCd);

        log.info("[{}] Scraping start!!! ", orgCd);
        for (DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto : baedalLoginInfo) {
            if (OrgCd.cpeats == orgCd) {
                List<CpeatsStore> storeList = cpeatsVatService.findStoreList(deliveryLoginInfoResponseDto);
                if (storeList.size() == 0) {
                    log.warn("[{}] BizNo : {}의 스토어 아이디가 없습니다.", orgCd, deliveryLoginInfoResponseDto.getCustomer().getBizNo());
                    return;
                }
                for (CpeatsStore cpeatsStore : storeList) {
                    log.info("[{}] BizNo : {} , StoreId : {} ", orgCd, cpeatsStore.getBizNo(), cpeatsStore.getStoreId());
                    DeliveryM0001RequestDto requestDto = buildCommonRequestDto(mallCd, deliveryLoginInfoResponseDto, cpeatsStore.getStoreId(), "");
                    processRequest(deliveryLoginInfoResponseDto, requestDto, orgCd);
                }
            } else {
                DeliveryM0001RequestDto requestDto = buildCommonRequestDto(mallCd, deliveryLoginInfoResponseDto, "", deliveryLoginInfoResponseDto.getCustomer().getBizNo());
                processRequest(deliveryLoginInfoResponseDto, requestDto, orgCd);
            }
        }
    }

    public DeliveryM0001RequestDto buildCommonRequestDto(MallCd mallCd, DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto, String storeId, String bizNo) {
        return DeliveryM0001RequestDto.builder()
                .mallCd(mallCd)
                .userId(deliveryLoginInfoResponseDto.getLoginId())
                .userPw(deliveryLoginInfoResponseDto.getLoginPw())
                .dateFrom(LocalDate.now().getYear() + "0101")
                .dateTo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .storeId(storeId)
                .bizNo(bizNo)
                .build();
    }

    public void processRequest(DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto, DeliveryM0001RequestDto requestDto, OrgCd orgCd) {
        DeliveryM0001ResponseDto m0001 = apiScrapDeliveryM000X.getM0001(requestDto);
        if ("Y".equals(m0001.getErrYn()) || "Y".equals(m0001.getOutM0001().getErrYn())) {
            log.warn("m0001.getErrMsg : {} ", m0001.getErrMsg());
            deliveryFailService.save(deliveryLoginInfoResponseDto, m0001.getErrMsg().isEmpty() ? m0001.getOutM0001().getErrMsg() : m0001.getErrMsg(), SvcCd.M0001, orgCd);
        } else {
            saveResponse(deliveryLoginInfoResponseDto, m0001, orgCd, requestDto.getStoreId());
        }
    }

    public void saveResponse(DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto, DeliveryM0001ResponseDto m0001, OrgCd orgCd, String storeId) {
        if (OrgCd.yogiyo == orgCd) {
            yogiyoVatService.save(deliveryLoginInfoResponseDto, m0001);
        } else if (OrgCd.cpeats == orgCd) {
            // 쿠팡이츠 storeId 필요
            cpeatsVatService.save(deliveryLoginInfoResponseDto, m0001, storeId);
        }
    }
}
