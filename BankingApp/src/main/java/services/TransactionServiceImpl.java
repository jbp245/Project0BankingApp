/**
 * 
 */
package services;

import java.util.List;

import daos.DAO;
import daos.TransactionDAOimpl;

import entities.Transaction;

public class TransactionServiceImpl implements TransactionService {

	public DAO<Transaction> tdao = new TransactionDAOimpl();
	
	public boolean addT(Transaction t) {
		return tdao.addT(t);
	}

	public List<Transaction> getAll() {
		return tdao.getAll();
	}

	public Transaction getT(int i) {
		return tdao.getT(i);
	}

	public Transaction getT(Transaction t) {
		return tdao.getT(t);
	}

	public boolean updateT(Transaction t) {
		return tdao.updateT(t);
	}

	public boolean deleteT(Transaction t) {
		return tdao.deleteT(t);
	}

	public boolean deleteT(int id) {
		return tdao.deleteT(id);
	}

	public boolean log(Transaction t) {
		return tdao.addT(t);
	}

}
