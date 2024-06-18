package com.rn.batch.delivery.yogiyo.service;

import com.rn.batch.api.code.MallCd;
import com.rn.batch.api.code.OrgCd;
import com.rn.batch.api.dto.DeliveryM0001RequestDto;
import com.rn.batch.api.dto.DeliveryM0001ResponseDto;
import com.rn.batch.api.scrap.ApiScrapDeliveryM000X;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;
import com.rn.batch.delivery.customer.repository.CustomerRepository;
import com.rn.batch.delivery.yogiyo.entity.YogiyoVatSales;
import com.rn.batch.delivery.yogiyo.repository.YogiyoVatSalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class YogiyoService {

    private final YogiyoVatSalesRepository yogiyoVatSalesRepository;
    private final ApiScrapDeliveryM000X apiScrapDeliveryM000X;
    private final CustomerRepository customerRepository;


    @Transactional
    public void vatHist(MallCd mallCd) {

        List<DeliveryLoginInfoResponseDto> baedalLoginInfo = customerRepository.findDeliveryLoginInfo("", OrgCd.yogiyo);

        log.info("[{}] Scraping start!!! ", OrgCd.yogiyo);

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

            log.info("m0001 : {}", m0001);

            for (DeliveryM0001ResponseDto.VatSales vatSales : m0001.getOutM0001().getList_1()) {
                String[] calcChk = vatSales.getSalesDt().split("-");
                int num = Integer.parseInt(calcChk[1]);
                int quarter = (num - 1) / 3 + 1;

                YogiyoVatSales yogiyoVatSales = YogiyoVatSales.builder()
                        .bizUnitSeq(deliveryLoginInfoResponseDto.getBizUnitSeq())
                        .bizNo(deliveryLoginInfoResponseDto.getCustomer().getBizNo())
                        .calcYear(calcChk[0])
                        .calcMonth(calcChk[1])
                        .quarter(String.valueOf(quarter))
                        .cnt(vatSales.getSalesCnt() == null || vatSales.getSalesCnt().isEmpty() ? 0L : Long.valueOf(vatSales.getSalesCnt()))
                        .onlineCard(vatSales.getOnlineCredit() == null || vatSales.getOnlineCredit().isEmpty() ? 0L : Long.valueOf(vatSales.getOnlineCredit()))
                        .onlineHp(vatSales.getOnlinePhone() == null || vatSales.getOnlinePhone().isEmpty() ? 0L : Long.valueOf(vatSales.getOnlinePhone()))
                        .offlineCard(vatSales.getOfflineCredit() == null || vatSales.getOfflineCredit().isEmpty() ? 0L : Long.valueOf(vatSales.getOfflineCredit()))
                        .offlineCash(vatSales.getOfflineCash() == null || vatSales.getOfflineCash().isEmpty() ? 0L : Long.valueOf(vatSales.getOfflineCash()))
                        .onlineEtc(vatSales.getOnlineEtc() == null || vatSales.getOnlineEtc().isEmpty() ? 0L : Long.valueOf(vatSales.getOnlineEtc()))
                        .sumRestDiscount(vatSales.getSelfDisc() == null || vatSales.getSelfDisc().isEmpty() ? 0L : Long.valueOf(vatSales.getSelfDisc()))
                        .figures(vatSales.getPchAmt() == null || vatSales.getPchAmt().isEmpty() ? 0L : Long.valueOf(vatSales.getPchAmt()))
                        .build();
                yogiyoVatSalesRepository.save(yogiyoVatSales);
            }
        }
    }
}
