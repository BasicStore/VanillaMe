package vanilla.jdbc.db;

import java.util.List;

import vanilla.jdbc.dm.Person;

/**
 * A class to manage generic operations for all dao access
 * for a vanilla jdbc application.
 * Specific operations are referred to in their respective dao subclasses
 * using the abstract members of this class.
 */
public class DataEngine {
	
	//  Database credentials - use prop file
	protected static final String DBMS_USER = "dilley";
	protected static final String DBMS_PASS = "GrahamD1ll3y!";

	// JDBC driver name and database URL
	protected static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	protected static final String SQL_SUFFIX = "?autoReconnect=true&useSSL=false";
	
	// TODO would be desirable....  
	
	// also remember there is no implementation of explicit commit() and rollback() yet
	
	
	public List<?> getAll() {
		return null;
	}
	
	

}
