package com.ecommarce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommarce.security.jwt.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFillter extends OncePerRequestFilter {
	
	@Autowired
	JwtService jwtServices;
	
	@Autowired
	CustomUserService customUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {

		String token = request.getHeader("Authorization");
		
		if(token != null)
			token = token.substring(7);
						
		if (token != null && !token.equals("undefined")) {
			try {
								
				Claims jwt = jwtServices.verifyToken(token);
				String userName = jwt.getSubject();
								
				UserDetails user = customUserService.loadUserByUsername(userName);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null ,user.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
								
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
