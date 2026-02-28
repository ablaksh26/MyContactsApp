package userfunction.model;

// The Class for premium account.
public class PremiumUser extends User {
	
	protected PremiumUser(UserBuilder builder) {
		super(builder);
	}
	
	@Override
	public String getAccountTier() {
		return "PREMIUM";
	}
}
