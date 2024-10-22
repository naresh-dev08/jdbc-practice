//ConnPoolTest.java
package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {
     private  static final String  GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
	
		try {
			//create  the DataSource obj
			OracleConnectionPoolDataSource  oraDs=new OracleConnectionPoolDataSource();
			//set jdbc properties to DataSource obj to  create jdbc con pool with jdbc con objs
			oraDs.setDriverType("thin");
			oraDs.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			oraDs.setUser("system");
			oraDs.setPassword("manager");
			
			//get  Pooled jdbc con object from the jdbc con pool
			try(Connection con=oraDs.getConnection();
					PreparedStatement ps=con.prepareStatement(GET_STUDENTS_QUERY);
					ResultSet rs=ps.executeQuery();
					){
				  //process the ResultSet
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
