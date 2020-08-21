package daos;

import java.util.List;

import models.Account;

public interface AccountDAO {

	public boolean addAccount(Account account);
	public void deleteAccount(int accountId);
	public Account getAccount(int accountId);
	public Account getAccountByUserId(int userId);
	public List<Account> getAccounts(int userId);
	public void withdraw(float amount, int accountId);
	public void deposit(float amount, int accountId);
}
