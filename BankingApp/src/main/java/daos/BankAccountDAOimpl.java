package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.BankAccount;
import util.JDBCConnection;

public class BankAccountDAOimpl implements DAO<BankAccount>{

	public static Connection conn = JDBCConnection.getConnection();
	
	//private final double ZERO_BALANCE = 0.0;

	//create
	public boolean addT(BankAccount t) {
		System.out.println(t.toString());
		try {
			String sql = "CALL add_bankaccount(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(t.getOwnerID()));

			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//read
	public List<BankAccount> getAll() {
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		
		try {
			String sql = "SELECT * FROM bankacc";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					BankAccount ba = new BankAccount();
					ba.setAcctNum(rs.getInt("ID"));
					ba.setOwnerID(rs.getInt("CUST_ID"));
					ba.setBalance(rs.getDouble("BALANCE"));
				
					accounts.add(ba);
				}
				
				return accounts;
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		return accounts;
	}

	public BankAccount getT(int cust_id) {
		try {
			
			String sql = "SELECT * FROM bankacc WHERE cust_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(cust_id));
			ps.executeQuery();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				BankAccount ba = new BankAccount();
				ba.setAcctNum(rs.getInt("ID"));
				ba.setOwnerID(rs.getInt("CUST_ID"));
				ba.setBalance(rs.getDouble("BALANCE"));
				
				return ba;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public BankAccount getT(BankAccount t) {
		try {
			
			String sql = "SELECT * FROM bankacc WHERE cust_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(t.getOwnerID()));
			ps.executeQuery();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				BankAccount ba = new BankAccount();
				ba.setAcctNum(rs.getInt("ID"));
				ba.setOwnerID(rs.getInt("CUST_ID"));
				ba.setBalance(rs.getDouble("BALANCE"));
				
				return ba;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	//update
	public boolean updateT(BankAccount t) {

		try {
			String sql = "UPDATE bankacc SET balance = ? WHERE cust_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Double.toString(t.getBalance()));
			ps.setString(2, Integer.toString(t.getOwnerID()));
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//delete
	public boolean deleteT(int id) {
		try {
			String sql = "DELETE bankacc WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteT(BankAccount t) {
		try {
			//likely have to change items in select statement
			String ins = "INSERT INTO archived_bankacc SELECT id, cust_id, balance FROM customer WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(ins);
			ps.setInt(1, t.getAcctNum());
			ps.execute();
			String del = "DELETE bankacc WHERE id = ?";
			ps = conn.prepareStatement(del);
			ps.setInt(1, t.getAcctNum());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
}
