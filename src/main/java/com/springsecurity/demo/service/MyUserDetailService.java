package com.springsecurity.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.demo.security.MyUserDetails;

@Service
public class MyUserDetailService implements UserDetailsService {

	//private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return new MyUserDetails(username);
		/*User user = userRepository.findByUsername(username)
	              .orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
	        return new MyUserDetails(user);*/
	}

	
}
