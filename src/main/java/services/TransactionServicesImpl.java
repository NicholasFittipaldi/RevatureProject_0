package services;

import java.util.List;

import daos.TransactionDAOimpl;
import models.Transaction;

public class TransactionServicesImpl implements TransactionServices {

	TransactionDAOimpl td = new TransactionDAOimpl();
	
	public boolean addTransaction(Transaction trans) {
		if (td.addTransaction(trans))
			return true;
		else
			return false;
	}

	public List<Transaction> viewTransactions(int userId) {
		List<Transaction> t = td.viewTransactions(userId);
		return t;
	}
}
