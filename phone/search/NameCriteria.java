package phone.search;

import java.util.regex.Pattern;

import phone.model.Contact;

public class NameCriteria implements SearchCriteria{
	private final Pattern pattern;

	public NameCriteria(String searchQuery) {
		this.pattern = Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE);
	}

	@Override
	public boolean test(Contact contact) {
		return pattern.matcher(contact.getName()).find();
	}

}