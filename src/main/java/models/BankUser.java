package models;

public class BankUser {

	int id;
	String username;
	String password;
	int superUser;
	
	public BankUser(int id, String username, String password, int superUser) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.superUser = superUser;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSuperUser() {
		return superUser;
	}

	public void setSuperUser(int superUser) {
		this.superUser = superUser;
	}

	@Override
	public String toString() {
		return "BankUser [ID =" + id + ", username=" + username + ", password=" + password + ", superUser=" + superUser + "]\n";
	}
}
