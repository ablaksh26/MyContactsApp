package userfunction.command;

import userfunction.model.UserProfile;

public class UpdateUserNameCommand implements ProfileCommand{
	private final UserProfile profile;
	private final String newName;
	private String oldName;
	
	public UpdateUserNameCommand(UserProfile profile, String newName) {
		this.profile = profile;
		this.newName = newName;
	}

	@Override
	public void execute() {
		this.oldName = profile.getUsername();
		profile.setUsername(newName);
		System.out.println("Username Updated...");
	}

	@Override
	public void undo() {
		if(oldName != null) {
			profile.setUsername(oldName);
			System.out.println("Reverted to old username...");
		}
	}
}