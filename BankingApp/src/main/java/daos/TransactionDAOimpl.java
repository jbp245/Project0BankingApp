package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Transaction;
import util.JDBCConnection;

public class TransactionDAOimpl implements DAO<Transaction> {
	
	public static Connection conn = JDBCConnection.getConnection();

	// create
	public boolean addT(Transaction t) {
		
		try {
			String sql = "CALL add_transaction(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(t.getUser_id()));
			cs.setString(2, Integer.toString(t.getBankacc_id()));
			cs.setString(3, t.getStatement());
			
			cs.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// read
	public List<Transaction> getAll() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			String sql = "SELECT * FROM transactions";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					Transaction tran = new Transaction();
					tran.setId(rs.getInt("ID"));
					tran.setUser_id(rs.getInt("CUST_ID"));
					tran.setBankacc_id(rs.getInt("BA_ID"));
					tran.setStatement(rs.getString("DESCRIPTION"));
					
					transactions.add(tran);
					
				}
				
				return transactions;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return transactions;
			}
	}
	
	public Transaction getT(int id) {
		try {
			
			String sql = "SELECT * FROM transactions WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Transaction a = new Transaction();
				a.setId(rs.getInt("ID"));
				a.setUser_id(rs.getInt("CUST_ID"));
				a.setBankacc_id(rs.getInt("BANKACC_ID"));
				a.setStatement(rs.getString("DESCRIPTION"));

				
				return a;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public Transaction getT(Transaction t) {
		// TODO Auto-generated method stub
		return null;
	}

	//update
	public boolean updateT(Transaction t) {

		try {
			String sql = "UPDATE customer SET description = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, t.getStatement());
			ps.setString(2, Integer.toString(t.getId()));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	//delete
	public boolean deleteT(Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteT(int id) {
		try {
			//likely have to change items in select statement
			String ins = "INSERT INTO archived_transactions SELECT id, username, password FROM customer WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(ins);
			ps.setInt(1, id);
			
			String del = "DELETE transaction WHERE id = ?";
			ps = conn.prepareStatement(del);
			ps.setInt(1, id);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
