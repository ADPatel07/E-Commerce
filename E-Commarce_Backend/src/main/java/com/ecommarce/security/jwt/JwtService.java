package com.ecommarce.security.jwt;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommarce.entity.user.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

	@Autowired
	RSAKeyGenerator keyGenerator;
	
	public String generateToken(UserInfo userInfo) {
		
		HashMap<String, String> claims = new HashMap<>();
		claims.put("Role", userInfo.getUserRole().getRole());
				
		return Jwts.builder().claims(claims).subject(userInfo.getUserName())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
				.signWith(keyGenerator.getPrivateKey()).compact();
	}
	
	 public Claims verifyToken(String token) throws Exception {
		 
		 	return Jwts.parser().verifyWith(keyGenerator.getPublicKey()).build().parseSignedClaims(token).getPayload();
		 
	    }
	
}
