package phone.model;

public class PhoneNumber {
	private final String label;
	private final String phoneNumber;

	public PhoneNumber(String label, String phoneNumber) {
		this.label = label;
		this.phoneNumber = phoneNumber;
	}

	public String getLabel() { return label; }

	public String getPhoneNumber() { return phoneNumber; }
	
	@Override
	public String toString() {
		return String.join(":", label, phoneNumber);
	}
}
