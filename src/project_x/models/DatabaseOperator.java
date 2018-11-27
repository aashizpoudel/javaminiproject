package project_x.models;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseOperator {

	Connection dbConnection ;
	static DatabaseOperator current;
	public static DatabaseOperator initialize() throws Exception {
		
		Connection c = null;
		
		if(current != null) {
			return current;
		}
	      
	     
	         Class.forName("org.sqlite.JDBC");
	    	 
	         c = DriverManager.getConnection("jdbc:sqlite:musicdb.db");
	         current = new DatabaseOperator(c);
	      
	      System.out.println("Opened database successfully");
		return current;
	}
	
	public DatabaseOperator(Connection c) {
		dbConnection =c ;
	}

	public boolean executeQuery(String s) throws SQLException {
		System.out.println(s);
		return dbConnection.createStatement().execute(s);
	}
	
	public ResultSet fetchQuery(String s) throws SQLException{
		return dbConnection.createStatement().executeQuery(s);
	}
}
