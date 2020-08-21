package models;

public class Transaction {

	int userId;
	int transId;
	int accountId;
	String action;
	float amount;
	
	public Transaction(int userId, int transId, int accountId, String action, float amount) {
		super();
		this.userId = userId;
		this.transId = transId;
		this.accountId = accountId;
		this.action = action;
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [userId=" + userId + ", transId=" + transId + ", accountId=" + accountId + ", action="
				+ action + ", amount=" + amount + "]\n";
	}
}
