package com.rn.batch.delivery.customer.repository;

import com.rn.batch.delivery.customer.entity.SchScrapErrHist;
import com.rn.batch.delivery.customer.entity.SchScrapErrHistId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchScrapErrHistRepository extends JpaRepository<SchScrapErrHist, SchScrapErrHistId> {
}
