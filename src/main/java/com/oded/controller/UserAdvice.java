package com.oded.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oded.exception.ErrorDTO;
import com.oded.exception.UserNotFound;

@ControllerAdvice
public class UserAdvice {
	@ExceptionHandler(value=UserNotFound.class)
	public ResponseEntity<ErrorDTO> userNo() {
		ErrorDTO dto=new ErrorDTO(3033, "No User");
		return new ResponseEntity<ErrorDTO>(HttpStatus.NOT_FOUND);
		
	}

}
