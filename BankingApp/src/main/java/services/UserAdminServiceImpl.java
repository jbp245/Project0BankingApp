package services;

import java.util.List;

import daos.DAO;
import daos.UserDAOimpl;

import entities.User;

public class UserAdminServiceImpl implements UserAdminService {

	public DAO<User> udao = new UserDAOimpl();

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

	public User login(String username, String password) {
		for (User target : udao.getAll()) {
			if ((target.getUsername().equals("username")) && (target.getPassword().equals("password"))) {
				System.out.println("Admin login succesful.");
				return target;
			}
		}
		System.out.println("username and/or password not found.");
		return null;
	}

	public boolean logout() {
		return false;
	}

	public List<User> viewAllUsers() {
		return udao.getAll();
	}

	public boolean createUser(User t) {
		return udao.addT(t);
	}

	public boolean updateUser(User t) {
		return udao.updateT(t);
	}

	public boolean deleteUser(User t) {
		return udao.deleteT(t);
	}

}
