
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnector {

	private static Connection con;
	private static String dbURL;
	private static String dbUSERNAME;
	private static String dbPASSWORD;
	private static String URL;
	private static String port;
	private static String dbName;

	public DatabaseConnector(String dbUSERNAME, String dbPASSWORD, String URL, String port, String dbName) {
		this.dbUSERNAME = dbUSERNAME;
		this.dbPASSWORD = dbPASSWORD;
		this.URL = URL;
		this.port = port;
		this.dbName = dbName;
	}

	public static Connection connectDB() throws SQLException {
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";

		Properties properties = new Properties();
		properties.setProperty("user", dbUSERNAME);
		properties.setProperty("password", dbPASSWORD);
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");

		try {
			con = DriverManager.getConnection(dbURL, properties);
			return con;
		} catch (SQLException e) {
			throw new SQLException("Error connecting to the database", e);
		}
	}

	public static void close() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("Error closing the database connection: " + e.getMessage());
		}
	}

	public static void executeQuery(String sql) {
		try (Connection connection = connectDB();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
