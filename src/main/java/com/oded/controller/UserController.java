package com.oded.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oded.config.JwtUtils;
import com.oded.entity.JwtRequest;
import com.oded.entity.JwtResponse;
import com.oded.entity.Posts;
import com.oded.entity.User;
import com.oded.exception.UserNotFound;
import com.oded.repo.UserRepo;
import com.oded.service.CustomUserService;

@RestController
public class UserController {
	@Autowired
	private UserRepo repo;
	@Autowired
	private BCryptPasswordEncoder end;
	@Autowired
	private CustomUserService seri;
	@Autowired
	private AuthenticationManager auth;
	@Autowired
	private JwtUtils utils;

	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> ho(@RequestBody JwtRequest req) {
		
		auth(req.getUsername(),req.getPassword());
		UserDetails loadUserByUsername = seri.loadUserByUsername(req.getUsername());
		 String generateToken = utils.generateToken(loadUserByUsername);
		 JwtResponse response=new JwtResponse();
		 response.setJwttoken(generateToken);
		return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
	}
	
	public void auth(String username,String password) {
		
		Authentication authenticate = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
	}
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
		
	}
	
	@GetMapping("/hi")
	public String ssayHello() {
		return "Hi Well Done You have done the JWT POC";
		
	}
	

}
