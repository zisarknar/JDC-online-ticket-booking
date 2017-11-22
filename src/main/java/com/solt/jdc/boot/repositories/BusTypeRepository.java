package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solt.jdc.boot.domains.BusType;

public interface BusTypeRepository extends JpaRepository<BusType, Integer> {

}
