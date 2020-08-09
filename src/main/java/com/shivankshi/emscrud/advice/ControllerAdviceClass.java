package com.shivankshi.emscrud.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shivankshi.emscrud.CustomException.EmployeeNotFoundException;
import com.shivankshi.emscrud.entity.EmployeeErrorResponse;

@ControllerAdvice

public class ControllerAdviceClass {

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(RuntimeException exc) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc) {
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

}
