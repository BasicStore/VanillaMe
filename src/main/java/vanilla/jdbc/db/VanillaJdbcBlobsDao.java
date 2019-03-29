package vanilla.jdbc.db;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VanillaJdbcBlobsDao extends DataEngine {

	private static final String DATABASE = "blob_files";
	private static final String DB_URL = "jdbc:mysql://localhost/" + DATABASE + SQL_SUFFIX;

	public void blogTest() {
		Connection conn = null;
		   PreparedStatement pstmt = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   try{
		      // Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      // Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL, DBMS_USER, DBMS_PASS);

		      //Create a Statement object and build table
		      stmt = conn.createStatement();

		      //Open a FileInputStream
		      File f = new File("XML_Data.xml");
		      long fileLength = f.length();
		      FileInputStream fis = new FileInputStream(f);
		      
		      //Create PreparedStatement and stream data
		      String sql = "INSERT INTO XML_Data VALUES (?,?)";
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1,100);
		      pstmt.setAsciiStream(2,fis,(int)fileLength);
		      pstmt.execute();

		      //Close input stream
		      fis.close();

		      // Do a query to get the row
		      sql = "SELECT Data FROM XML_Data WHERE id=100";
		      rs = stmt.executeQuery (sql);
		      // Get the first row
		      if (rs.next ()){
		         //Retrieve data from input stream
		         InputStream xmlInputStream = rs.getAsciiStream (1);
		         int c;
		         ByteArrayOutputStream bos = new ByteArrayOutputStream();
		         while (( c = xmlInputStream.read ()) != -1)
		            bos.write(c);
		         //Print results
		         String output = bos.toString();
		         System.out.println(output);
		      }
		      // Clean-up environment
		      rs.close();
		      stmt.close();
		      pstmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(pstmt!=null)
		            pstmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		
		
	}
	
	
	
	
    
	
	
}
