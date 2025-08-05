package com.ecommarce.security.jwt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.ecommarce.entity.user.RSAKeys;
import com.ecommarce.repository.user.RSAKeysRepository;

@Service
public class RSAKeyGenerator {

	private KeyPair keyPair;

	@Autowired
	private RSAKeysRepository keyRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void generateKeys() throws NoSuchAlgorithmException, InvalidKeySpecException {

		RSAKeys keys = keyRepository.findById(1).orElse(new RSAKeys());
		
		if (keys.getPrivateKey() == null | keys.getPublicKey() == null) {
			this.keyPair = generateKeyPair();

			keys.setPrivateKey(keyPair.getPrivate().getEncoded());
			keys.setPublicKey(keyPair.getPublic().getEncoded());
			keys.setId(1);
			
			keyRepository.save(keys);
			
		} else {
			PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(keys.getPrivateKey());
	        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(keys.getPublicKey());
			KeyFactory kpg =  KeyFactory.getInstance("RSA");
			
			PrivateKey privateKey = kpg.generatePrivate(privateSpec);
			PublicKey publicKey = kpg.generatePublic(publicSpec);
			
			this.keyPair = new KeyPair(publicKey, privateKey);
		}

	}

	private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048); // You can use 1024, 2048, or 4096 bits
		return keyPairGenerator.generateKeyPair();
	}

	public PublicKey getPublicKey() {
		return keyPair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return keyPair.getPrivate();
	}

	public String getPublicKeyAsString() {
		return Base64.getEncoder().encodeToString(getPublicKey().getEncoded());
	}

	public String getPrivateKeyAsString() {
		return Base64.getEncoder().encodeToString(getPrivateKey().getEncoded());
	}
}
