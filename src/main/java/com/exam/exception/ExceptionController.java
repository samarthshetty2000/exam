package com.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.util.ErrorResponse;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleDefaultException(Exception ex) {
		
	   System.out.println(ex.getMessage());
	    ErrorResponse response = new ErrorResponse();
	    response.setMessage(ex.getMessage());
	    return response;
	}
	}


