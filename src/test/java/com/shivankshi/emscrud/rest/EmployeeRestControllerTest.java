package com.shivankshi.emscrud.rest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivankshi.emscrud.entity.Employee;
import com.shivankshi.emscrud.service.EmployeeService;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@RunWith(SpringRunner.class)

@AutoConfigureMockMvc(addFilters=false)

@WebMvcTest(value=EmployeeRestController.class)
public class EmployeeRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
	private EmployeeService employeeService;
	
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
	@WithMockUser("/abc")
	@Test
	public void testGetEmployees() throws Exception
	{
		Employee mockEmployee1=new Employee();
		mockEmployee1.setId(1);
		mockEmployee1.setFirstName("Shivankshi");
		mockEmployee1.setLastName("Khandelwal");
		mockEmployee1.setEmail("abc@gmail.com");
		mockEmployee1.setDesignation("Trainee Analyst");
		mockEmployee1.setSalary(500000);
		
		Employee mockEmployee2=new Employee();
		mockEmployee2.setId(1);
		mockEmployee2.setFirstName("Shivankshi");
		mockEmployee2.setLastName("Khandelwal");
		mockEmployee2.setEmail("abc@gmail.com");
		mockEmployee2.setDesignation("Trainee Analyst");
		mockEmployee2.setSalary(500000);
		
		List<Employee> employeeList=new ArrayList<Employee>();
		employeeList.add(mockEmployee1);
		employeeList.add(mockEmployee2);
			
		Mockito.when(employeeService.getEmployees()).thenReturn(employeeList);
		
		String URI="/api/employees";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		String expectedJson=this.mapToJson(employeeList);
		String outputInJson=result.getResponse().getContentAsString();
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		}

	@WithMockUser("/abc")
	@Test
	public void testGetEmployee() throws Exception
	{
		Employee mockEmployee1=new Employee();
		mockEmployee1.setId(1);
		mockEmployee1.setFirstName("Shivankshi");
		mockEmployee1.setLastName("Khandelwal");
		mockEmployee1.setEmail("abc@gmail.com");
		mockEmployee1.setDesignation("Trainee Analyst");
		mockEmployee1.setSalary(500000);
		
		Mockito.when(employeeService.findById(Mockito.anyInt())).thenReturn(mockEmployee1);
		
		String URI="/api/employees/1";
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		
		String expectedJSON=this.mapToJson(mockEmployee1);
		
		String outputInJson=result.getResponse().getContentAsString();
		
		assertThat(outputInJson).isEqualTo(expectedJSON);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		
	}

	
	@WithMockUser("/abc")
	@Test
	public void testAddEmployee() throws Exception
	{
		Employee mockEmployee1=new Employee();
		mockEmployee1.setId(1);
		mockEmployee1.setFirstName("Shivankshi");
		mockEmployee1.setLastName("Khandelwal");
		mockEmployee1.setEmail("abc@gmail.com");
		mockEmployee1.setDesignation("Trainee Analyst");
		mockEmployee1.setSalary(500000);
		
		String inputInJsonString=this.mapToJson(mockEmployee1);
		String URI="/api/employees";
		Mockito.when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee1);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJsonString)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
		        .andExpect(status().isOk()).andReturn();
		
		MockHttpServletResponse response=result.getResponse();
		
		String outputInJsonString=response.getContentAsString();
		System.out.println(outputInJsonString);
		assertThat(outputInJsonString).isEqualTo(inputInJsonString);
		//assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@WithMockUser("/abc")
	@Test
	public void TestUpdateEmployee() throws Exception
	{
		Employee mockEmployee1=new Employee();
		mockEmployee1.setId(1);
		mockEmployee1.setFirstName("Shivankshi");
		mockEmployee1.setLastName("Khandelwal");
		mockEmployee1.setEmail("abc@gmail.com");
		mockEmployee1.setDesignation("Trainee Analyst");
		mockEmployee1.setSalary(500000);
		
		String inputInJsonString=this.mapToJson(mockEmployee1);
		String URI="/api/employees/1";
		
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJsonString)
				.contentType(MediaType.APPLICATION_JSON);
		

		
		
		MvcResult result=mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
		        .andExpect(status().isOk()).andReturn();
		int status=result.getResponse().getStatus();
		assertEquals(200, status);
		}
	
	

	
	@WithMockUser("/abc")
	@Test
	public void testDeleteEmployee() throws Exception
	{
		String URI="/api/employees/1";
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.delete(URI)).andReturn();
		
		
		int status=result.getResponse().getStatus();
		
		assertEquals(200, status);
		
		String contentString=result.getResponse().getContentAsString();
		assertEquals(contentString, "employee deleted with id 1");
		
		
		
	}

	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
