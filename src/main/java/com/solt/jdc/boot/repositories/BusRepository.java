package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solt.jdc.boot.domains.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}
