package com.solt.jdc.boot.services;

import java.util.List;

import com.solt.jdc.boot.domains.Services;

public interface ServicesService {
	void addService(Services services);

	List<Services> getAllServices();

	Services findById(int servicId);

	void updateService(Services services);

	void deleteService(int serviceId);
}
