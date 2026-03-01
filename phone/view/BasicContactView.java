package phone.view;

import phone.model.Contact;

public class BasicContactView implements ContactView{
	private final Contact contact; 

	public BasicContactView(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String display() {
		return contact.getContactSummary();
	}

	@Override
	public String toString() {
		return display();
	}
	
	
}