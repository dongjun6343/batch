package com.rn.batch.delivery.cpeats.repository;


import com.rn.batch.delivery.cpeats.entity.CpeatsVatSales;
import com.rn.batch.delivery.cpeats.entity.CpeatsVatSalesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpeatsVatSalesRepository extends JpaRepository<CpeatsVatSales, CpeatsVatSalesId> {
}
