package phone.storage;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import phone.model.Contact;
import phone.model.EmailAddress;
import phone.model.Organization;
import phone.model.Person;
import phone.model.PhoneNumber;
import userfunction.model.User;


public class ContactFileManager {

	private static final String BASE_DIR = "phone/storage/";
	
	public static void saveContacts(User user) {
		File file_dir = generateFile(user);
		
		if(!file_dir.exists()) {
			file_dir.mkdirs();
		}
		
		File contactFile = new File(file_dir + "/contacts.txt");
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(contactFile))){
			for(Contact contact : user.getContacts()) {
				
				StringBuilder phoneStr = new StringBuilder();
				for(PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
					phoneStr.append(phoneNumber.getLabel())
							.append(",")
							.append(phoneNumber.getPhoneNumber())
							.append(";");
				}
				
				StringBuilder emailStr = new StringBuilder();
				for(EmailAddress emailAddress : contact.getEmailAddresses()) {
					emailStr.append(emailAddress.getLabel())
							.append(",")
							.append(emailAddress.getEmail())
							.append(";");
				}
				
				String line = "";
				if(contact.getContactType().equalsIgnoreCase("PERSON")) {
					if(contact instanceof Person) {
						Person p = (Person) contact;
						line = String.format("PERSON|%s|%s|%s|%s",p.getName(),
																		p.getRelationship(),
																		phoneStr,
																		emailStr);
					}
				}else {
					if(contact instanceof Organization) {
						Organization o = (Organization) contact;
						line = String.format("ORGANIZATION|%s|%s|%s|%s|%s",o.getName(),
																		   o.getWebsite(),
																		   o.getIndustry(),
																		   phoneStr,
																		   emailStr);
					}
				}
				writer.write(line);
				writer.newLine();
			}
	
		}catch(IOException e) {
			System.out.println("Error Saving Data: " + e.getMessage());
		}
	}

	public static void loadContacts(User user) {
		File file_dir = generateFile(user);
		File contactFile = new File(file_dir + "/contacts.txt");
		
		try(BufferedReader reader = new BufferedReader(new FileReader(contactFile))) {
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|", -1);
				
				String type = parts[0];
				
				int phoneIdx = type.equalsIgnoreCase("PERSON") ? 3 : 4;
				int emailIdx = type.equalsIgnoreCase("PERSON") ? 4 : 5;
				
				List<PhoneNumber> phoneNumbers = parsePhoneString(parts[phoneIdx]);
				List<EmailAddress> emailAddresses = parseEmailString(parts[emailIdx]);
				
				Contact loadedContact;
				
				if(type.equalsIgnoreCase("PERSON")) {
					loadedContact = new Person.PersonBuilder().setName(parts[1])
															  .setRelationsip(parts[2])
															  .build();
					
				}else {
					loadedContact = new Organization.OrganizationBuilder().setName(parts[1])
																		  .setWebsite(parts[2])
																		  .setIndustry(parts[3])
																		  .build();
				}
				
				for(PhoneNumber phoneNumber : phoneNumbers) {
					loadedContact.addPhoneNumber(phoneNumber);
				}
				
				for(EmailAddress emailAddress : emailAddresses) {
					loadedContact.addEmailAddress(emailAddress);
				}
				
				user.getContacts().add(loadedContact);
			}
		}catch(IOException e) {
			System.out.println("Error Loading Data: " + e.getMessage());
		}
	}

	private static List<PhoneNumber> parsePhoneString(String phoneStr) {
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		
		if(phoneStr.isEmpty()) { return phoneNumbers; }
		
		String[] phones = phoneStr.split(";");
		
		for(String phone : phones) {
			String[] parts = phone.split(",");
			
			if(parts.length == 2) {
				String label = parts[0];
				String phoneNumber = parts[1];
			
				phoneNumbers.add(new PhoneNumber(label, phoneNumber));
			}
		}
		
		return phoneNumbers;
	}

	private static List<EmailAddress> parseEmailString(String emailStr) {
		List<EmailAddress> emailAddresses = new ArrayList<>();
		
		if(emailStr.isEmpty()) { return emailAddresses; }
		
		String[] emails = emailStr.split(";");
		
		for(String email : emails) {
			String[] parts = email.split(",");
			
			if(parts.length == 2) {
				String label = parts[0];
				String emailAddress = parts[1];
		
				emailAddresses.add(new EmailAddress(label, emailAddress));
			}
		}
		
		return emailAddresses;
	}

	private static File generateFile(User user) {
		String safeEmail = user.getEmail().replace("@", "_").replace(".", "_");
		
		return new File(BASE_DIR + safeEmail);
	}
}