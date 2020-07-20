package com.shivankshi.emscrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.shivankshi.emscrud.dao.EmployeeRepository;
import com.shivankshi.emscrud.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		//bean id due to conflict in DAO implementation
		this.employeeRepository = employeeRepository;
	}
	
	
	
	//following service methods will delegate the calls to the DAO

	@Override
	public List<Employee> getEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee employee=null;
		if(result.isPresent())
		{
		employee=result.get();	
		}
		else {
			throw new RuntimeException("Employee id not found");
		}
		return employee;
	}

//	@Override
//	public Employee save(Employee theEmployee) {
//		return employeeRepository.save(theEmployee);
//
//	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}



@Override
public Employee addEmployee(Employee employee) {
	
	return employeeRepository.save(employee);
}



@Override
public Employee updateEmployee(int empId, int salary, String designation) {
	Optional<Employee> employeeFromDb=employeeRepository.findById(empId);
	Employee employee=null;
	if(employeeFromDb.isPresent())
	{
		employee=employeeFromDb.get();
		employee.setSalary(salary);
		employee.setDesignation(designation);
	}
	else {
		throw new RuntimeException("Employee id not found");
	}
	
	return employee;
}

}
