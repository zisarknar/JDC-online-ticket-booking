package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Services;
import com.solt.jdc.boot.repositories.ServicesRepository;
import com.solt.jdc.boot.services.ServicesService;

@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	private ServicesRepository servicesRepository;
	@Override
	public void addService(Services services) {
		servicesRepository.saveAndFlush(services);
	}

	@Override
	public List<Services> getAllServices() {
		return servicesRepository.findAll();
	}

	@Override
	public Services findById(int servicId) {
		return servicesRepository.getOne(servicId);
	}

	@Override
	public void updateService(Services services) {
		servicesRepository.saveAndFlush(services);
	}

	@Override
	public void deleteService(int serviceId) {
		servicesRepository.delete(serviceId);
	}

}
