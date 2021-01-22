package services;

import entities.User;

public interface UserService extends Service<User>{
	
	boolean enrollUser(String username, String password);
	
	User login(String username, String password);
	
	boolean logout();
	
	boolean openAccount(User user);
	
	// boolean closeAccount(User user);
	
	boolean viewTransactionHistory(User user);
	
}
