package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.User;

public interface UserService {

	 Iterable<User> findAll();
	 User find(int id);
	 void save(User user);
	 void delete(User user);
	 void update(User user);
	 long getCount();
	 User registerNewUser(User user);
}
