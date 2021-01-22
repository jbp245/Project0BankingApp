package services;

import java.util.List;

import daos.BankAccountDAOimpl;
import daos.DAO;
import daos.TransactionDAOimpl;
import daos.UserDAOimpl;
import entities.BankAccount;
import entities.Transaction;
import entities.User;

public class UserServiceImpl implements UserService {

	public DAO<User> udao = new UserDAOimpl();
	public DAO<BankAccount> bdao = new BankAccountDAOimpl();
	public DAO<Transaction> tdao = new TransactionDAOimpl();
	
	public boolean addT(User t) {
		return udao.addT(t);
	}

	public List<User> getAll() {
		return udao.getAll();
	}

	public User getT(int i) {
		return udao.getT(i);
	}

	public User getT(User t) {
		return udao.getT(t);
	}

	public boolean updateT(User t) {
		return udao.updateT(t);
	}

	public boolean deleteT(User t) {
		return udao.deleteT(t);
	}

	public boolean deleteT(int id) {
		return udao.deleteT(id);
	}

	public boolean enrollUser(String username, String password) {
		return udao.addT(new User(username, password));
	}

	public User login(String username, String password) {
		for (User target : udao.getAll()) {
			if ((udao.getT(target).getUsername().equals(username)) && (udao.getT(target).getPassword().equals(password))) {
				System.out.println("Login successful");
				return udao.getT(new User(username, password));
			}
		}
		System.out.println("username and/or password not found.");
		return null;
	}

	public boolean logout() {
		return false;
	}

	public boolean openAccount(User user) {
		return bdao.addT(new BankAccount(user.getCustomerID()));
	}
	

	public boolean viewTransactionHistory(User user) {
		if (tdao.getAll().size() == 0) {
			System.out.println("no prior transactions");
			return false;
			}
		for (Transaction t : tdao.getAll()) {
			System.out.print(t.getStatement() + " occured at " + t.getTime());
		}
		return true;
	}

}
