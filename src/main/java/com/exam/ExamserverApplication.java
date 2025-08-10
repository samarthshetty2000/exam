package com.exam;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
@SpringBootApplication
public class ExamserverApplication {
	
	
	public static void main(String[] args) {
		System.out.println("DB URL from env: " + System.getenv("DB_URL"));
		System.out.println("DB user from env: " + System.getenv("DB_PASSWORD")+ System.getenv("DB_PASSWORD").length());
		System.out.println("DB pass from env: " + System.getenv("DATA_USER")+ System.getenv("DATA_USER").length() + Objects.equals(System.getenv("DATA_USER"), "samarth"));
		SpringApplication.run(ExamserverApplication.class, args);
		

	}

	


}
