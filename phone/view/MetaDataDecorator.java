package phone.view;

import phone.model.Contact;

public class MetadataDecorator extends ContactViewDecorator{

	public MetadataDecorator(ContactView view, Contact contact) {
		super(view, contact);
	}
	
	@Override
	public String display() {
		String baseDisplay = view.display();
		String timeAdded = contact.getTimeStamp().toString().substring(0, 16).replace("T", " ");
		
		return String.format(
				"=================================================\n" +
			    "%s\n" +
				"Added: %s\n" +
			    "ID: %s\n" +
			    "=================================================",
			    baseDisplay, timeAdded, contact.getId().toString());
	}
}