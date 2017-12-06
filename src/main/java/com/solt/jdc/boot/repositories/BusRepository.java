package com.solt.jdc.boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.BusType;

public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus findById(int id);

    List<Bus> findByBusType(BusType busType);
}
