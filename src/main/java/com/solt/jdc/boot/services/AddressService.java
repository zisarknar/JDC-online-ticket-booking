package com.solt.jdc.boot.services;

import java.util.List;

import com.solt.jdc.boot.domains.Address;

public interface AddressService {
	void addAddress(Address address);

	List<Address> getAllAddress();

	Address findById(int addressId);

	void deleteAddress(int addressId);

	void updateAddress(Address address);
}
