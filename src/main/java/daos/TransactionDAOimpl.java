package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Transaction;
import util.JDBCConnection;

public class TransactionDAOimpl implements TransactionDAO {

	public boolean addTransaction(Transaction trans) {
		String sql = "call add_transaction(?, ?, ?, ?)";
		
		try {
			Connection con = JDBCConnection.getConnection();
			
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, trans.getUserId());
			cs.setInt(2, trans.getAccountId());
			cs.setString(3, trans.getAction());
			cs.setFloat(4, trans.getAmount());
			
			cs.execute();
			
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Transaction> viewTransactions(int userId) {
		String sql = "select * from transactions where userid = ?";
		
        try {
        	Connection con = JDBCConnection.getConnection();
            List<Transaction> result = new ArrayList<Transaction>();
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Transaction t = new Transaction(
                        rs.getInt("userid"),
                        rs.getInt("transid"),
                        rs.getInt("accountid"),
                        rs.getString("action"),
                        rs.getFloat("amount"));
                
                result.add(t);
            }
            
            return result;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
}
