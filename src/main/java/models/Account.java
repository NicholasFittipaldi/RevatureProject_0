package models;

import java.util.ArrayList;

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
	
	public String balanceToString() {
		ArrayList<Character> balanceString = new ArrayList<Character>();
		String string = "";
		int count = 0;
		
		for (char c : String.format("%.2f", balance).toCharArray())
			balanceString.add(c);
		
		if (balance > 999.00) {
			for (int i = balanceString.size() - 4; i >= 0; i--) {
				count++;
				if (count == 3 && i == 0)
					break;
				else if (count == 3) {
					balanceString.add(i, ',');
					count = 0;
				}
			}
		}
		
		for (char c : balanceString)
			string = string + c;
				
		
		return "$" + string;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", name=" + name + ", balance=$" + balance
				+ "]\n";
	}
}
