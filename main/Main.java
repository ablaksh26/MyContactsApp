package com.main;
import java.util.Scanner;

import userfunction.model.User;
import userfunction.model.UserBuilder;
import userfunction.model.UserProfile;
import userfunction.model.UserProfileBuilder;
import userfunction.manager.PasswordHasher;
import userfunction.validation.InvalidEmailException;
import userfunction.validation.InvalidPhoneNumberException;
import userfunction.validation.UserValidator;
import userfunction.validation.WeakPasswordException;
/*
 
   @author: Abhilaksh
   @version: UC1
   
   The Main file for the User Registration , the Contacts app function and the exit.
 */

public class Main {
	
	private static final Scanner sc = new Scanner(System.in);
	private static final PasswordHasher hasher = new PasswordHasher();
	
	private static User loggedInUser = null;
	
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
				System.out.println("Login will be implemented soon");
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
	
	public static void main(String[]args) {
		System.out.println("///////////////////////// Welcome to MyContactsApp ///////////////////////");
		
		boolean isRunning = true;
		
		while(isRunning) {
			if(loggedInUser == null) {
				isRunning = handleGuestMenu();
			}
		}
		
	}
}
