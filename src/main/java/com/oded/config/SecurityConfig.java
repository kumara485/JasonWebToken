package com.oded.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oded.service.CustomUserService;
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserService ser;
	@Autowired
	private RequestFilter fil;
	
	@Bean
	public BCryptPasswordEncoder encoded() {
	  return	new BCryptPasswordEncoder();
	}

	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	   }
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {


//		http.csrf().disable()
//		// dont authenticate this particular request
//		.authorizeRequests().antMatchers("/authenticate").permitAll().
//		// all other requests need to be authenticated
//		anyRequest().authenticated()
//		// make sure we use stateless session; session won't be used to
//		// store user's state.
//		.and()
//	.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.csrf().disable()
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/authenticate").permitAll().
		// all other requests need to be authenticated
		anyRequest().authenticated().and().
		// make sure we use stateless session; session won't be used to
		// store user's state.
		exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// Add a filter to validate the tokens with every request
httpSecurity.addFilterBefore(fil, UsernamePasswordAuthenticationFilter.class);

		

// Add a filter to validate the tokens with every request
    //  http.addFilterBefore(fil, UsernamePasswordAuthenticationFilter.class);

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		
		auth.userDetailsService(ser).passwordEncoder(encoded());
	}

}
