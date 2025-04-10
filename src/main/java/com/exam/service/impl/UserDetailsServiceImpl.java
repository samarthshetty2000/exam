package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("User Not found");
			throw new UsernameNotFoundException("No User found !!");
		}
		
		return user;
	}
	

}
