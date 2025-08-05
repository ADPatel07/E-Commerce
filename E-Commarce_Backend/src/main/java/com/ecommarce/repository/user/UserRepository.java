package com.ecommarce.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.user.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {
	
	Optional<UserInfo> findByUserName(String userName);

	Optional<UserInfo> findByEmail(String email);

}
