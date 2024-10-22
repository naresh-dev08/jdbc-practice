//PersonAgeCalculator_MYSQL.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class PersonAgeCalculator_MYSQL {
	private static final String  GET_PERSON_AGE_QUERY="SELECT  (DATEDIFF(NOW(),DOB))/365.25 FROM CUSTOMER_INFO WHERE CNO=?";
			

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1", "root","root");
				PreparedStatement ps=con.prepareStatement(GET_PERSON_AGE_QUERY);
				Scanner sc=new Scanner(System.in);){
			
			  int no=0;
			 if(sc!=null) {
				 System.out.println("Enter  Customer Number::");
				 no=sc.nextInt();
			 }
			 
			 //set  values to Query params
			 if(ps!=null) 
				 ps.setInt(1, no);
			 
			 //Execute the pre-compiled SQL Query
			  try(ResultSet rs=ps.executeQuery() ){
				  
				  if(rs!=null) {
					   if(rs.next()) {
						   float age=rs.getFloat(1);
						   System.out.println("Customer age::"+age);
					   }//if
					   else {
						   System.out.println("Customer  not found");
					   }
				  }//if
				  
			  }//try1
	
	}//try2
   catch(SQLException se) {
	   se.printStackTrace();
   }
  catch(Exception e) {
	  e.printStackTrace();
  }
	}//main
}//class
