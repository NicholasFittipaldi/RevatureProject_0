package application;

import java.util.List;
import java.util.Scanner;

import models.*;
import services.*;

public class Console {
	static Scanner scan = new Scanner(System.in);
	static BankServicesImpl bsi = new BankServicesImpl();
	static AccountServicesImpl asi = new AccountServicesImpl();
	static TransactionServicesImpl tsi = new TransactionServicesImpl();
	
	static int accountId;

	public static void main(String[] args) {		
		loggedOut();
	}
	
	public static void loggedOut() {
		System.out.println("\nSelect an option below (enter the number):"
				+ "\n1) Log In"
				+ "\n2) Register"
				+ "\n3) Exit");
		int choice = scan.nextInt();
		
		switch (choice) {
		case 1:
			while (true) {
				System.out.println("\nUsername:");
				String username = scan.next();
				System.out.println("Password:");
				String password = scan.next();
			
				if (bsi.getUser(username) == null)
					System.out.println("*Invalid Username*");
				if (bsi.getUserByPass(password) == null)
					System.out.println("*Invalid Password*");
				if (bsi.getUser(username) != null && bsi.getUserByPass(password) != null) {
					BankUser bankUser = bsi.getUser(username);
					mainMenu(bankUser);
				}
			}
			
		case 2:
			while (true) {
				System.out.println("\nEnter a valid username:");
				String newUser = scan.next();
				System.out.println("Create a password:");
				String newPassword = scan.next();
			
				if (bsi.getUser(newUser) != null)
					System.out.println("*Username is already in use*");
				else {
					BankUser bankUser = new BankUser(0000, newUser, newPassword, 0);
					bsi.addUser(bankUser);
					mainMenu(bankUser);
					
					break;
				}
			}
			
		case 3:
			System.out.println("Thank You!");
			System.exit(0);
		}
	}
	
