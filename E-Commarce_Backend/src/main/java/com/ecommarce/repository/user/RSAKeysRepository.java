package com.ecommarce.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.user.RSAKeys;

public interface RSAKeysRepository extends JpaRepository<RSAKeys, Integer> {

}
