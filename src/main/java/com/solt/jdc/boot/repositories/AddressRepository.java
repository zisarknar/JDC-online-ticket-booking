package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solt.jdc.boot.domains.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
