package com.solt.jdc.boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solt.jdc.boot.domains.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {
	@Query("SELECT s FROM Station s where s.address.cities.name=?1")
	List<Station> findStationByCity(String cityName);
	
	@Query("SELECT s FROM Station s where s.name=?1")
	List<Station> findStationByName(String stationName);
	
	@Query("SELECT s FROM Station s where s.name=?1 AND s.address.cities.name=?2")
	List<Station> findStationByFilter(String stationName,String cityName);
}
