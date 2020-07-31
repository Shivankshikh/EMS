package com.shivankshi.emscrud.entity;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {

	private String username;
    private String password;
    private int age;
    private int salary;
    //private Set<Role> roles;

}
