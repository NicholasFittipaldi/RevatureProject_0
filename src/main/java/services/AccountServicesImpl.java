package services;

import java.util.List;

import daos.AccountDAOimpl;
import models.Account;

public class AccountServicesImpl implements AccountServices {
	
	AccountDAOimpl ad = new AccountDAOimpl();

	public boolean addAccount(Account account) {
		if (ad.addAccount(account))
			return true;
		else
			return false;
	}

	public boolean deleteAccount(int accountId) {
		Account a = ad.getAccount(accountId); 
		
		if (a.getBalance() == 0.0) {
			ad.deleteAccount(accountId);
			return true;
		}
		else
			return false;
	}

	public List<Account> getAccounts(int userId) {
		List<Account> a = ad.getAccounts(userId);
		return a;
	}
	
	public Account getAccountByUserId(int userId) {
		Account a = ad.getAccountByUserId(userId); 
		return a;
	}

	public boolean withdraw(float amount, int accountId) {
		Account a = ad.getAccount(accountId);
		
		if (a == null) {
			System.out.println("*Account ID " + accountId + " does not exist*");
			return false;
		}
		else if (amount > a.getBalance()) {
			System.out.println("*Withdraw amount" + amount + " is greater than current balance*");
			return false;
		}
		else {
			ad.withdraw(a.getBalance() - amount, accountId);
			return true;
		}
	}

	public boolean deposit(float amount, int accountId) {
		Account a = ad.getAccount(accountId);
		
		if (a == null) {
			System.out.println("*Account ID " + accountId + " does not exist*");
			return false;
		}
		else if (amount < 0) {
			System.out.println("*You entered a negative amount*");
			return false;
		}
		else {
			ad.deposit(a.getBalance() + amount, accountId);
			return true;
		}
	}
}
