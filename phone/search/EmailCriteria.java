package phone.search;

import java.util.regex.Pattern;

import phone.model.Contact;
import phone.model.EmailAddress;

public class EmailCriteria implements SearchCriteria{
	private final Pattern pattern;

	public EmailCriteria(String searchQuery) {
		this.pattern = Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE);
	}

	@Override
	public boolean test(Contact contact) {
		for(EmailAddress email : contact.getEmailAddresses()) {
			if(pattern.matcher(email.getEmail()).find()) {
				return true;
			}
		}
		
		return false;
	}

}