package services;

import java.util.List;

import entities.User;

public interface UserAdminService extends Service<User>{
	
	// login
	
	User login(String username, String password);
	
	boolean logout();
	
	// user methods
	
	List<User> viewAllUsers();
	
	boolean createUser(User user);
	
	boolean updateUser(User user);
	
	boolean deleteUser(User user);
	
	//transaction methods
	
//	List<Transaction> viewAllTrans();
//	
//	boolean recordTransaction(Transaction t);
	
}
