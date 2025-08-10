package com.exam.controller;

import java.awt.Graphics;
import java.awt.PageAttributes.MediaType;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.READER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@RestController
@CrossOrigin("*")        
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/addimg")
	public ResponseEntity<byte[]> getimage(@RequestBody MultipartFile photo) throws IOException {
		 
	    byte[] a=photo.getBytes();
	    return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
                .body(a);	    
		
	}
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Set<UserRole> userRoles=new HashSet<>();
		Role role=new Role();
		 role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoles.add(userRole);

		return userService.createUser(user,userRoles);
		
	}
	
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) throws Exception {
		return userService.getUser(username);
	}
	
	
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
	
		userService.deleteUser(id);
	}
	
	
	
	@GetMapping("/forgot-password")
	public ResponseEntity<?> generateOtp(@RequestBody Map<String,String> mp){
		System.out.println(mp.get("email"));
		userService.generateOtp(mp.get("email"));
		
		return ResponseEntity.ok("Otp generated Successfully");
		
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verify(@RequestBody Map<String,Object> mp){
		System.out.println("Verification");
		
		System.out.println(mp.get("email"));
//		System.out.println(mp.get("otp"));
		int otp=userService.verifyOtp(((String) mp.get("email")));
		System.out.println(otp);
		int otp2=(int) mp.get("otp");
		System.out.println(otp2);
		System.out.println(otp==otp2);
		if(otp==otp2) {
			return ResponseEntity.ok("Success");
		}
		else
			return new ResponseEntity("Failed",HttpStatus.BAD_REQUEST);
		
		
	}

	
	
	

}
