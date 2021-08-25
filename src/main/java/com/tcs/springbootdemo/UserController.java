package com.tcs.springbootdemo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController { // spring

	@Autowired // DI
	IUserService userService;

	@GetMapping
	private Iterable<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/{id}")
	private Optional<User> getUser(@PathVariable("id") Integer id) {
		return userService.getUser(id);
	}

	@ExceptionHandler(value = { UserNotFoundException.class, IllegalStateException.class, EmptyResultDataAccessException.class })
	public ResponseEntity<User> exception(RuntimeException runtimeException) {
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}

	@PostMapping
	private void saveUser(@RequestBody User user) {
		userService.save(user);
		System.out.println(user.getFirstName());

	}
}
