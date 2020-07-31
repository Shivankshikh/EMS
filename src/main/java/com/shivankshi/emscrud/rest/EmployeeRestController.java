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

import com.shivankshi.emscrud.entity.Employee;
import com.shivankshi.emscrud.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/employees/{empId}")
	public Employee getEmployee(@PathVariable int empId) {
		Employee theEmployee = employeeService.findById(empId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee not found with id- " + empId);
		}
		return theEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		theEmployee.setId(0);

		return employeeService.addEmployee(theEmployee);
	}

	@PutMapping("/employees/{empId}")
	public Employee updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee theEmployee) {

		return employeeService.updateEmployee(empId, theEmployee);
	}

	@DeleteMapping("/employees/{empId}")
	public String deleteEmployee(@PathVariable int empId) {
		Employee tEmployee = employeeService.findById(empId);
		if (tEmployee == null) {
			throw new RuntimeException("Employee not found");
		}

		employeeService.deleteById(empId);

		return "employee deleted with id " + empId;
	}

}