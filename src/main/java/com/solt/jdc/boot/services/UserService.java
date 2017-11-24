package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.User;

public interface UserService {


	public Iterable<User> findAll();
	public User find(int id);
	public void save(User user);
	public void delete(User user);
	public void update(User user);
}
