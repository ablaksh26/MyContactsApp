package userfunction.command;

import java.util.Stack;

public class ProfileUpdateController {
	private final static Stack<ProfileCommand> commandHistory = new Stack<>();

	public static void executeCommand(ProfileCommand profileCommand) {
		try {
			profileCommand.execute();
			commandHistory.push(profileCommand);
			
		}catch(IllegalArgumentException e) {
			System.out.println("Validation error: " + e.getMessage());
		}catch(Exception e) {
			System.out.println("Unexpexted error: " + e.getMessage());
		}
	}
	
	public static void undoCommand() {
		if(commandHistory.isEmpty()) {
			System.out.print("Nothing to undo");
			return;
		}
		
		ProfileCommand previousCommand= commandHistory.pop();
		previousCommand.undo();
	}
}