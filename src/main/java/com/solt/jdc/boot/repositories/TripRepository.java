package com.solt.jdc.boot.repositories;

import com.solt.jdc.boot.domains.Trip;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	
	@Query("SELECT t FROM trip t WHERE t.source=?1 AND t.destination=?2")
	List<Trip> findTripByFilter(String source,String destination);
}
