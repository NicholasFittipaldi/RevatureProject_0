package daos;

import java.util.List;

import models.Transaction;

public interface TransactionDAO {

	public boolean addTransaction(Transaction trans);
	public List<Transaction> viewTransactions(int userId);
}
