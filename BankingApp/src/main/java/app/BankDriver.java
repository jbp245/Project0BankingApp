package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.BankAccount;
//import entities.Transaction;
import entities.User;

import services.BankAccountService;
import services.BankAccountServiceImpl;
//import services.TransactionService;
//import services.TransactionServiceImpl;
import services.UserService;
import services.UserServiceImpl;

public class BankDriver {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static User current_user = null;
	private static List<BankAccount> user_accounts = new ArrayList<BankAccount>();
	//private static List<Transaction> user_transactions = new ArrayList<Transaction>();
	
	private static UserService userve = new UserServiceImpl();
	private static BankAccountService bserve = new BankAccountServiceImpl();
	//private static TransactionService tserve = new TransactionServiceImpl();
	
	public static void main(String[] args) {
		loginMenu();
		sc.close();
	}
	
	private static void consolidateLoginMenu() {
		System.out.println("Enter 1 to register a new account");
		System.out.println("Enter 2 to login to an existing user");
		System.out.println("Enter 3 to quit");
	}

	private static void loginMenu() {
		consolidateLoginMenu();
		int login_sc = Integer.parseInt(sc.next());
		switch (login_sc){
		// register an account
		case 1:
			register();
		//login
		case 2:
			logged();
		//quit
		case 3:
			System.out.println("JDBC Connection with url: " + System.getenv("rds1url") + " username: " + System.getenv("rds1user") + " password: "+ System.getenv("rds1pass") + " has been closed");
			java.lang.System.exit(0);
		}
	}
	
	private static void logged() {
		System.out.println("Please input your username:");
		String username = sc.next();
		System.out.println("Please input your password:");
		String password = sc.next();
		current_user = userve.login(username, password);
		if ((current_user.getUsername().equals("admin")) && (current_user.getPassword().equals("password"))) {
			superUserMenu();
		} else if ((current_user.getUsername().equals(username)) && (current_user.getPassword().equals(password))) {
			current_user = userve.login(username, password);
			userMenu();
		}
		current_user = null;
		loginMenu();
	}

	//works perfectly
	private static void consolidateSuperUserMenu() {
		System.out.println("");
		System.out.println("Enter 1 to create a new user");
		System.out.println("Enter 2 to retrieve an existing user");
		System.out.println("Enter 3 to retrieve all users");
		System.out.println("Enter 4 to update an existing user");
		System.out.println("Enter 5 to delete/archive a user");
		System.out.println("Enter 6 to logout of admin");
	}
	
	private static void superUserMenu() {
		consolidateSuperUserMenu();
		int super_choice = Integer.parseInt(sc.next());
		switch (super_choice) {
		case 1:
			System.out.println("Please input a username:");
			String username = sc.next();
			System.out.println("Please input a password:");
			String password = sc.next();
			if (userve.addT(new User(username, password))) {
				System.out.println("new user added successfully");
			} else {
				System.out.println("new user unsuccessfully added");
			}
			superUserMenu();
		case 2:
			System.out.println("Please enter the id of the user to be retrieved");
			int cust_id = sc.nextInt();
			userve.getT(cust_id);
			System.out.println(userve.getT(cust_id));
			superUserMenu();
		case 3:
			userve.getAll();
			System.out.println(userve.getAll());
			superUserMenu();
		case 4:
			System.out.println("Please enter the id of the user to be updated");
			cust_id = sc.nextInt();
			
			User temp = userve.getT(cust_id);
			System.out.println(temp.toString() + "temp success");
			
			System.out.println("Please input a new username for " + temp.getUsername() + ":");
			username = sc.next();
			temp.setUsername(username);
			System.out.println("Please input a new password for " + temp.getUsername() + ":");
			password = sc.next();
			temp.setPassword(password);
			
			userve.updateT(temp);
			System.out.println(userve.getT(cust_id));
			System.out.println("user updated");
			superUserMenu();
		case 5:
			System.out.println("Please enter the id of the user to be deleted");
			cust_id = sc.nextInt();
			userve.deleteT(cust_id);
			superUserMenu();
		case 6:
			current_user = null;
			loginMenu();
		}
	}
	
