package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.User;
import util.JDBCConnection;

public class UserDAOimpl implements DAO<User>{
	
	public static Connection conn = JDBCConnection.getConnection();

	
	//create
	public boolean addT(User t) {
		
		try {
			String sql = "CALL add_customer(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, t.getUsername());
			cs.setString(2, t.getPassword());

			cs.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	//read
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		
		try {
			String sql = "SELECT * FROM customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					User a = new User();
					// may need to change the args
					a.setCustomerID(rs.getInt("ID"));
					a.setUsername(rs.getString("USERNAME"));
					a.setPassword(rs.getString("PASSWORD"));
					
					users.add(a);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		return users;
	}
	
	public User getT(int id) {
		try {
			
			String sql = "SELECT * FROM customer WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User a = new User();
				a.setCustomerID(rs.getInt("ID"));
				a.setUsername(rs.getString("USERNAME"));
				a.setPassword(rs.getString("PASSWORD"));
				
				return a;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public User getT(User t) {
		try {
			
			String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.executeQuery();
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User a = new User();
				a.setCustomerID(rs.getInt("ID"));
				a.setUsername(rs.getString("USERNAME"));
				a.setPassword(rs.getString("PASSWORD"));
				
				return a;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}


	//update
	public boolean updateT(User t) {

		try {
			String sql = "UPDATE customer SET username = ?, password = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, Integer.toString(t.getCustomerID()));
			
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	//delete
	public boolean deleteT(User u) {
		try {
			String ins = "DELETE BankAccount WHERE cust_id = ?";
			PreparedStatement ps = conn.prepareStatement(ins);
			ps.setInt(1, u.getCustomerID());
			ps.executeQuery();
			
			String del = "DELETE customer WHERE id = ?";
			ps = conn.prepareStatement(del);
			ps.setInt(1, u.getCustomerID());
			ps.executeQuery();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean deleteT(int id) {
		try {
			//likely have to change items in select statement
			String ins = "DELETE customer WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(ins);
			ps.setInt(1, id);
			ps.executeQuery();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
