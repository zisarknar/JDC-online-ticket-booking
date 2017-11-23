package com.solt.jdc.boot.services;

import java.util.List;

import com.solt.jdc.boot.domains.Bus;

public interface BusService {
	void createBus(Bus bus);

	List<Bus> getAllBus();

	void updateBus(Bus bus);

	void deleteBus(int busId);
	
	Bus findById(int busId);
}
