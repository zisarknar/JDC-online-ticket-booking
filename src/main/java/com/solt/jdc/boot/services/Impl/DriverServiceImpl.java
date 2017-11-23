package com.solt.jdc.boot.services.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Driver;
import com.solt.jdc.boot.repositories.DriverRepository;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Override
	public Iterable<Driver> findAll() {
		return  driverRepository.findAll();
	}

	@Override
	public Driver find(int id) {

			return driverRepository.findOne(id);
	}

	@Override
	public void save(Driver driver) {
	 driverRepository.save(driver);

	}

	@Override
	public void delete(Driver driver) {
		driverRepository.delete(driver);

	}

}
