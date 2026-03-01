package phone.model;

import java.util.ArrayList;
import java.util.List;

public class Organization extends Phone {
	
	private String website = "N/A";
	private String industry = "General Business";

	protected Organization(OrganizationBuilder builder) {
		super(builder.name);
		this.website = builder.website;
		this.industry = builder.industry;
		
		for(PhoneNumber phoneNumber : builder.phones) {
			this.addPhoneNumber(phoneNumber);
		}
		
		for(EmailAddress emailAddress : builder.emails) {
			this.addEmailAddress(emailAddress);
		}
	}

	public String getWebsite() { return website; }

	public String getIndustry() { return industry; }

	@Override
	public String getContactSummary() { return getName() + "[" + industry + "|" + website + "]"; }
	
	@Override
	public String getContactType() { return "ORGANIZATION"; }

	public static class OrganizationBuilder {
		String name;
		String website = "N/A";
		String industry = "General Business";
		
		private final List<PhoneNumber> phones = new ArrayList<>();
		private final List<EmailAddress> emails = new ArrayList<>();

		public OrganizationBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public OrganizationBuilder setWebsite(String website) {
			this.website = website;
			return this;
		}
		
		public OrganizationBuilder setIndustry(String industry) {
			this.industry = industry;
			return this;
		}

		public OrganizationBuilder addPhoneNumber(PhoneNumber phoneNumber) {
			phones.add(phoneNumber);
			return this;
		}

		public OrganizationBuilder addEmailAddress(EmailAddress emailAddress) {
			emails.add(emailAddress);
			return this;
		}

		public Organization build() {
			return ContactFactory.createOrganizationContact(this);
		}
	}

}