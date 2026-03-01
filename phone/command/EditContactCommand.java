package phone.command;

import java.util.List;

import phone.model.Contact;

public class EditContactCommand implements ContactCommand {
	private final List<Contact> addressBook;
	private final int indexToEdit;
	private final Contact editedContact;
	private ContactMemento memento;

	public EditContactCommand(List<Contact> addressBook, int indexToEdit, Contact editedContact) {
		this.addressBook = addressBook;
		this.indexToEdit = indexToEdit;
		this.editedContact = editedContact;
	}

	@Override
	public void execute() {
		this.memento = new ContactMemento(addressBook.get(indexToEdit));
		
		addressBook.set(indexToEdit, editedContact);
		System.out.println("Contact Edited Successfully");
	}

	@Override
	public void undo() {
		if(memento != null) {
			addressBook.set(indexToEdit, memento.getSavedState());
			System.out.println("Rolled back contact Edit");
		}else {
			System.out.println("Nothing to undo!!");
		}
	}
}