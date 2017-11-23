package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.User;

public interface UserService {


	public Iterable<User> findAll();
	public User find(int id);
	public void save(User user);
	public void delete(User user);
}
