package com.test.service;

import org.springframework.stereotype.Service;

import com.test.model.User;

@Service
public class UserService {
	public User getByEmail(String loginName)
	{
		User u=new User();
		u.setUserName("chenyun");
		u.setPhone("18910858107");
		return u;
	}
	
	public User create(User user)
	{
		return user;
	}
	
}
