package com.shivankshi.emscrud.service;

import java.util.List;

import com.shivankshi.emscrud.entity.Employee;

public interface EmployeeService {
	
public List<Employee> getEmployees();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);

}
