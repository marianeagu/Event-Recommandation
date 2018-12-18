package database;
import database.mysql.MySQLConnection;
import database.mongodb.MongoDBConnection;

/**
 * @author neagum
 *
 */

public class DatabaseConnectionFactory {
	// This should change based on the pipeline.
	private static final String DEFAULT_DB = "mysql";

	// Create a DBConnection based on given db type.
	public static DatabaseConnection getDBConnection(String db) {
		switch (db) {
		case "mysql":
			return MySQLConnection.getInstance();
		// You may try other dbs and add them here.
		case "mongodb":
			return MongoDBConnection.getInstance();
		// You may try other dbs and add them here.
		default:
			throw new IllegalArgumentException("Invalid db " + db);
		}
	}

	// This is overloading not overriding.
	public static DatabaseConnection getDBConnection() {
		return getDBConnection(DEFAULT_DB);
	}
}
