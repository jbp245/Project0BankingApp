package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

	/*
	 * we are going to observe a single connection object withing htis class., if no
	 * cvonnect exists, we will create one else return current
	 */

	private static Connection conn = null;

	public static Connection getConnection() {

		try {
			if (conn == null) {
				// make a new connection
				
				/* Oracle added a hotfix to ensure that ojdbc drive would correctly load at the beginning of app existing */
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// to establish a connection we need 3 credentials // url(endpoint, username, password
				return conn = DriverManager.getConnection(System.getenv("rds1url"),System.getenv("rds1user"),System.getenv("rds1pass"));
			} else {
				// return preestablished connection
				return conn;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
