package userfunction.model;

public class UserProfile {
	private String username;
	private String bio;
	private String phoneNumber;
	
	
	// The User Profile Blueprint
	protected UserProfile(UserProfileBuilder builder) {
		this.username = builder.getUsername();
		this.bio = builder.getBio();
		this.phoneNumber = builder.getPhoneNumber();
	}

	
	public String getUsername() { return username; }

	public String getBio() { return bio; }

	public String getPhoneNumber() { return phoneNumber; }

	@Override
	public String toString() {
		return String.join("|", username, bio, phoneNumber);
	}
}
