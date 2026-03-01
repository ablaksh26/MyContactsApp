package authentication.providers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthProvider {
	private final static Map<String, String> activeTokens = new HashMap<>();

	private final static Map<String, ExternalData> providerDatabase = new HashMap<>();
	
	static {
		// Add dummy data
		providerDatabase.put("noreply@gmail.com", new ExternalData("6262-2722-5666", "HDFC-7287832829"));
		providerDatabase.put("noreply@gmail.com", new ExternalData("4252-2626-5424", "SBI-78329327823"));
	}

	public static String generateToken(String email) {
		String token = "contacts_" + UUID.randomUUID().toString();
		
		activeTokens.put(token, email);
		
		return token;
	}

	public static boolean isValidToken(String token, String email) {
		String registeredEmail = activeTokens.get(token);
		
		return registeredEmail != null && registeredEmail.equals(email);
	}

	public static ExternalData fetchSensitiveData(String token) {
		String email = activeTokens.get(token);
		if(email != null) {
			return providerDatabase.get(email);
		}
		
		return null;
	}

	public static class ExternalData {
		private final String aadharNumber;
		private final String bankDetails;
		

		public ExternalData(String aadharNumber, String bankDetails) {
			this.aadharNumber = aadharNumber;
			this.bankDetails = bankDetails;
		}

		public String getAadharNumber() { return aadharNumber; }

		public String getBankDetails() { return bankDetails; }
		
	}
}