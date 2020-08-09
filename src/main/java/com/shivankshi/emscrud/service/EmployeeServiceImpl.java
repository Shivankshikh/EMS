package com.shivankshi.emscrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivankshi.emscrud.CustomException.EmployeeNotFoundException;
import com.shivankshi.emscrud.dao.EmployeeRepository;
import com.shivankshi.emscrud.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {

		Optional<Employee> result = employeeRepository.findById(theId);
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new EmployeeNotFoundException("Employee does not exists");
		}
		return employee;
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(int empId, Employee employee) {
		Optional<Employee> employeeFromDb = employeeRepository.findById(empId);
		Employee employee2 = null;
		if (employeeFromDb.isPresent()) {
			employee2 = employeeFromDb.get();
			employee2.setDesignation(employee.getDesignation());
			employee2.setSalary(employee.getSalary());
		} else {
			throw new EmployeeNotFoundException("Employee id not found");

		}
		employeeRepository.save(employee2);
		return employee2;
	}

}