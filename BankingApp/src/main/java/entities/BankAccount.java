package entities;

public class BankAccount {

	private int acctNum;
	
	private int ownerID;
	private double balance;
	
	//bean default constructor
	public BankAccount() {
		super(); 
	}
	
	public BankAccount(int acctNum) {
		super();
		this.acctNum = 0;
		this.balance = 0;
		this.ownerID = acctNum;
	}
	
	public BankAccount(int acctNum, double balance) {
		super();
		this.acctNum = 0;
		this.balance = balance;
		this.ownerID = acctNum;
	}
	
//	public BankAccount
	
	public int getAcctNum() {
		return acctNum;
	}
	
	public void setAcctNum(int acctNum) {
		this.acctNum = acctNum;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void adjustBalance(double balance) {
		this.balance += balance;
	}
	
	public boolean isBlank() {
		return balance == 0;
	}

	@Override
	public String toString() {
		return "BankAccount [acctNum=" + acctNum + ", customer_id= " + ownerID +", balance=" + balance + "]";
	}

	/**
	 * @return the ownerID
	 */
	public int getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID the ownerID to set
	 */
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	
}
