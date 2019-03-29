package vanilla.jdbc.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vanilla.jdbc.dm.Person;


// obviously, use a data engine to provide connection
// in real life use a singleton spring bean to keep the connection
public class VanillaJdbcExampleDao extends DataEngine {
	private static final String DATABASE = "people";
	private static final String DB_URL = "jdbc:mysql://localhost/" + DATABASE + SQL_SUFFIX;

	
	public List<Person> getPeopleUsingStatement() {
		//STEP 1: declare connection and statement
		Connection conn = null;
		Statement stmt = null;
		
		List<Person> personList = new ArrayList<Person>();
		
		try {
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
	
			//STEP 3: Open a connection
	        System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,DBMS_USER,DBMS_PASS);
	
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM jdbc_employees";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		  //STEP 5: Extract data from result set
	      while(rs.next()){
	    	  int id = rs.getInt("id");
	    	  String firstName = rs.getString("first");
	    	  String lastName = rs.getString("last");
	    	  int age = rs.getInt("age");
	    	  boolean ageWasNull = rs.wasNull();
	    	  age = ageWasNull ? 100 : age;
	    	  personList.add(new Person(id, firstName, lastName, age));
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	      return personList;
	    } catch(SQLException e) {
		   e.printStackTrace();
	    } catch(Exception e) {
		   e.printStackTrace();
        } finally{ // close resources
		   try {
		       if(stmt!=null)
		           stmt.close();
			   } 
		   catch(SQLException se2){}
		   try{
		       if(conn!=null) {
			       conn.close();
		       }    
	       }
		   catch(SQLException e){
		       e.printStackTrace();
		   }
		   System.out.println("exiting finally int get all.........");
	    }
		
		return null;
    }  
	
	
	
	public void update(Person person) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
		    //STEP 2: Register JDBC driver
		    Class.forName(JDBC_DRIVER);

		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,DBMS_USER,DBMS_PASS);

		    //STEP 4: Execute a query
		    System.out.println("Updating with prepared statement...");
		    String sql = "UPDATE jdbc_employees set first=? WHERE id=?";
		    prepStmt = conn.prepareStatement(sql);
		      
		    //Bind values into the parameters.
		    prepStmt.setString(1, person.getFirstName());  
		    prepStmt.setInt(2, person.getId()); 
		    int rows = prepStmt.executeUpdate();
		    System.out.println("Number of rows updated: " + rows );
		} catch(SQLException e) {
		   e.printStackTrace();
	    } catch(Exception e) {
		   e.printStackTrace();
	    } finally{ // close resources
		    try {
		        if(prepStmt!=null)
		            prepStmt.close();
			     } 
		    catch(SQLException se2){}
		   try{
		       if(conn!=null) {
			       conn.close();
		       }    
	       }
		   catch(SQLException e){
		       e.printStackTrace();
		   }
		   System.out.println("exiting finally in update....");
	    }
	}
	
	
	
	public void insert(Person person) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
		    //STEP 2: Register JDBC driver
		    Class.forName(JDBC_DRIVER);

		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,DBMS_USER,DBMS_PASS);

		    //STEP 4: Execute a query
		    System.out.println("inserting with prepared statement...");
		    String sql = "insert into jdbc_employees (id, first, last, age) values(?, ?, ?, ?)";
		    prepStmt = conn.prepareStatement(sql);
		      
		    //Bind values into the parameters.
		    prepStmt.setInt(1, person.getId());  
		    prepStmt.setString(2, person.getFirstName()); 
		    prepStmt.setString(3, person.getLastName());
		    prepStmt.setInt(4, person.getAge());
		    int rows = prepStmt.executeUpdate();
		    System.out.println("Number of rows inserted: " + rows );
		} catch(SQLException e) {
		   e.printStackTrace();
	    } catch(Exception e) {
		   e.printStackTrace();
	    } finally{ // close resources
		    try {
		        if(prepStmt!=null)
		            prepStmt.close();
			     } 
		    catch(SQLException se2){}
		   try{
		       if(conn!=null) {
			       conn.close();
		       }    
	       }
		   catch(SQLException e){
		       e.printStackTrace();
		   }
		   System.out.println("exiting finally in update....");
	    }
		
	}
	
	
	public void delete(Person person) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		
		try {
		    //STEP 2: Register JDBC driver
		    Class.forName(JDBC_DRIVER);

		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,DBMS_USER,DBMS_PASS);

		    //STEP 4: Execute a query
		    System.out.println("deleting with prepared statement...");
		    String sql = "delete from jdbc_employees where id = ?";
		    prepStmt = conn.prepareStatement(sql);
		      
		    //Bind values into the parameters.
		    prepStmt.setInt(1, person.getId());  
		    int rows = prepStmt.executeUpdate();
		    System.out.println("Number of rows deleted: " + rows );
		} catch(SQLException e) {
		   e.printStackTrace();
	    } catch(Exception e) {
		   e.printStackTrace();
	    } finally{ // close resources
		    try {
		        if(prepStmt!=null)
		            prepStmt.close();
			     } 
		    catch(SQLException se2){}
		   try{
		       if(conn!=null) {
			       conn.close();
		       }    
	       }
		   catch(SQLException e){
		       e.printStackTrace();
		   }
		   System.out.println("exiting finally in update....");
	    }
		
	}
	
	
	
	
	public void deleteAll() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
		    //STEP 2: Register JDBC driver
		    Class.forName(JDBC_DRIVER);

		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,DBMS_USER,DBMS_PASS);

		    //STEP 4: Execute a query
		    System.out.println("deleting all with statement...");
		    String sql = "delete from jdbc_employees";
		    stmt = conn.prepareStatement(sql);
		      
		    //Bind values into the parameters.
		    stmt.execute(sql);
		} catch(SQLException e) {
		   e.printStackTrace();
	    } catch(Exception e) {
		   e.printStackTrace();
	    } finally{ // close resources
		    try {
		        if(stmt!=null)
		            stmt.close();
			     } 
		    catch(SQLException se2){}
		   try{
		       if(conn!=null) {
			       conn.close();
		       }    
	       }
		   catch(SQLException e){
		       e.printStackTrace();
		   }
		   System.out.println("exiting finally in update....");
	    }
		
	}

	
	
	public Person getPersonUsingPreparedStatement(int id) {
		
		//STEP 1: declare connection and statement
		Connection conn = null;
		PreparedStatement stmt = null;
		Person person = null;
		
		try {
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
	
			//STEP 3: Open a connection
	        System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,DBMS_USER,DBMS_PASS);
	
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			
			stmt = conn.prepareStatement("SELECT id, first, last, age FROM jdbc_employees where id = ?");
			stmt.setInt(1, id);  
		    ResultSet rs = stmt.executeQuery();
		    
		  //STEP 5: Extract data from result set
	      if (rs.next()){
	         person = new Person(rs.getInt("id"), rs.getString("first"), rs.getString("last"), rs.getInt("age"));
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	      return person;
	    } catch(SQLException e) {
		   e.printStackTrace();
	    } catch(Exception e) {
		   e.printStackTrace();
        } finally{ // close resources
		   try {
		       if(stmt!=null)
		           stmt.close();
			   } 
		   catch(SQLException se2){}
		   try{
		       if(conn!=null) {
			       conn.close();
		       }    
	       }
		   catch(SQLException e){
		       e.printStackTrace();
		   }
		   System.out.println("exiting finally in method to get a single person.........");
	    }
		
		return null;
	}
	
	
	
}



















