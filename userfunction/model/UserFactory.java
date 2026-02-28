package userfunction.model;

public class UserFactory {
	
	public static User createUser(String type, UserBuilder builder) {
		if("PREMIUM".contentEquals(type)) {
			return new PremiumUser(builder);
		}
		
		return new FreeUser(builder);
	}

}