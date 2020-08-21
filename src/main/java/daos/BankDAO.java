package daos;

import java.util.List;

import models.BankUser;

public interface BankDAO {

	public boolean addUser(BankUser bankUser);
	public BankUser getUser(String username);
	public BankUser getUser(int idNum);
	public BankUser getUserByPass(String password);
	
	public List<BankUser> getAllUsers();
	public BankUser updateUser(BankUser bankUser);
	public boolean deleteUser(BankUser bankUser);
}
