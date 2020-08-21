package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.BankUser;
import util.JDBCConnection;

public class BankDAOimpl implements BankDAO {

	public boolean addUser(BankUser bankUser) {
		String sql = "call add_user(?, ?, ?)";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, bankUser.getUsername());
			cs.setString(2, bankUser.getPassword());
			cs.setInt(3, 0);
			
			cs.execute();
			
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}

	public BankUser getUser(String username) {
		String sql = "select * from bankUsers where username = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return new BankUser(
						rs.getInt("userid"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getInt("superuser"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public BankUser getUser(int idNum) {
		String sql = "select * from bankUsers where userid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idNum);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return new BankUser(
						rs.getInt("userid"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getInt("superuser"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public BankUser getUserByPass(String password) {
		String sql = "select * from bankUsers where pass = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return new BankUser(
						rs.getInt("userid"),
						rs.getString("username"),
						rs.getString("pass"),
						rs.getInt("superuser"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<BankUser> getAllUsers() {
		String sql = "select * from bankusers";
		
        try {
        	Connection con = JDBCConnection.getConnection();
            List<BankUser> result = new ArrayList<BankUser>();
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                BankUser p = new BankUser(
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getInt("superuser"));
                
                result.add(p);
            }
            
            return result;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}

	public BankUser updateUser(BankUser bankUser) {
		String sql = "update bankusers set username = ?, pass = ?, superuser = ? where userid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, bankUser.getUsername());
			cs.setString(2, bankUser.getPassword());
			cs.setInt(3, bankUser.getSuperUser());
			cs.setInt(4, bankUser.getId());
			
			cs.execute();	
			
			return bankUser;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean deleteUser(BankUser bankUser) {
		String sql = "delete from bankusers where userid = ?";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, bankUser.getId());
			
			cs.execute();	
			
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
