//SelectTest3_TWR.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest3_TWR {

	public static void main(String[] args) {
	
		try (//establish the connection
			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create the JDBC Statement object
				Statement   st=con.createStatement();
			 //send and execute the SQL Query
				ResultSet rs=st.executeQuery("SELECT COUNT(*) FROM EMP")){
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
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}//main

}//class
