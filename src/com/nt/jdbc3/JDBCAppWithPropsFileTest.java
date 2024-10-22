//JDBCAppWithPropsFileTest.java
package com.nt.jdbc3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCAppWithPropsFileTest {
	private static final String   GET_STUDENT_DETAILS_QUERY="SELECT * FROM STUDENT";

	public static void main(String[] args) {
		
		
		try(InputStream is=new FileInputStream("src/com/nt/commons/jdbc.properties");){
			  //load the properties content to java.util.Properties class obj
			   Properties props=null;
			    if(is!=null) {
			    	props=new Properties();
			    	props.load(is);
			    }
			    
			    try(Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),
			    		                                                                                            props.getProperty("jdbc.username"),
			    		                                                                                            props.getProperty("jdbc.password"));
			    		PreparedStatement ps=con.prepareStatement(GET_STUDENT_DETAILS_QUERY);
			    		ResultSet rs=ps.executeQuery();
			    		){
			    	 if(rs!=null) {
			    		 while(rs.next()) {
			    			 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			    		 }//while
			    	 }//if
			    }//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
