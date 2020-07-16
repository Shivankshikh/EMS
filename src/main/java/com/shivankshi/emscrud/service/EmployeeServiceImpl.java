package com.shivankshi.emscrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
