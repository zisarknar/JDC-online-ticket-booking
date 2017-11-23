package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solt.jdc.boot.domains.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {

}
