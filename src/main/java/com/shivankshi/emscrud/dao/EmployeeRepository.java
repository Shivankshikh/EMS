package com.shivankshi.emscrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivankshi.emscrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
