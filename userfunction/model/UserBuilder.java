package userfunction.model;

// The Builder Class for the User Attributes.
public class UserBuilder {
	private String email;
	private String passwordHash;
	private UserProfile profileInfo;
	private String userType = "FREE";


	
	// The getters and the setters function for attributes.
	
	public String getEmail() { return email; }

	public String getPasswordHash() { return passwordHash; }

	public UserProfile getProfileInfo() { return profileInfo; }

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	public UserBuilder setProfileInfo(UserProfile profileInfo) {
		this.profileInfo = profileInfo;
		return this;
	}

	public UserBuilder setUserType(String type) {
		this.userType = type;
		return this;
	}

	public User build() {
		return UserFactory.createUser(this.userType, this);
	}
}
