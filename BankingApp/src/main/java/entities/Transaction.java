package entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Transaction {
	
	private int id;
	private int user_id;
	private int bankacc_id;
	private String statement;
	private Timestamp time;
	
	//bean default constructor
	public Transaction() {
		super();
	}
	
	public Transaction(String statement) {
		super();
		this.statement = statement;
		this.time = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
	}
	
	public Transaction(int user_id, int bankacc_id, String statement) {
		super();
		this.user_id = user_id;
		this.bankacc_id = bankacc_id;
		this.statement = statement + " " + Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
		
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the bankacc_id
	 */
	public int getBankacc_id() {
		return bankacc_id;
	}

	/**
	 * @param bankacc_id the bankacc_id to set
	 */
	public void setBankacc_id(int bankacc_id) {
		this.bankacc_id = bankacc_id;
	}

	/**
	 * @return the statement
	 */
	public String getStatement() {
		return statement;
	}

	/**
	 * @param statement the statement to set
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}

	/**
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Transaction [statement=" + statement + ", time=" + time + "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
