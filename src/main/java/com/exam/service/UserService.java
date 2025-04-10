package com.exam.service;

import java.util.Set;

import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface UserService {
	
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	
	public User getUser(String username) throws Exception;
	
	
	public void deleteUser(Long id);
	
	public int generateOtp(String userName);
	
	public int verifyOtp(String userName);

}
