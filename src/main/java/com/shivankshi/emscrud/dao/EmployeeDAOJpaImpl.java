package com.shivankshi.emscrud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shivankshi.emscrud.entity.Employee;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getEmployees() {
		TypedQuery<Employee> theQuery=entityManager.createQuery("from Employee", Employee.class);
		
		List<Employee> employees=theQuery.getResultList();
		return employees;
	}

	

	@Override
	public Employee findById(int theId) {
		Employee employee=entityManager.find(Employee.class, theId);
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//save or update the employee
		Employee dbEmployee=entityManager.merge(theEmployee);
		
		//update with id from db
		theEmployee.setId(dbEmployee.getId());
		
	}

	@Override
	public void deleteById(int theId) {
		Query theQuery=entityManager.createQuery("delete from Employee where id=:empId");
		theQuery.setParameter("empId", theId);
		theQuery.executeUpdate();
		
	}
	

}
