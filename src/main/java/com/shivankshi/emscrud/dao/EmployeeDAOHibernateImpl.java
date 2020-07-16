package com.shivankshi.emscrud.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.shivankshi.emscrud.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	private EntityManager entityManager; //automatically created by spring boot
	
	
	@Autowired      //using constructor injection
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	@Override
	//@Transactional  //handles transaction management so we don't have to start and commit transaction manually
	public List<Employee> getEmployees() {
		//get the current hibernate session
		
		Session currentSesion=entityManager.unwrap(Session.class);
		
		//create the query
		
		Query<Employee> theQuery=currentSesion.createQuery("from Employee", Employee.class);
		
		//execute query and get result list
		
		List<Employee> employees=theQuery.getResultList();
		//
		return employees;
	}


	@Override
	public Employee findById(int theId) {
		Session currentSession=entityManager.unwrap(Session.class);
		
		Employee theEmployee=currentSession.get(Employee.class,theId);
		
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {
		
		Session currentSession=entityManager.unwrap(Session.class);
		
		//if id=0 then save/add new employee
		//else update the existing one
		
		currentSession.saveOrUpdate(theEmployee);
		
	}


	@Override
	public void deleteById(int theId) {
		
		Session currentSession=entityManager.unwrap(Session.class);
		Query theQuery=currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
	}

	

	
}
