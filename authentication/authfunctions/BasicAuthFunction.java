package com.seveneleven.mycontactapp.auth.strategy;

import java.util.Map;
import java.util.Optional;

import authentication.Authentication;
import userfunction.model.User;
import userfunction.manager.PasswordHasher;


public class BasicAuthFunction implements Authentication{
	
	private final Map<String, User> userDatabase;
	private final PasswordHasher hasher;
	

	public BasicAuthStrategy(Map<String, User> userDatabase, PasswordHasher hasher) {
		this.userDatabase = userDatabase;
		this.hasher = hasher;
	}

	@Override
	public Optional<User> authenticate(String email, String rawPassword){
		
		User user = userDatabase.get(email);
		
		if(user == null) {
			return Optional.empty();
		}
		
		String hashed = hasher.hash(rawPassword);
		if(hashed.equals(user.getPasswordHash())) {
			return Optional.of(user); // Login success
		}
		
		return Optional.empty(); // Login fail
	}
}