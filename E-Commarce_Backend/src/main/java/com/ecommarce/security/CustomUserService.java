package com.ecommarce.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommarce.entity.user.UserInfo;
import com.ecommarce.repository.user.UserRepository;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		try {
			
			UserInfo user = (UserInfo) userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

			if (username != null && user != null) {
				
				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().getRole()));
				
				return new User(username, String.valueOf(user.getOtp()), grantedAuthorities);
			}

			throw new UsernameNotFoundException("UserName Not Found");

		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
