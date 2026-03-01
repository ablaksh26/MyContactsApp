package phone.observer;

import phone.model.Contact;

public class DeletionLogger implements ContactObserver{
	@Override
	public void onContactDeleted(Contact contact, boolean isHardDelete) {
		String type = isHardDelete ? "HARD DELETED (Permanent)" : "SOFT DELETED (Move to trash)";
		System.out.println("\n[SYSTEM LOG] Lifecycle Event: Contact '" + contact.getName() + "' was " + type + ".");
	}
}