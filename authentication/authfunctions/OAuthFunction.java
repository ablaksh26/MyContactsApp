package com.seveneleven.mycontactapp.auth.strategy;

import java.util.Optional;
import java.util.Map;

import authentication.Authentication;
import authentication.providers.AuthProvider;
import authentication.userfunction.model.User;

public class OAuthFunction implements Authentication{
	
	private final Map<String, User> userDatabase;

	public OAuthStrategy(Map<String, User> userDatabase) {
		this.userDatabase = userDatabase;
	}
	

	@Override
	public Optional<User> authenticate(String email, String token) {
		
		User user = userDatabase.get(email);
		if(user == null) {
			return Optional.empty();
		}
		
		if(!"PREMIUM".equalsIgnoreCase(user.getAccountTier())) {
			System.out.println("Only premium accounts can access the OAuth facility");
			return Optional.empty();
		}
		
		if(AuthProvider.isValidToken(token, email)) {
			
			AuthProvider.ExternalData externalData = AuthProvider.fetchSensitiveData(token);
			
			if(externalData != null) {
				user.getProfileInfo().setAadharNumber(externalData.getAadharNumber());
				user.getProfileInfo().setBankDetails(externalData.getBankDetails());
				System.out.println("[MyContacts] Synced aadhar and bank details from OAuthProvider");
			}
			
			return Optional.of(user);
		}
		
		return Optional.empty();

}