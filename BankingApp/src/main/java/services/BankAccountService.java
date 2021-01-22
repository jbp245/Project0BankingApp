package services;

import entities.BankAccount;

public interface BankAccountService extends Service<BankAccount> {

	boolean withdraw(BankAccount ba, double amount);
	
	boolean deposit(BankAccount ba, double amount);
	
	boolean isEmpty(BankAccount ba);

}
