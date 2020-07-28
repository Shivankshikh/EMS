package com.shivankshi.emscrud.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivankshi.emscrud.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import com.shivankshi.emscrud.entity.Employee;

@RestController
@RequestMapping("/api") //base mapping
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	//@ApiOperation(value = "", authorizations = { @Authorization(value = "BasicAuth") })
	
	@GetMapping("/employees")
	@ApiOperation(value ="Get List of Existing Employees",authorizations = {@Authorization(value="BasicAuth")} )
	public List<Employee> getEmployees()
	{
			return employeeService.getEmployees();
	}
	
	
	@GetMapping("/employees/{empId}")
	@ApiOperation(value ="Get Employee by id", notes = "Provide an id to look up specific Employee from the Database", response = Employee.class)
	public Employee getEmployee(@PathVariable int empId)
	{
		Employee theEmployee =employeeService.findById(empId);
		if(theEmployee==null)
		{
			throw new RuntimeException("Employee not found with id- "+empId);
		}
		return theEmployee;
	}
	
	@PostMapping("/employees")
	@ApiOperation(value ="Add a new Employee", notes = "Provide an Employee", response = Employee.class, authorizations = {@Authorization(value="BasicAuth")})
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		//RequestBody for binding json data with Employee object
		
		//if explicitly id is passed, set it to zero to force a save of new item instead of update
		
		theEmployee.setId(0);
		
		return employeeService.addEmployee(theEmployee);
	}
	


	
	
	@PutMapping("/employees/{empId}")
	@ApiOperation(value ="Update Existing Employee", notes = "Only salary or designation can be updated", response = Employee.class ,authorizations = {@Authorization(value="BasicAuth")})
	public Employee updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee theEmployee)
	{
		
		return employeeService.updateEmployee(empId,theEmployee);
	}
	
	@DeleteMapping("/employees/{empId}")
	@ApiOperation(value ="Delete an Existing Employee", notes = "Provide an employee Id to be deleted" ,authorizations = {@Authorization(value="BasicAuth")})
	public String deleteEmployee(@PathVariable int empId)
	{
		Employee tEmployee= employeeService.findById(empId);
		if(tEmployee==null)
		{
			throw new RuntimeException("Employee not found");
		}
		
		employeeService.deleteById(empId);
		
		return "employee deleted with id "+empId;
	}
	

	

	
	
}
