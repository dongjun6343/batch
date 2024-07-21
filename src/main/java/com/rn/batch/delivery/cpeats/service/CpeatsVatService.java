package com.rn.batch.delivery.cpeats.service;

import com.rn.batch.api.dto.DeliveryM0001ResponseDto;
import com.rn.batch.api.dto.DeliveryP0006ResponseDto;
import com.rn.batch.delivery.cpeats.entity.CpeatsStore;
import com.rn.batch.delivery.cpeats.entity.CpeatsVatSales;
import com.rn.batch.delivery.cpeats.repository.CpeatsStoreRepository;
import com.rn.batch.delivery.cpeats.repository.CpeatsVatSalesRepository;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpeatsVatService {

    private final CpeatsVatSalesRepository cpeatsVatSalesRepository;
    private final CpeatsStoreRepository cpeatsStoreRepository;

    @Transactional
    public void save(DeliveryLoginInfoResponseDto deliveryRequestDto, DeliveryM0001ResponseDto m0001, String storeId) {

        for (DeliveryM0001ResponseDto.CpeatsVat cpeatsVat : m0001.getOutM0001().getList()) {
            CpeatsVatSales cpeatsVatSales = CpeatsVatSales.builder()
                    .bizUnitSeq(deliveryRequestDto.getBizUnitSeq())
                    .orderNo(deliveryRequestDto.getCustomer().getBizNo()) // bizNo로 변경
                    .storeId(storeId)
                    .recognitionDate(cpeatsVat.getSelectDt())
                    .recogDt(cpeatsVat.getSelectDt())
                    .creditPayAmount(Long.valueOf(cpeatsVat.getCreditAmt()))
                    .cashPayAmount(Long.valueOf(cpeatsVat.getCashAmt()))
                    .etcPayAmount(Long.valueOf(cpeatsVat.getEtcAmt()))
                    .stDiscPayAmount(Long.valueOf(cpeatsVat.getDiscountAmt())) // 상점부담할인금액
                    .creditRefund(0L)
                    .cashRefund(0L)
                    .etcRefund(Long.valueOf(cpeatsVat.getDspsblAmt())) // 컵보증금 - 기타할인금액에 추가
                    .stDiscRefund(0L)
                    .build();
            cpeatsVatSalesRepository.save(cpeatsVatSales);
        }
    }

    @Transactional(readOnly = true)
    public List<CpeatsStore> findStoreList(String bizNo) {
        List<CpeatsStore> storeList = cpeatsStoreRepository.findByBizNo(bizNo);
        return storeList;
    }

    @Transactional
    public void saveStoreList(DeliveryP0006ResponseDto p0006) {
        for (DeliveryP0006ResponseDto.Store store : p0006.getOutP0006().getStoreList()) {
            CpeatsStore cpeatsStore = CpeatsStore.builder()
                    .bizNo(store.getBizNo())
                    .storeId(store.getStoreId())
                    .storeName(store.getStoreName())
                    .repName(store.getRepName())
                    .build();
            cpeatsStoreRepository.save(cpeatsStore);
        }
    }
}
