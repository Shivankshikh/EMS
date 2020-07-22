package com.shivankshi.emscrud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2


public class EmscrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmscrudApplication.class, args);
	}
	@Bean
	public Docket swaggerConfiguration()
	{
		List<SecurityScheme> schemeList = new ArrayList<>();
	    schemeList.add(new BasicAuth("BasicAuth"));
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/employees/**"))
				.apis(RequestHandlerSelectors.basePackage("com.shivankshi"))
				.build()
				.apiInfo(apiDetails())
				.securitySchemes(schemeList).securityContexts(Arrays.asList(securityContext()));
		
		
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo("Employee Management System API Documentation",
				"Sample Api for EMS", 
				"1.0",
				"Free to use", 
				new springfox.documentation.service.Contact("Shivankshi Khandelwal","http://springio","shivankshi.khandelwal@wisse.com"),
				"API License",
				"\"http://springio\"",
				Collections.emptyList());
	}
	
	
	private SecurityContext securityContext()
	{
	    return SecurityContext.builder().forPaths(PathSelectors.any()).build();
	 }
	
	@Bean
	  public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder().useBasicAuthenticationWithAccessCodeGrant(true).build();
	  }
	
	
//	@Bean
//	  public Docket productApi() {
//	    List<SecurityScheme> schemeList = new ArrayList<>();
//	    schemeList.add(new BasicAuth("BasicAuth"));
//	    ApiKey apiKey = new ApiKey("JWT", "Authorization", "header");
//	    schemeList.add(apiKey);
//	    return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(CurrentUser.class).select().apis(RequestHandlerSelectors.basePackage("systems.justinvest.rest.controller"))
//	        .paths(regex("/api/j/v2/.*")).build().apiInfo(metaData()).securitySchemes
}
