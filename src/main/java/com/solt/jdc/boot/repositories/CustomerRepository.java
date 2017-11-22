package com.solt.jdc.boot.repositories;

import com.solt.jdc.boot.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
