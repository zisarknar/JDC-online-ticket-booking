package com.solt.jdc.boot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.solt.jdc.boot.domains.User;

public interface UserRepository extends  CrudRepository<User,Integer> {

}
