package com.rn.batch.delivery.yogiyo.service;

import com.rn.batch.api.dto.DeliveryM0001ResponseDto;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;
import com.rn.batch.delivery.yogiyo.entity.YogiyoVatSales;
import com.rn.batch.delivery.yogiyo.repository.YogiyoVatSalesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class YogiyoVatService {

    private final YogiyoVatSalesRepository yogiyoVatSalesRepository;

    @Transactional
    public void save(DeliveryLoginInfoResponseDto deliveryLoginInfoResponseDto, DeliveryM0001ResponseDto m0001) {

        String bizNo = deliveryLoginInfoResponseDto.getBizUnitSeq();
        String biz1 = bizNo.substring(0, 3);
        String biz2 = bizNo.substring(3, 5);
        String biz3 = bizNo.substring(5);
        String vatBizNo = String.join("-", Arrays.asList(biz1, biz2, biz3));

        for (DeliveryM0001ResponseDto.VatSales vatSales : m0001.getOutM0001().getList_1()) {
            String[] calcChk = vatSales.getSalesDt().split("-");
            int num = Integer.parseInt(calcChk[1]);
            int quarter = (num - 1) / 3 + 1;

            YogiyoVatSales yogiyoVatSales = YogiyoVatSales.builder()
                    .bizUnitSeq(deliveryLoginInfoResponseDto.getBizUnitSeq())
                    .bizNo(vatBizNo)
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
