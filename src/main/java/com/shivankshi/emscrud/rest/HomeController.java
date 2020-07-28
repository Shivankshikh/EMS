package com.shivankshi.emscrud.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	Logger logger=LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home()
	{
		logger.info("welcome in the EMS");
		logger.debug("debug level log");
	    logger.info("info level log");
	    logger.error("error level log");
	    logger.trace("In home Controller");
		return "Welcome in the Employee Management System Web Application";
	}

}
