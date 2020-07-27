package com.shivankshi.emscrud.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shivankshi.emscrud.dao.UserRepository;
import com.shivankshi.emscrud.entity.User;
import com.shivankshi.emscrud.principal.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=repo.findByUsername(username);
		
		return new UserPrincipal(user);
				
	
	}
	
	
	
}
