package phone.model;

import phone.model.Organization.OrganizationBuilder;
import phone.model.Person.PersonBuilder;

public class PhoneFactory {
	
	public static Person createPersonContact(PersonBuilder builder) {
		return new Person(builder);
	}
	
	public static Organization createOrganizationContact(OrganizationBuilder builder) {
		return new Organization(builder);
	}
}