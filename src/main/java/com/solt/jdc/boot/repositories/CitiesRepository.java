package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solt.jdc.boot.domains.Cities;

public interface CitiesRepository extends JpaRepository<Cities, Integer> {

}
