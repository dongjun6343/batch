package com.rn.batch.delivery.cpeats.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rn.batch.delivery.cpeats.repository.custom.CpeatsStoreRepositoryCustom;
import lombok.RequiredArgsConstructor;

import static com.rn.batch.delivery.cpeats.entity.QCpeatsStore.cpeatsStore;

@RequiredArgsConstructor
public class CpeatsStoreRepositoryCustomImpl implements CpeatsStoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean existsCpeatsStoreId(String bizUnitSeq, String bizNo, String storeId) {
        return jpaQueryFactory.select(cpeatsStore.id)
                .from(cpeatsStore)
                .where(
                        cpeatsStore.bizUnitSeq.eq(bizUnitSeq),
                        cpeatsStore.bizNo.eq(bizNo),
                        cpeatsStore.storeId.eq(storeId)
                ).fetchFirst() != null;
    }
}