	//works perfectly
	private static void consolidateUserMenu() {
		System.out.println("");
		System.out.println("User Menu:");
		System.out.println(current_user.toString());
		System.out.println("Enter 1 to view existing bank accounts");
		System.out.println("Enter 2 to create a new bank account");
		System.out.println("Enter 3 to delete an existing account");
		System.out.println("Enter 4 to withdraw or deposit into an account");
		//System.out.println("Enter 5 to retrieve all previous transactions");
		System.out.println("Enter 6 to logout");
	}
	
	private static void userMenu() {
		consolidateUserMenu();
		
		int super_choice = Integer.parseInt(sc.next());
		switch (super_choice) {
		//get all of a user's bankaccounts
		case 1:
			user_accounts.clear();
			List<BankAccount> temp = bserve.getAll();
			for (BankAccount ba: temp) {
				if (ba.getOwnerID() == current_user.getCustomerID()) {
					user_accounts.add(ba);
				}
			}
			if (user_accounts.size() == 0) {
				System.out.println("no bankaccounts available");
			}
			else {
			System.out.print(user_accounts);
			}
			userMenu();
		//add a new bankaccount
		case 2:
			BankAccount temp_ba = new BankAccount(current_user.getCustomerID());
			user_accounts.add(temp_ba);
			bserve.addT(temp_ba);
			System.out.println("new bank account created");
			userMenu();
		//delete a blank bankaccount
		case 3:
			System.out.println("select an account to delete");
			int acctNum = sc.nextInt();
			if (bserve.deleteT(acctNum)) {
				System.out.println("deletion successful");
			} else {
				System.out.println("deletion unsuccessful");
			}	
			userMenu();
		//withdraw or deposit money
		case 4:
			System.out.println("Enter 1 to Withdraw OR 2 to Deposit");
			super_choice = Integer.parseInt(sc.next());
			switch (super_choice) {
			case 1:
				System.out.println("enter the amount you would like to withdraw");
				double amount = sc.nextDouble();
				bserve.withdraw(user_accounts.get(0), amount);
				//tserve.log(new Transaction(current_user.getCustomerID(),user_accounts.get(0).getAcctNum(),"withdrew: " + amount));
				break;
			case 2:
				System.out.println("enter the amount you would like to deposit");
				amount = sc.nextDouble();
				bserve.deposit(user_accounts.get(0), amount);
				//tserve.log(new Transaction(current_user.getCustomerID(),user_accounts.get(0).getAcctNum(),"deposited: " + amount));
			}
			userMenu();
		//view transactions
//		case 5:
//			//userve.viewTransactionHistory(current_user);
//			logTransaction();
//			userMenu();
//		//logout
		case 6:
			current_user = null;
			user_accounts = null;
			loginMenu();
		}
		
	}
	
	//works perfectly
	private static void register() {
		System.out.println("Register a new account \n");
		System.out.println("Please input your new username:");
		String username = sc.next();
		System.out.println("Please input your new password:");
		String password = sc.next();
		if (userve.addT(new User(username, password))){
			System.out.println("");
			System.out.println("successfully registered " + username);
			//userve.addT(new User(username, password));
		} else {
			System.out.println("");
			System.out.println("registration unsuccessful");
		}
		loginMenu();
	}
	
// private static void logTransaction() {
//		user_transactions.clear();
//		List<Transaction> temp = tserve.getAll();
//		for (Transaction tran: temp) {
//			if (tran.getUser_id() == current_user.getCustomerID()) {
//				user_transactions.add(tran);
//			}
//		}
//		if (user_transactions.size() == 0) {
//			System.out.println("no prior transactions");
//		}
//		else {
//		System.out.print(user_transactions);
//		}
//		userMenu();
//	}
	
}


