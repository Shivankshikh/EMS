package com.shivankshi.emscrud.dao;


import org.springframework.data.repository.CrudRepository;

import com.shivankshi.emscrud.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	

}
