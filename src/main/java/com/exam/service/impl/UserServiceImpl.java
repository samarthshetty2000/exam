package com.exam.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


@Service
public class UserServiceImpl implements UserService{
	private static final Integer EXPIRE_MIN = 5;
	 private LoadingCache<String, Integer> otpCache=CacheBuilder.newBuilder()
             .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
             .build(new CacheLoader<String, Integer>() {
                 @Override
                 public Integer load(String s) throws Exception {
                     return 0;
                 }
             });;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User is already there");
			throw new Exception("User already present");
		}
		
		for(UserRole ur:userRoles) {
			roleRepository.save(ur.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		local=this.userRepository.save(user);
		return user;
	}

	@Override
	public User getUser(String username) throws Exception {
		User local=userRepository.findByUsername(username);
		if(local==null) {
			System.out.println("User not found");
			throw new Exception("User not Found");
		}
		return local;
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
		
	}

	@Override
	public int generateOtp(String userName) {

		   
		int otp=(int) ((Math.random())*9999);
		  otpCache.put(userName, otp);
		System.out.println(otp);
		return otp;
	}

	@Override
	public int verifyOtp(String userName) {
		
		  return otpCache.getIfPresent(userName);
	}
	
	
	
	
	

}
