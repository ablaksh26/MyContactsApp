package phone.model;

public class EmailAddress {
	private final String label;
	private final String email;

	public EmailAddress(String label, String email) {
		this.label = label;
		this.email = email;
	}
	
	public String getLabel() { return label; }

	public String getEmail() { return email; }

	@Override
	public String toString() {
		return String.join(":", label, email);
	}
}