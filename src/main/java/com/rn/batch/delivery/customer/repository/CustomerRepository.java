package com.rn.batch.delivery.customer.repository;


import com.rn.batch.delivery.customer.entity.Customer;
import com.rn.batch.delivery.customer.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>, CustomerRepositoryCustom {
}
