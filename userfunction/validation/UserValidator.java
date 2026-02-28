package userfunction.validation;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserValidator {
	
	// The regex pattern for email.
	public static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	// The regex pattern for passwords.
	public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)\\S{8,}$";

	// The regex pattern for phone numbers.
	public static final String PHONE_PATTERN = "^\\+?[1-9]\\d{1,14}$";

	public static void validateEmail(String email) throws InvalidEmailException{
		
		if(email == null || email.isEmpty()) {
			throw new InvalidEmailException("Email cannot be empty!!");
		}
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches()) {
			throw new InvalidEmailException("Email address format invalid please use valid format: user@example.com");
		}
	}
	
	// The Validate password function for checking password pattern.
	public static void validatePassword(String password) throws WeakPasswordException{
		if(password == null || password.isEmpty()) {
			throw new WeakPasswordException("Password cannot be empty or null");
		}
		
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher= pattern.matcher(password);
		
		if(!matcher.matches()) {
			throw new WeakPasswordException("The password is too weak please make sure the password has atleast one uppercase character, one lowercase character, one special character and is atleast 8 digits.");
		}
	}
	
	// The validate phone number function for checking the phone number pattern.

	public static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
		if(phoneNumber == null || phoneNumber.isEmpty()) {
			throw new InvalidPhoneNumberException("Phone number cannot be empty or null");
		}
		
		Pattern pattern = Pattern.compile(PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		if(!matcher.matches()) {
			throw new InvalidPhoneNumberException("The phone number is of invalid format please enter a valid phone number.");
		}
		
	}
}