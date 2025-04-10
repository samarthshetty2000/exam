package com.exam.entity;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
	private String authority;

	public Authority(String authority) {
		
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}
