package phone.search;

import phone.model.Contact;
import phone.model.PhoneNumber;

public class PhoneCriteria implements SearchCriteria{
	private final String targetNumber;

	public PhoneCriteria(String targetNumber) {
		this.targetNumber = targetNumber.replaceAll("[^0-9+]", "");
	}

	@Override
	public boolean test(Contact contact) {
		for(PhoneNumber phone : contact.getPhoneNumbers()) {
			if(phone.getPhoneNumber().replaceAll("[^0-9+]", "").contains(targetNumber)) {
				return true;
			}
		}
		
		return false;
	}

}