package userfunction.command;

import userfunction.model.UserProfile;

public class UpdatePhoneNumberCommand implements ProfileCommand{
	private final UserProfile profile;
	private final String newPhoneNumber;
	private String oldPhoneNumber;

	public UpdatePhoneNumberCommand(UserProfile profile, String newPhoneNumber) {
		this.profile = profile;
		this.newPhoneNumber = newPhoneNumber;
	}

	@Override
	public void execute() {
		this.oldPhoneNumber = profile.getPhoneNumber();
		profile.setPhoneNumber(newPhoneNumber);
		System.out.println("Phone number updated...");	
	}

	@Override
	public void undo() {
		if(oldPhoneNumber != null) {
			profile.setPhoneNumber(oldPhoneNumber);
			System.out.println("Reverted to old phone number...");
		}
	}
	
	
}