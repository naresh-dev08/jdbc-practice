package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLSelectTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","root");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT");	){
			
			//process the ResultSet object
			if(rs!=null) {
				 boolean isRSEmpty=true;
				 while(rs.next()) {
					 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getFloat(4));
					 isRSEmpty=false;
				 }//while
				 if(isRSEmpty)
					 System.out.println("No Records found");
				 else
					 System.out.println("Records found and displayed");
		
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
