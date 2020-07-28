package com.shivankshi.emscrud;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


public class EmscrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmscrudApplication.class, args);
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
