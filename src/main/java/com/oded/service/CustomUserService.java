package com.oded.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oded.config.MyUserDetails;
import com.oded.entity.User;
import com.oded.repo.UserRepo;


@Service
public class CustomUserService implements UserDetailsService {
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.err.println("clllllllllllllllllllllllllllllllllllllll");
		User user = repo.findByUsername(username);
		//if(user!=null) {
		String username2 = user.getUsername();
		System.out.println("User Name"+username2);
		
		return new MyUserDetails(user.getUsername(),user.getPassword(),Collections.EMPTY_LIST);

		
		
		
		
		}
		// throw new UsernameNotFoundException("Unable to retive Username from DB");
		
		
		
		//}
	

}
