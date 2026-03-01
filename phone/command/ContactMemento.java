package phone.command;

import phone.model.Contact;

public class ContactMemento {
	private final Contact savedState;

	public ContactMemento(Contact contactToSave) {
		this.savedState = contactToSave.copy();
	}
	
	public Contact getSavedState() {
		return savedState;
	}
}