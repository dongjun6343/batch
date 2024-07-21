package com.rn.batch.delivery.customer.repository.custom.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rn.batch.api.code.OrgCd;
import com.rn.batch.delivery.customer.dto.DeliveryLoginInfoResponseDto;
import com.rn.batch.delivery.customer.dto.QDeliveryLoginInfoResponseDto;
import com.rn.batch.delivery.customer.repository.custom.CustomerRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.rn.batch.delivery.customer.entity.QBzunt.bzunt;
import static com.rn.batch.delivery.customer.entity.QCustomer.customer;
import static com.rn.batch.delivery.customer.entity.QScrapOrg.scrapOrg;

@RequiredArgsConstructor
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DeliveryLoginInfoResponseDto> findDeliveryLoginInfo(String custStatus, OrgCd orgCd) {
        return jpaQueryFactory.select(new QDeliveryLoginInfoResponseDto(
                        bzunt.bizUnitSeq,
                        scrapOrg.loginId,
                        scrapOrg.loginPw,
                        customer
                ))
                .distinct()
                .from(customer)
                .innerJoin(bzunt).on(customer.bizNo.eq(bzunt.bizNo))
                .innerJoin(scrapOrg).on(bzunt.bizUnitSeq.eq(scrapOrg.id.bizUnitSeq))
                .where(
                        getCustStatusCondition(custStatus),
                        scrapOrg.useYn.eq("Y"),
                        scrapOrg.id.orgCd.eq(orgCd),
                        customer.statusDetail.eq("D"), // D : 정상
                        customer.bizNo.length().eq(10),
                        scrapOrg.loginId.isNotNull(),
                        scrapOrg.loginPw.isNotNull(),
                        scrapOrg.loginPw.isNotEmpty()
                )
                .fetch();
    }

    private static BooleanExpression getCustStatusCondition(String custStatus) {
        if (!StringUtils.hasText(custStatus)) {
            return customer.custStatus.in("F", "G");
        }
        return customer.custStatus.eq(custStatus);
    }
}
