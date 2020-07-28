package com.shivankshi.emscrud.service;

import java.util.List;

import com.shivankshi.emscrud.entity.Employee;

public interface EmployeeService {
	
public List<Employee> getEmployees();
	
	public Employee findById(int theId);
	
	public Employee addEmployee(Employee employee);
	
	//public Employee updateEmployee(int empId, int salary, String designation);
	
	public void deleteById(int theId);
	
	public Employee updateEmployee(int empId,Employee employee);
	
	

}
