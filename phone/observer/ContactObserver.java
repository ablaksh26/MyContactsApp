package com.seveneleven.mycontactapp.contact.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;

public interface ContactObserver {

	void onContactDeleted(Contact contact, boolean isHardDelete);
}