package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.BusType;
import com.solt.jdc.boot.repositories.BusTypeRepository;
import com.solt.jdc.boot.services.BusTypeService;

@Service
public class BusTypeServiceImpl implements BusTypeService {

	@Autowired
	private BusTypeRepository busTypeRepository;

	@Override
	public void addBusType(BusType busType) {
		busTypeRepository.saveAndFlush(busType);
	}

	@Override
	public List<BusType> getAllBusTypes() {
		
		return busTypeRepository.findAll();
	}

	@Override
	public BusType findById(int busTypeId) {
		return busTypeRepository.getOne(busTypeId);
	}

	@Override
	public void updateBusType(BusType busType) {
		busTypeRepository.saveAndFlush(busType);
	}

	@Override
	public void deleteBusType(BusType busType) {
		busTypeRepository.delete(busType);

	}

}
