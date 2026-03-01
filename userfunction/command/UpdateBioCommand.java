package userfunction.command;

import userfunction.model.UserProfile;

public class UpdateBioCommand implements ProfileCommand{
	private final UserProfile profile;
	private final String newBio;
	private String oldBio;
	
	public UpdateBioCommand(UserProfile profile, String newBio) {
		this.profile = profile;
		this.newBio = newBio;
	}

	@Override
	public void execute() {
		this.oldBio = profile.getBio();
		profile.setBio(newBio);
		System.out.println("Bio Updated...");
	}

	@Override
	public void undo() {
		if(oldBio != null) {
			profile.setBio(oldBio);
			System.out.println("Reverted to old Bio...");
		}
	}
}