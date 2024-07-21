package com.rn.batch.delivery.cpeats.repository;


import com.rn.batch.delivery.cpeats.entity.CpeatsStore;
import com.rn.batch.delivery.cpeats.entity.CpeatsVatSales;
import com.rn.batch.delivery.cpeats.entity.CpeatsVatSalesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CpeatsStoreRepository extends JpaRepository<CpeatsStore, Long> {
    List<CpeatsStore> findByBizNo(String bizNo);
}
