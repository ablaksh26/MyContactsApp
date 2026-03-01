package main;


import java.util.Scanner;
import java.util.Map;
import java.util.Optional;

import userfunction.model.User;
import userfunction.model.UserBuilder;
import userfunction.model.UserProfile;
import userfunction.model.UserProfileBuilder;
import userfunction.manager.PasswordHasher;
import userfunction.validation.InvalidEmailException;
import userfunction.validation.InvalidPhoneNumberException;
import userfunction.validation.UserValidator;
import userfunction.validation.WeakPasswordException;
import authentication.authfunctions.BasicAuthFunction;
import authentication.authfunctions.OAuthFunction;
import authentication.session.SessionManager;
import authentication.providers.AuthProvider;
import authentication.Authentication;
import userfunction.storage.UserFileManager;

/*
 
   @author: Abhilaksh
   @version: UC2
   
   The Main file, now implements the Login function and also handles the logged in User.
*/

public class Main {
	
	private static final Scanner sc = new Scanner(System.in);
	private static final PasswordHasher hasher = new PasswordHasher();
	
	private static final Map<String, User> userDatabase = UserFileManager.loadData();
	
	public static void userRegistration() {
		sc.nextLine();
		System.out.print("Enter Email: ");
		String email = sc.nextLine();
		
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		
		System.out.print("Enter User Type: ");
		String type = sc.nextLine();
		
		System.out.print("Enter Username: ");
		String username = sc.nextLine();
		
		System.out.print("Enter Bio/Status: ");
		String bio = sc.nextLine();
		
		System.out.print("Enter Phone Number: ");
		String phoneNumber = sc.nextLine();
		try {
			UserValidator.validateEmail(email);
			UserValidator.validatePassword(password);
			UserValidator.validatePhoneNumber(phoneNumber);
			
			String hashedPassword = hasher.hash(password);
			
			UserProfile newProfile = new UserProfileBuilder().setUsername(username)
												  .setBio(bio)
												  .setPhoneNumber(phoneNumber)
												  .build();
			
			User newUser = new UserBuilder().setEmail(email)
											.setPasswordHash(hashedPassword)
											.setProfileInfo(newProfile)
											.setUserType(type.toUpperCase())
											.build();

			userDatabase.put(newUser.getEmail(), newUser);
			UserFileManager.saveData(userDatabase);
			
			System.out.println("------User Registered-------");
			System.out.println("Email: " + newUser.getEmail());
			System.out.println("Password Hash: " + newUser.getPasswordHash());
			System.out.println("Profile Information: " + newUser.getProfileInfo().toString());
			System.out.println("Type: " + newUser.getAccountTier());
			
		}catch (InvalidEmailException | WeakPasswordException | InvalidPhoneNumberException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("Unexpected error occured: " + e.getMessage());
		}
		
		
		
		
	}
	

	public static void userLogin() {
		System.out.println("\n---Login---");
		
		scanner.nextLine();
		
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		
		User userLoginAttempt = userDatabase.get(email);
		
		if(userLoginAttempt == null) {
			System.out.println("User not found. Please register first!!");
			return;
		}
		
		boolean isPremium = "PREMIUM".equalsIgnoreCase(userLoginAttempt.getAccountTier());
		
		System.out.println("Select Auth method: ");
		System.out.println("1. Password");
		
		if(isPremium) {
			System.out.println("2. OAuth Token (AuthProvider) [Unlocked]");
		}else {
			System.out.println("2. OAuth Token (AuthProvider) [Locked - Premium only]");
		}
		
		
		System.out.print("Choice: ");
		String method = scanner.nextLine();
		
		Authentication authStrategy;
		String secret;
		
		if("2".equals(method)) {
			if(!isPremium) {
				System.out.println("You must be a premium user to access OAuth services. Please use password instead.");
				return;
			}
			
			System.out.println("\n[Redirecting to AuthProvider...]");
			String generatedToken = AuthProvider.generateToken(email);
			System.out.println("[AuthProvider] Authentication Successful. Your token is " + generatedToken);
			System.out.println("[Redirecting back to MyContactsApp.....]");
			
			authStrategy = new OAuthStrategy(userDatabase);
			
			System.out.print("Please paste your OAuth Token to finalize login: ");
			secret = scanner.nextLine();
		}else {
			authStrategy = new BasicAuthStrategy(userDatabase, hasher);
			
			System.out.print("Enter password: ");
			secret = scanner.nextLine();
		}
		
		Optional<User> loginResult = authStrategy.authenticate(email, secret);
		
		if(loginResult.isPresent()) {
			SessionManager.getInstance().loginUser(loginResult.get());
			System.out.println("Login Successful");
		}else {
			System.out.println("Login Failed: Please enter valid credentials");
		}
		
	}

//	  Handles the menu before the user logs in and return the Guest intent as a boolean.
	public static boolean handleGuestMenu() {
		System.out.println("---User Menu---");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("0. Exit");
		System.out.println("----------------");
		System.out.print("Enter choice: ");
		
		int choice = sc.nextInt();
		
		
		return switch(choice) {
			case 1 -> {
				userRegistration();
				yield true;
			}
			case 2 -> {
				userLogin();
				yield true;
			}
			
			case 0 -> {
				System.out.println("Thank you!!");
				yield false;
			}
			
			default -> {
				System.out.println("Please enter a valid choice");
				yield true;
			}
		};

	}


	public static boolean handleUserMenu() {
		User activeUser = SessionManager.getInstance().getCurrentUser().get();
		UserProfile profile = activeUser.getProfileInfo();
		
		System.out.println("\n---Main Menu (Logged in as " + activeUser.getEmail() +")---");
		System.out.println("1. Profile Management");
		System.out.println("0. logout");

		System.out.print("Enter Choice: ");
		int input = scanner.nextInt();
		
		return switch(input) {
			case 1 -> {
				System.out.println("Profile Info:-\n" + activeUser.getProfileInfo().toString());
				
				if(profile.getAadharNumber() != null && profile.getBankDetails() != null) {
					System.out.println("Linked Aadhar: " + profile.getAadharNumber());
					System.out.println("Linked Bank: " + profile.getBankDetails());
				} else {
					System.out.println("[Message] Login using AuthProvider to link bank and aadhar detials");
				}
				yield true;
			}
			case 0 -> {
				System.out.println("Logging out...");
				SessionManager.getInstance().logoutUser();
				yield true;
			}
			default -> {
				System.out.println("Invalid Choice!!");
				yield true;
			}
		};
		
	}
	
	public static void main(String[]args) {
		System.out.println("///////////////////////// Welcome to MyContactsApp ///////////////////////");
		
		boolean isRunning = true;
		
		while(isRunning) {
			if(!SessionManager.getInstance().isLoggedIn()) {
				isRunning = handleGuestMenu();
			}
			else {
				isRunning = handleUserMenu();
			}
		}
		
	}
}