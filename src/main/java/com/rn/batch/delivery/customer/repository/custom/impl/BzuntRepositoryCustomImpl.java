package com.rn.batch.delivery.customer.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rn.batch.api.code.OrgCd;
import com.rn.batch.delivery.customer.repository.custom.BzuntRepositoryCustom;
import lombok.RequiredArgsConstructor;

import static com.rn.batch.delivery.customer.entity.QBzunt.bzunt;
import static com.rn.batch.delivery.customer.entity.QScrapOrg.scrapOrg;

@RequiredArgsConstructor
public class BzuntRepositoryCustomImpl implements BzuntRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public String findMaxBizUnitSeqInfo(String bizNo, OrgCd orgCd) {
        return jpaQueryFactory.select(scrapOrg.id.bizUnitSeq.max())
                .from(bzunt)
                .innerJoin(scrapOrg).on(bzunt.bizUnitSeq.eq(scrapOrg.id.bizUnitSeq))
                .where(
                        bzunt.bizNo.eq(bizNo),
                        scrapOrg.id.orgCd.eq(orgCd)
                )
                .fetchOne();
    }
}
