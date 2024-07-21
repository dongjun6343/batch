package com.rn.batch.delivery.cpeats.repository;


import com.rn.batch.delivery.cpeats.entity.CpeatsStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpeatsStoreRepository extends JpaRepository<CpeatsStore, Long> {
    List<CpeatsStore> findByBizUnitSeqAndBizNo(String bizUnitSeq, String bizNo);
}
