package services;

import java.util.List;

import daos.BankDAOimpl;
import models.BankUser;

public class BankServicesImpl implements BankServices {
	
	BankDAOimpl bd = new BankDAOimpl(); 

	public boolean addUser(BankUser bankUser) {
		if (bd.addUser(bankUser))
			return true;
		else
			return false;
	}

	public BankUser getUser(String username) {
		BankUser b = bd.getUser(username);
		return b;
	}
	
	public BankUser getUser(int idNum) {
		BankUser b = bd.getUser(idNum);
		return b;
	}
	
	public BankUser getUserByPass(String password) {
		BankUser b = bd.getUserByPass(password);
		return b;
	}

	public List<BankUser> getAllUsers() {
		List<BankUser> b = bd.getAllUsers();
		return b;
	}

	public BankUser updateUser(BankUser bankUser) {
		BankUser b = bd.updateUser(bankUser);
		return b;
	}

	public boolean deleteUser(BankUser bankUser) {
		if (bd.deleteUser(bankUser))
			return true;
		else
			return false;
	}

}