	public static void mainMenu(BankUser bankUser) {
		int choice = 0;
		
		if (bankUser.getSuperUser() == 1) {
			while (true) {
				System.out.println("\nWhat would you like to do next? (enter the number):"
						+ "\n1) View accounts and balances."
						+ "\n2) Create a new Account."
						+ "\n3) Delete empty Account."
						+ "\n4) Withdraw"
						+ "\n5) Deposit"
						+ "\n6) View Transactions"
						+ "\n7) View Users"
						+ "\n8) Create User"
						+ "\n9) Update User"
						+ "\n10) Delete User"
						+ "\n11) Log Out");
				choice = scan.nextInt();
			
				switch (choice) {
			
				case 1:
					if (asi.getAccounts(bankUser.getId()).isEmpty())
						System.out.println("*You have no accounts at the moment*");
					else {
						List<Account> accountList = asi.getAccounts(bankUser.getId());
						
						System.out.println("------------------------------------------------------");
					    System.out.printf("%10s %10s %15s %15s", "Account", "User", "Name", "Balance");
					    System.out.println();
					    System.out.println("------------------------------------------------------");
						for (Account a : accountList) {
							System.out.format("%10d %10d %15s %15s",
					                a.getAccountId(), a.getUserId(), a.getName(), a.balanceToString());
					        System.out.println();
						}
					    System.out.println("------------------------------------------------------");
					}
					break;
				
				case 2:
					System.out.println("\nEnter a name for your account:");
					String name = scan.next();
					System.out.println("Enter the starting balance (enter as a decimal):");
					float balance = scan.nextFloat();
				
					Account a = new Account(0000000, bankUser.getId(), name, balance);
				 
					if (asi.addAccount(a))
						System.out.println("Account successfully created");
					else
						System.out.println("Account could not be created");
					break;
				
				case 3:
					System.out.println("Enter the account ID number for the account you want to delete:"
							+ "\n(keep in mind you can only delete an account with a balance of $0.00)");
					accountId = scan.nextInt();
				
					if (asi.deleteAccount(accountId))
						System.out.println("*Account deleted successfully*");
					else
						System.out.println("*Account could not be deleted*");
					break;
				
				case 4:
					System.out.println("Enter the account number you want to withdraw from:");
					accountId = scan.nextInt();
					System.out.println("Enter the amount you want to withdraw (enter as a decimal):");
					float amount = scan.nextFloat();
				
					if (asi.withdraw(amount, accountId)) {
						Transaction t = new Transaction(bankUser.getId(), 0000000, accountId, "Withdraw", -amount);
						tsi.addTransaction(t);
						System.out.println("*Withdraw successful*");
					}
					else
						System.out.println("*Withdraw failed*");
					break;
				
				case 5:
					System.out.println("Enter the account number you want to deposit into:");
					accountId = scan.nextInt();
					System.out.println("Enter the amount you want to deposit (enter as a decimal):");
					float amount2 = scan.nextFloat();
				
					if (asi.deposit(amount2, accountId)) {
						Transaction t1 = new Transaction(bankUser.getId(), 0000000, accountId, "Deposit", amount2);
						tsi.addTransaction(t1);
						System.out.println("*Deposit successful*");
					}
					else
						System.out.println("*Deposit failed*");
					break;
					
				case 6:
					if (tsi.viewTransactions(bankUser.getId()).isEmpty())
						System.out.println("*You have not made any transactions yet*");
					else
						System.out.println(tsi.viewTransactions(bankUser.getId()));
					break;
				
				case 7:
					if (bsi.getAllUsers().isEmpty())
						System.out.println("*There are no users at this time*");
					else
						System.out.println(bsi.getAllUsers());
					break;
					
				case 8:
					while (true) {
						System.out.println("\nEnter a valid username:");
						String newUsername = scan.next();
						System.out.println("Create a password:");
						String newPassword = scan.next();
					
						if (bsi.getUser(newUsername) != null)
							System.out.println("*Username is already in use*");
						else {
							BankUser newBankUser = new BankUser(0000, newUsername, newPassword, 0);
							bsi.addUser(newBankUser);
							System.out.println("*User created successfully*");
							break;
						}
					}
					break;
					
				case 9:
					System.out.println("\nEnter the user ID number for the user you wish to update:");
					int idNum = scan.nextInt();
					BankUser b2 = bsi.getUser(idNum);
					System.out.println(b2);
					
					if (b2 == null)
						System.out.println("*Invalid user ID*");
					
					System.out.println("Enter the field you would like to change:"
							+ "\n(username, password, or super user status)");
					String field = scan.next();
					
					if (field.equals("username")) {
						while (true) {
							System.out.println("Enter a new username:");
							String newUsername = scan.next();
							if (bsi.getUser(newUsername) != null || b2.getUsername() == newUsername)
								System.out.println("*Username is already in use*");
							else {
								b2.setUsername(newUsername);
								if (bsi.updateUser(b2) != null)
									System.out.println("*User updated successfully*");
								else 
									System.out.println("*User update failed*");
								break;
							}
						}
					}
					
					if (field.equals("password")) {
						while (true) {
							System.out.println("Enter a new password:");
							String newPassword = scan.next();
							if (b2.getPassword() == newPassword)
								System.out.println("*New password must be different than previous password*");
							else {
								b2.setPassword(newPassword);
								if (bsi.updateUser(b2) != null)
									System.out.println("*User updated successfully*");
								else 
									System.out.println("*User update failed*");
								break;
							}
						}
					}
					
					if (field.equals("super user") || field.equals("superuser")) {
						if (b2.getSuperUser() == 0) {
							System.out.println("Make user an admin? (yes or no)");
							String ans = scan.next();
							if (ans.equals("yes") || ans.equals("y")) {
								b2.setSuperUser(1);
								if (bsi.updateUser(b2) != null)
									System.out.println("*User is now an admin*");
								else 
									System.out.println("*User update failed*");
								break;
							}
							else if (ans.equals("no") || ans.equals("n"))
								break;
						}
					}
					break;
					
				case 10:
					while (true) {
						System.out.println("\nEnter the ID number of the user you wish to delete:");
						int idNum2 = scan.nextInt();
						BankUser b3 = bsi.getUser(idNum2);
						Account a2 = asi.getAccountByUserId(idNum2);
						
						if (b3 == null)
							System.out.println("*Invalid ID number*");
						else if (a2.getBalance() != 0.0f) {
							System.out.println("*User still has active accounts*"
									+ "\n*Could not delete user*");
							break;
						}
						else if (a2.getBalance() == 0.0f) {
							asi.deleteAccount(a2.getAccountId());
							if (bsi.deleteUser(b3)) {
								System.out.println("*User deleted*");
								break;
							}
							else {
								System.out.println("*Could not delete user*");
								break;
							}
						}
						else if (bsi.deleteUser(b3)) {
							System.out.println("*User deleted*");
							break;
						}
						else {
							System.out.println("*Could not delete user*");
							break;
						}
					}	
					break;
			
				case 11:
					loggedOut();
				}
			}
		}
		else {
			while (true) {
				System.out.println("\nWhat would you like to do next? (enter the number):"
						+ "\n1) View accounts and balances."
						+ "\n2) Create a new Account."
						+ "\n3) Delete empty Account."
						+ "\n4) Withdraw"
						+ "\n5) Deposit"
						+ "\n6) View Transactions"
						+ "\n7) Log Out");
				choice = scan.nextInt();
			
				switch (choice) {
			
				case 1:
					if (asi.getAccounts(bankUser.getId()).isEmpty())
						System.out.println("*You have no accounts at the moment*");
					else {
						List<Account> accountList = asi.getAccounts(bankUser.getId());
						
						System.out.println("------------------------------------------------------");
					    System.out.printf("%10s %10s %15s %15s", "Account", "User", "Name", "Balance");
					    System.out.println();
					    System.out.println("------------------------------------------------------");
						for (Account a : accountList) {
							System.out.format("%10d %10d %15s %15.2f",
					                a.getAccountId(), a.getUserId(), a.getName(), a.getBalance());
					        System.out.println();
						}
					    System.out.println("------------------------------------------------------");
					}
					break;
					
				case 2:
					System.out.println("\nEnter a name for your account:");
					String name = scan.next();
					System.out.println("Enter the starting balance (enter as a decimal):");
					float balance = scan.nextFloat();
					
					Account a = new Account(0000000, bankUser.getId(), name, balance);
					 
					if (asi.addAccount(a))
						System.out.println("Account successfully created");
					else
						System.out.println("Account could not be created");
					break;
					
				case 3:
					System.out.println("Enter the account ID number for the account you want to delete:"
							+ "\n(keep in mind you can only delete an account with a balance of $0.00)");
					accountId = scan.nextInt();
					
					if (asi.deleteAccount(accountId))
						System.out.println("*Account deleted successfully*");
					else
						System.out.println("*Account could not be deleted*");
					break;
					
				case 4:
					System.out.println("Enter the account number you want to withdraw from:");
					int accountid2 = scan.nextInt();
					System.out.println("Enter the amount you want to withdraw (enter as a decimal):");
					float amount = scan.nextFloat();
										
					if (asi.withdraw(amount, accountid2)) {
						Transaction t = new Transaction(bankUser.getId(), 0000000, accountid2, "Withdraw", -amount);
						tsi.addTransaction(t);
						System.out.println("*Withdraw successful*");
					}
					else
						System.out.println("*Withdraw failed*");
					break;
					
				case 5:
					System.out.println("Enter the account number you want to deposit into:");
					accountId = scan.nextInt();
					System.out.println("Enter the amount you want to deposit (enter as a decimal):");
					float amount2 = scan.nextFloat();
										
					if (asi.deposit(amount2, accountId)) {
						Transaction t1 = new Transaction(bankUser.getId(), 0000000, accountId, "Deposit", amount2);
						tsi.addTransaction(t1);
						System.out.println("*Deposit successful*");
					}
					else
						System.out.println("*Deposit failed*");
					break;
					
				case 6:
					if (tsi.viewTransactions(bankUser.getId()).isEmpty())
						System.out.println("*You have not made any transactions yet*");
					else
						System.out.println(tsi.viewTransactions(bankUser.getId()));
					break;
			
				case 7:
					loggedOut();
				}
			}
		}
	}
}
