package com.shivankshi.emscrud.service;

import java.util.List;

import com.shivankshi.emscrud.entity.User;
import com.shivankshi.emscrud.entity.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
