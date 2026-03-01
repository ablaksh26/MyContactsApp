package com.seveneleven.mycontactapp.user.command;

import userfunction.model.User;
import userfunction.manager.PasswordHasher;
import userfunction.validation.UserValidator;

public class ChangePasswordCommand implements ProfileCommand {
	private final User user;
	private final String newRawPassword;
	private final PasswordHasher hasher;
	private String oldPasswordHash;

	public ChangePasswordCommand(User user, String newRawPassword, PasswordHasher hasher) {
		this.user = user;
		this.newRawPassword = newRawPassword;
		this.hasher = hasher;
	}

	@Override
	public void execute() {
		try {
			UserValidator.validatePassword(newRawPassword);
			
			this.oldPasswordHash = user.getPasswordHash();
			
			String newHash = hasher.hash(newRawPassword);
			user.setPasswordHash(newHash);
			System.out.println("Password changed successfully!!");
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	@Override
	public void undo() {
		if(oldPasswordHash != null) {
			user.setPasswordHash(oldPasswordHash);
			System.out.println("Old password restord");
		}
	}
	
}