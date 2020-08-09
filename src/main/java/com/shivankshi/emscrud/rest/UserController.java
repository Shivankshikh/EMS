package com.shivankshi.emscrud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivankshi.emscrud.entity.User;
import com.shivankshi.emscrud.entity.UserDto;
import com.shivankshi.emscrud.service.UserService;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public List<User> listUser() {
		return userService.findAll();
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/users/{id}")
	public User getOne(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestBody UserDto user) {
		User newUser = userService.findOne(user.getUsername());
		if (newUser == null) {
			userService.save(user);
			return new ResponseEntity<String>("Successfully added", HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Already exists", HttpStatus.CONFLICT);
		}

	}

}
