package auth;

import java.util.Optional;

import userfunction.model.User;


public interface Authentication {
	
	public Optional<User> authenticate(String identifier, String secret);
}