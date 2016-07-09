package quizzer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DBConnection {
	private static final String ACCOUNT = MyDBInfo.MYSQL_USERNAME;
	private static final String PASSWORD = MyDBInfo.MYSQL_PASSWORD;
	private static final String SERVER = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static final String DATABASE = MyDBInfo.MYSQL_DATABASE_NAME;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://";
	private String tableName;
	private String[] colNames, colTypes;
	private List<List<Object>> data;
	private int numRows;
	
	protected DBConnection() {
	}
	
	protected DBConnection(String newTableName, String[] newColNames,
			String[] newColTypes) {
		this();
		initializeConnection(newTableName, newColNames, newColTypes);
	}
	
	protected void createTable() {
		String query = makeCreationQuery();
		
		try {
			executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteTable() {
		String query = "DROP TABLE " + tableName + ";";
		
		try {
			executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void loadTable() {
		String query = "SELECT * FROM " + tableName + ";";
		ResultSet rs = null;
		
		try {
			rs = executeQuery(query);
			while(rs.next())
				for (int i = 0; i < colNames.length; i++)
					data.get(i).add(rs.getObject(i));
			rs.previous();
			numRows = rs.getRow();
		} catch (Exception e) {
			if (e.getMessage().equals("Table '" + DATABASE
					+ '.' + "tableName" + "' doesn't exist")) {
				createTable();
				loadTable();
			} else e.printStackTrace();
		}
	}
	
	protected int getRowCount() {
		return numRows;
	}

	protected int getColumnCount() {
		return colNames.length;
	}

	protected String getColumnName(int columnIndex) {
		return colNames[columnIndex];
	}
	
	protected ResultSet executeQuery(String query) throws Exception {
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(
					DB_URL + SERVER, ACCOUNT, PASSWORD);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + DATABASE);
			rs = stmt.executeQuery(query);
			con.close();
		} catch (Exception e) {
			throw e;
		}
		return rs; // rs would be null if the exception were thrown
	}
	
	protected void initializeConnection(String newTableName, String[] newColNames,
			String[] newColTypes) {
		
		if (newTableName == null || newTableName.isEmpty())
			throw new RuntimeException("newTableName was invalid.");
		if (newColNames == null || newColNames.length < 1
				|| newColNames[0].isEmpty())
			throw new RuntimeException("newColNames was invalid.");
		if (newColTypes == null || newColTypes.length != newColNames.length
				|| newColTypes[0].isEmpty())
			throw new RuntimeException("newColTypes was invalid.");
		
		tableName = newTableName;
		colNames = newColNames;
		colTypes = newColTypes;
		numRows = 0;
		
		data = new ArrayList<List<Object>>();
		for (int i = 0; i < colNames.length; i++)
			data.add(new ArrayList<Object>());
		
		loadTable();
		
	}
	
	private String makeCreationQuery() {
		StringBuilder query = new StringBuilder("CREATE TABLE ");
		query.append(tableName);
		query.append(" (");
		int i = 0;
		for (; i < colNames.length; i++) {
			query.append(colNames[i]);
			query.append(" ");
			query.append(colTypes[i]);
			query.append(",");
		}
		query.setLength(query.length() - 1); // delete last comma
		query.append(" );");
		return query.toString();
	}
}
