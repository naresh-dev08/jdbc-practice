//SelectTest3.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest3 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create the JDBC Statement object
			 if(con!=null)
			   st=con.createStatement();
			 //prepare the SQL Query
			 String quey="SELECT COUNT(*) FROM EMP";
			 
			 //send and execute the SQL Query
			 if(st!=null)
				 rs=st.executeQuery(quey);
			 
			 //process the ResultSet obj
			 if(rs!=null) {
				 rs.next();
				  System.out.println(" Emp db table records count is ::"+rs.getInt("count(*)"));  // (or)
				// System.out.println(" Emp db table records count  ::"+rs.getInt(1));
				   
			 }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close the jdbc objs
			 try {
				 if(rs!=null)
					 rs.close();
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 
			 try {
				 if(st!=null)
					 st.close();
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 
			 try {
				 if(con!=null)
					 con.close();
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 
		}//finally

	}//main

}//class
