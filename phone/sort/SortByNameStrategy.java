package com.seveneleven.mycontactapp.contact.sort;

import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.Comparator;
import java.util.List;

public class SortByNameStrategy implements ContactSortStrategy {
    @Override
    public void sort(List<Contact> contacts) {
        contacts.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
    }
}