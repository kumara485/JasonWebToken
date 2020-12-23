package com.oded.config;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.oded.entity.JwtRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtils {
	
	private String jwtsecret="kumar";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	  Claims body=null;
	public String getUserNameFromToken(String token) {
	      body = Jwts.parser().setSigningKey(jwtsecret).parseClaimsJws(token).getBody();
		return body.getSubject();
		
	}
	
	public String generateToken(UserDetails details) {
		
		  String token = Jwts.builder().setClaims(body).setSubject(details.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())
				  ).setExpiration(new Date(System.currentTimeMillis()+ 3600000)).
		  signWith(SignatureAlgorithm.HS512, jwtsecret).compact();
		  
		return token;
		
	}
	
	public boolean validate(String token,UserDetails details) {
		String generateToken=null;
		String userNameFromToken = getUserNameFromToken(token);
		
		return userNameFromToken.equals(details.getUsername());
		
	}

}
