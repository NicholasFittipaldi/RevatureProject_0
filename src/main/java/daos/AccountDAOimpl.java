package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import util.JDBCConnection;

public class AccountDAOimpl implements AccountDAO {
	
	public boolean addAccount(Account account) {
		String sql = "call add_account(?, ?, ?)";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, account.getUserId());
			cs.setString(2, account.getName());
			cs.setFloat(3, account.getBalance());
			
			cs.execute();
			
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}			
	}
	
	public void deleteAccount(int accountId) {
		String sql = "delete from bankAccounts where accountid = ? and balance = 0.0";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, accountId);
			
			cs.execute();				
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	public Account getAccount(int accountId) {
		String sql = "select * from bankAccounts where accountid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return new Account(
						rs.getInt("userid"),
						rs.getInt("accountid"),
						rs.getString("name"),
						rs.getFloat("balance"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Account getAccountByUserId(int userId) {
		String sql = "select * from bankAccounts where userid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return new Account(
						rs.getInt("userid"),
						rs.getInt("accountid"),
						rs.getString("name"),
						rs.getFloat("balance"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Account> getAccounts(int userId) {
		String sql = "select * from bankAccounts where userid = ?";
		
        try {
        	Connection con = JDBCConnection.getConnection();
            List<Account> result = new ArrayList<Account>();
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Account p = new Account(
                        rs.getInt("accountid"),
                        rs.getInt("userid"),
                        rs.getString("name"),
                        rs.getFloat("balance"));
                
                result.add(p);
            }
            
            return result;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

	public void withdraw(float amount, int accountId) {
		String sql = "update bankAccounts set balance = ? where accountid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setFloat(1, amount);
			cs.setInt(2, accountId);
			
			cs.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deposit(float amount, int accountId) {
		String sql = "update bankAccounts set balance = ? where accountid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setFloat(1, amount);
			cs.setInt(2, accountId);
			
			cs.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
