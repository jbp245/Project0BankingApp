package services;

import entities.Transaction;

public interface TransactionService extends Service<Transaction> {

	boolean log(Transaction t);
}
