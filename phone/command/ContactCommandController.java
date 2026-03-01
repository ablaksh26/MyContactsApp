package phone.command;


import java.util.Stack;

public class ContactCommandController {
	private final static Stack<ContactCommand> commandHistory = new Stack<>();

	public static void executeCommand(ContactCommand contactCommand) {
		contactCommand.execute();
		commandHistory.push(contactCommand);
	}

	public static void undoCommand() {
		if(commandHistory.isEmpty()) {
			System.out.print("Nothing to undo");
			return;
		}
		commandHistory.pop().undo();
	}
}