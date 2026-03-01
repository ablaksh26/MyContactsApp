package com.seveneleven.mycontactapp.contact.view;

import phone.model.Contact;
import phone.EmailAddress;
import phone.model.PhoneNumber;

public class FullDetailsDecorator extends ContactViewDecorator {

	public FullDetailsDecorator(ContactView view, Contact contact) {
		super(view, contact);
	}
	
	@Override
	public String display() {
		StringBuilder stringBuilder = new StringBuilder(view.display()) ;

		stringBuilder.append("\nPhones: ");
		if(contact.getPhoneNumbers().isEmpty()) {
			stringBuilder.append("None");
		}else {
			for(PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
				stringBuilder.append(String.format("[%s] %s | ",phoneNumber.getLabel(),phoneNumber.getPhoneNumber()));
			}
		}

		stringBuilder.append("\nEmails: ");
		if(contact.getEmailAddresses().isEmpty()) {
			stringBuilder.append("None");
		}else {
			for(EmailAddress emailAddress : contact.getEmailAddresses()) {
				stringBuilder.append(String.format("[%s] %s | ",emailAddress.getLabel(),emailAddress.getEmail()));
			}
		}
		
		return stringBuilder.toString();
	}
}