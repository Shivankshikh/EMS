package com.shivankshi.emscrud.rest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivankshi.emscrud.entity.Employee;
import com.shivankshi.emscrud.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)

public class EmployeeRestSecurityTest {

	@Autowired
	public TestRestTemplate testRestTemplate;
	
	@MockBean
	public EmployeeService employeeService;
	
	@Before
	public void init()
	{
		Employee mockEmployee1=new Employee();
		mockEmployee1.setId(1);
		mockEmployee1.setFirstName("Shivankshi");
		mockEmployee1.setLastName("Khandelwal");
		mockEmployee1.setEmail("abc@gmail.com");
		mockEmployee1.setDesignation("Trainee Analyst");
		mockEmployee1.setSalary(500000);
		
		when(employeeService.findById(1)).thenReturn(mockEmployee1);
		
		
	}
	

	
	
	@Test
	public void find_login_ok() throws Exception
	{

		
		ResponseEntity<String> responseEntity=testRestTemplate
				.withBasicAuth("user", "password")
				.getForEntity("/api/employees", String.class);
		
		
		
		// assertEquals(MediaType.APPLICATION_JSON_UTF8, responseEntity.getHeaders().getContentType());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	
		}

	
	@Test
	public void find_nologin_401() throws Exception
	{
		
		ResponseEntity<String> responseEntity=testRestTemplate
				.getForEntity("/api/employees/1", String.class);
		
		//assertEquals(MediaType.APPLICATION_JSON_UTF8, outputInJsonString.getHeaders().getContentType());
		assertEquals(HttpStatus.UNAUTHORIZED,responseEntity.getStatusCode());
		
		
		
		
	}
	
	
	public String mapToJson(Object object) throws JsonProcessingException
	{
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
