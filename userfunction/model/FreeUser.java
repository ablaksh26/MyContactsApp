package userfunction.model;

// The Class for the free account.

public class FreeUser extends User {
	

	protected FreeUser(UserBuilder builder) {
		super(builder);
	}
	
	@Override
	public String getAccountTier() {
		return "FREE";
	}
}