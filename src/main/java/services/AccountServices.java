package services;

import java.util.List;

import models.Account;

public interface AccountServices {

	public boolean addAccount(Account account);
	public boolean deleteAccount(int accountId);
	public List<Account> getAccounts(int userId);
	public Account getAccountByUserId(int userId);
	public boolean withdraw(float amount, int accountId);
	public boolean deposit(float amount, int accountId);
}
