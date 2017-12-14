package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.repositories.StationRepository;
import com.solt.jdc.boot.services.StationService;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository stationRepository;

	@Override
	public void addStation(Station station) {
		stationRepository.saveAndFlush(station);
	}

	@Override
	public List<Station> getAllStations() {
		return stationRepository.findAll();
	}

	@Override
	public Station findById(int stationId) {
		return stationRepository.getOne(stationId);
	}

	@Override
	public void deleteStation(int stationId) {
		stationRepository.delete(stationId);
	}

	@Override
	public void updateStation(Station station) {
		stationRepository.saveAndFlush(station);
	}

	@Override
	public List<Station> findStationByCity(String city) {
		return stationRepository.findStationByCity(city);
	}

	@Override
	public List<Station> findStationByName(String stationName) {
		return stationRepository.findStationByName(stationName);
	}

	@Override
	public List<Station> findStationByFilter(String busCode, String cityName) {
		return stationRepository.findStationByFilter(busCode, cityName);
	}

}
