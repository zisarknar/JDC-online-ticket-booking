package com.solt.jdc.boot.services;

import java.util.List;

import com.solt.jdc.boot.domains.Cities;

public interface CitiesService {
	void createCity(Cities cities);

	List<Cities> getAllCities();

	Cities findById(int citiesId);

	void deleteCities(int citiesId);

	void updateCities(Cities cities);
}
