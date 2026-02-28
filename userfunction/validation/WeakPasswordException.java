package userfunction.validation;

@SuppressWarnings("serial")
public class WeakPasswordException extends Exception{

	public WeakPasswordException(String message) {
		super(message);
	}
}