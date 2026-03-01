package phone.view;

import phone.model.Contact;

public class ContactViewDecorator implements ContactView{
	protected final ContactView view;
	protected final Contact contact;

	public ContactViewDecorator(ContactView view, Contact contact) {
		this.view = view;
		this.contact = contact;
	}

	@Override
	public String display() {
		return view.display();
	}
	
	@Override
	public String toString() {
		return display();
	}
}