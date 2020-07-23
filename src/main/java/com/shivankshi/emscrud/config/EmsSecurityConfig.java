package com.shivankshi.emscrud.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class EmsSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf()
        .disable()
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .anyRequest()
        .authenticated();
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("user").password("{noop}password").roles("USER")
        .and()
        .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		
//
//		
//		http.httpBasic()
//		.and()
//		.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/api/employees/**").hasRole("USER")
//		.antMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
//		.antMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
//		.and()
//		.csrf().disable()
//		.formLogin().disable();
//	}
	
	

}
