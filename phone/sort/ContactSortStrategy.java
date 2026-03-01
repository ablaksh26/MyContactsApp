package phone.sort;

import java.util.List;

import phone.model.Contact;

public interface ContactSortStrategy {
	void sort(List<Contact> contacts);
}