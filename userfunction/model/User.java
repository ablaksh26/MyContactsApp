package userfunction.model;

public abstract class User {
	
	// Define user attributes;
	private String email;
	private String passwordHash;
	private UserProfile profileInfo;
	
	
	protected User(UserBuilder builder) {
		this.email = builder.getEmail();
		this.passwordHash = builder.getPasswordHash();
		this.profileInfo = builder.getProfileInfo();
	}
	
	
	public String getEmail() { return email; }
	
	public String getPasswordHash() { return passwordHash; }

	public UserProfile getProfileInfo() { return profileInfo; }
	
	public abstract String getAccountTier();
}