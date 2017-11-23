package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Cities;
import com.solt.jdc.boot.repositories.CitiesRepository;
import com.solt.jdc.boot.services.CitiesService;

@Service
public class CitiesServiceImpl implements CitiesService {
	@Autowired
	private CitiesRepository citiesRepository;

	@Override
	public void createCity(Cities cities) {
		citiesRepository.saveAndFlush(cities);
	}

	@Override
	public List<Cities> getAllCities() {
		return citiesRepository.findAll();
	}

	@Override
	public Cities findById(int citiesId) {
		return citiesRepository.findOne(citiesId);
	}

	@Override
	public void deleteCities(int citiesId) {
		citiesRepository.delete(citiesId);
	}

	@Override
	public void updateCities(Cities cities) {
		citiesRepository.saveAndFlush(cities);
	}

}
