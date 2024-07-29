package com.rn.batch.delivery.cpeats.repository;


import com.rn.batch.delivery.cpeats.entity.CpeatsStore;
import com.rn.batch.delivery.cpeats.repository.custom.CpeatsStoreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CpeatsStoreRepository extends JpaRepository<CpeatsStore, Long>, CpeatsStoreRepositoryCustom {

    List<CpeatsStore> findByBizUnitSeqAndBizNo(String bizUnitSeq, String bizNo);
    CpeatsStore findByBizUnitSeqAndBizNoAndStoreId(String bizUnitSeq, String bizNo, String storeId);
}
