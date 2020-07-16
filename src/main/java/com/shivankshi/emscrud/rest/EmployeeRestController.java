package com.shivankshi.emscrud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivankshi.emscrud.service.EmployeeService;
import com.shivankshi.emscrud.entity.Employee;

@RestController
@RequestMapping("/api") //base mapping
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	
	public List<Employee> getEmployees()
	{
			return employeeService.getEmployees();
	}
	
	
	@GetMapping("/employees/{empId}")
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
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		//RequestBody for binding json data with Employee object
		
		//if explicitly id is passed, set it to zero to force a save of new item instead of update
		
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	
	@PutMapping("/employees")
	
	public Employee updateEmployee(@RequestBody Employee theEmployee)
	{
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	@DeleteMapping("/employees/{empId}")
	
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
