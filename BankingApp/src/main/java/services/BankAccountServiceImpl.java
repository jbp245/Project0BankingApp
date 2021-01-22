/**
 * 
 */
package services;

import java.util.List;

import daos.BankAccountDAOimpl;
import daos.DAO;

import entities.BankAccount;

public class BankAccountServiceImpl implements BankAccountService {

	public DAO<BankAccount> bdao = new BankAccountDAOimpl();
	
	public boolean addT(BankAccount t) {
		return bdao.addT(t);
	}

	public List<BankAccount> getAll() {
		return bdao.getAll();
	}

	public BankAccount getT(int i) {
		return bdao.getT(i);
	}

	public BankAccount getT(BankAccount t) {
		return bdao.getT(t);
	}

	public boolean updateT(BankAccount t) {
		return bdao.updateT(t);
	}

	public boolean deleteT(BankAccount t) {
		return bdao.deleteT(t);
	}

	public boolean deleteT(int id) {
		return bdao.deleteT(id);
	}

	public boolean withdraw(BankAccount ba, double amount) {
		if (ba.getBalance() >= amount) {
			ba.adjustBalance(-amount);
			System.out.println("Withdraw of $" + amount + " successful");
			return bdao.updateT(ba);
		}
		System.out.println("Insufficient funds.");
		return false;
	}

	public boolean deposit(BankAccount ba, double amount) {
		if (amount > 0) {
			ba.adjustBalance(amount);
			System.out.println("Deposit of $" + amount + " successful");
			return bdao.updateT(ba);
		}
		System.out.println("must deposit more than $0.0");
		return false;
	}

	public boolean isEmpty(BankAccount ba) {
		return bdao.getT(ba).isBlank();
	}

}
