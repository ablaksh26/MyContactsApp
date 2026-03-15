package userfunction.model;


// The UserBuilder class used to construct UserProfile and all its getter and the setter. 
 
 
public class UserProfileBuilder {
	
	// The variables for profile and also the getters and the setters function for the same.
	private String username;
	private String bio;
	private String phoneNumber;
	

	
	public String getUsername() { return username; }

	public String getBio() { return bio; }

	public String getPhoneNumber() { return phoneNumber; }
	
	public UserProfileBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserProfileBuilder setBio(String bio) {
		this.bio = bio;
		return this;
	}

	public UserProfileBuilder setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public UserProfile build() {
		return new UserProfile(this);
	}

}
