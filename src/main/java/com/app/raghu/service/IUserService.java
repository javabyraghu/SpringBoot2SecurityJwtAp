package com.app.raghu.service;

import com.app.raghu.entity.User;

public interface IUserService {

	public Integer saveUser(User user);
	public User findByUsername(String username);
}
