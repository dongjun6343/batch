package com.rn.batch.delivery.customer.repository;


import com.rn.batch.delivery.customer.entity.Bzunt;
import com.rn.batch.delivery.customer.repository.custom.BzuntRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BzuntRepository extends JpaRepository<Bzunt, String>, BzuntRepositoryCustom {
}
