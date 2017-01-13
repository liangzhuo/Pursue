package com.liang.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.liang.model.User;

public interface UserRepository extends CrudRepository<User, String>{
	Page<User> findByAddress(String address, Pageable pageable);
}
