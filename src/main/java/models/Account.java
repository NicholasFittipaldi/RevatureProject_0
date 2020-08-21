package models;

public class Account {
	
	int accountId;
	int userId;
	String name;
	float balance;
	
	public Account(int accountId, int userId, String name, float balance) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.name = name;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", name=" + name + ", balance=$" + balance
				+ "]\n";
	}
}
