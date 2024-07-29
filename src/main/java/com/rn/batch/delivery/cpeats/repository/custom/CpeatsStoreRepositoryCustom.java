package com.rn.batch.delivery.cpeats.repository.custom;

public interface CpeatsStoreRepositoryCustom {
    Boolean existsCpeatsStoreId(String bizUnitSeq, String bizNo, String storeId);
}
