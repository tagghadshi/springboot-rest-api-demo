package com.tcs.springbootdemo;

import java.util.Optional;

public interface IUserService {
	void save(User user);

	Iterable<User> getAllUser();

	Optional<User> getUser(Integer id);

	void deleteUser(Integer id);
}
