package phone.model;

import java.util.ArrayList;
import java.util.List;

public class Person extends Phone {
	
	private String relationship;
	
	protected Person(PersonBuilder builder) {
		super(builder.name);
		this.relationship = builder.relationship;
		
		for(PhoneNumber phoneNumber : builder.phones) {
			this.addPhoneNumber(phoneNumber);
		}
		
		for(EmailAddress emailAddress : builder.emails) {
			this.addEmailAddress(emailAddress);
		}
	}

	public String getRelationship() { return relationship; }
	
	@Override
	public String getContactSummary() { return getName() + "[" + relationship  + "]"; }

	@Override
	public String getContactType() { return "PERSON"; }

	public static class PersonBuilder {
		String name;
		String relationship = "Aquaintance";
		
		private final List<PhoneNumber> phones = new ArrayList<>();
		private final List<EmailAddress> emails = new ArrayList<>();

		public PersonBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public PersonBuilder setRelationsip(String relationship) {
			this.relationship = relationship;
			return this;
		}
		
		public PersonBuilder addPhoneNumber(PhoneNumber phoneNumber) {
			phones.add(phoneNumber);
			return this;
		}

		public PersonBuilder addEmailAddress(EmailAddress emailAddress) {
			emails.add(emailAddress);
			return this;
		}
		
		public Person build() {
			return ContactFactory.createPersonContact(this);
		}
	}

}