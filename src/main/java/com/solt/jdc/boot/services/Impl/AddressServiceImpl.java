package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Address;
import com.solt.jdc.boot.repositories.AddressRepository;
import com.solt.jdc.boot.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void addAddress(Address address) {
		addressRepository.saveAndFlush(address);
	}

	@Override
	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}

	@Override
	public Address findById(int addressId) {
		return addressRepository.getOne(addressId);
	}

	@Override
	public void deleteAddress(int addressId) {
		addressRepository.delete(addressId);
	}

	@Override
	public void updateAddress(Address address) {
		addressRepository.saveAndFlush(address);
	}
	
}
