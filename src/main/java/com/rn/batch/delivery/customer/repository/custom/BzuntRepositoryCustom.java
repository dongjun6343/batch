package com.rn.batch.delivery.customer.repository.custom;

import com.rn.batch.api.code.OrgCd;

public interface BzuntRepositoryCustom {
    String findMaxBizUnitSeqInfo(String bizNo, OrgCd orgCd);
}
