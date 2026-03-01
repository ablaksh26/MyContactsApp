package phone.sort;

import phone.model.Contact;
import java.util.Comparator;
import java.util.List;

public class SortByDateStrategy implements ContactSortStrategy {
    @Override
    public void sort(List<Contact> contacts) {
        contacts.sort(Comparator.comparing(Contact::getTimeStamp).reversed());
    }
}