package com.ecommarce.services.user;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommarce.entity.user.UserInfo;
import com.ecommarce.repository.user.UserRepository;

@Service
public class CommonServices {
	
	@Autowired
	private UserRepository userRepository;

	public UserInfo getCurrentUser() {
		Object principals = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principals instanceof UserDetails)
			return userRepository.findByUserName(((UserDetails) principals).getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		else
			return null;
	}
	
	public String[] getIgnoreFields(Object source) throws IllegalArgumentException, IllegalAccessException{
		
		List<String> ignoreFields = new ArrayList<>();
		
		for(Field field: source.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if(field.get(source) == null)
				ignoreFields.add(field.getName());
		}
		
		return ignoreFields.toArray(new String[0]);
		
	}
	
	public int generateOTP(String email) {
		int otp = (int) (Math.random() * 1000000);
		System.out.println(otp);
		return otp;
	}
}

