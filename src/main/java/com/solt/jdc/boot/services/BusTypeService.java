package com.solt.jdc.boot.services;

import java.util.List;

import com.solt.jdc.boot.domains.BusType;

public interface BusTypeService {
	void addBusType(BusType busType);

	List<BusType> getAllBusTypes();

	BusType findById(int busTypeId);

	void updateBusType(BusType busType);

	void deleteBusType(BusType busType);
}
