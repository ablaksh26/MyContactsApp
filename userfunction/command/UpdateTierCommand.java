package com.seveneleven.mycontactapp.user.command;

import java.util.Map;

import authentication.session.SessionManager;
import userfunction.model.PremiumUser;
import userfunction.model.User;
import userfunction.model.UserBuilder;

public class UpdateTierCommand implements ProfileCommand {
	private final Map<String, User> userDatabase;
	private final User oldUser;
	private User upgradedUser;
	
	public UpdateTierCommand(Map<String, User> userDatabase, User currentUser) {
		this.userDatabase = userDatabase;
		this.oldUser = currentUser;
	}

	@Override
	public void execute() {
		if(oldUser instanceof PremiumUser) {
			System.out.println("You are already a premium user.");
			return;
		}
		
		this.upgradedUser = new UserBuilder().setEmail(oldUser.getEmail())
											 .setPasswordHash(oldUser.getPasswordHash())
											 .setProfileInfo(oldUser.getProfileInfo())
											 .setUserType("PREMIUM")
											 .build();
		
		userDatabase.put(upgradedUser.getEmail(), upgradedUser);
		SessionManager.getInstance().loginUser(upgradedUser);
		
		System.out.println("Upgraded to premium OAuth features now unlocked!!");
	}

	@Override
	public void undo() {
		if(upgradedUser != null) {
			userDatabase.put(oldUser.getEmail(), oldUser);
			SessionManager.getInstance().loginUser(oldUser);
			
			System.out.println("Reverted back to FREE tier");
		}
	}
}