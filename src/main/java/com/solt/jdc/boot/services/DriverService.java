package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Driver;

public interface DriverService {

	public Iterable<Driver> findAll();
	public Driver find(int id);
	public void save(Driver account);
	public void delete(Driver account);
	public void update(Driver account);
}
