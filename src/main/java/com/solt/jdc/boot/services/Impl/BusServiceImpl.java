package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.repositories.BusRepository;
import com.solt.jdc.boot.services.BusService;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepository busRepository;
	@Override
	public void createBus(Bus bus) {
		busRepository.saveAndFlush(bus);
	}

	@Override
	public List<Bus> getAllBus() {
		return busRepository.findAll();
	}

	@Override
	public void updateBus(Bus bus) {
		busRepository.saveAndFlush(bus);
	}

	@Override
	public void deleteBus(int busId) {
		busRepository.delete(busId);
	}

	@Override
	public Bus findById(int busId) {
		return busRepository.findById(busId);
	}
	
}
