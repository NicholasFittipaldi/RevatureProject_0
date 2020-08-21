package services;

import java.util.List;

import models.Transaction;

public interface TransactionServices {

	public boolean addTransaction(Transaction trans);
	public List<Transaction> viewTransactions(int userId);
}
