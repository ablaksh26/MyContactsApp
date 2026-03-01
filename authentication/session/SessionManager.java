package authentication.session;

import java.util.Optional;

import userfunction.model.User;

public class SessionManager {
	
	private User currentUser;
	private static SessionManager instance;
	
	private SessionManager() {
		this.currentUser = null;
	}
	

	public static SessionManager getInstance() {
		if(instance == null) {
			instance = new SessionManager();
		}
		
		return instance;
	}

	public void loginUser(User user) {
		this.currentUser = user;
	}
	

	public void logoutUser() {
		this.currentUser = null;
	}
	

	public boolean isLoggedIn() {
		return currentUser != null;
	}

	public Optional<User> getCurrentUser(){
		return Optional.ofNullable(currentUser);
	}
}