//PersonAgeCalculator_AllDBs.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class PersonAgeCalculator_AllDBs {
	private static final String  GET_PERSON_DOB_QUERY="SELECT  DOB FROM CUSTOMER_INFO WHERE CNO=?";
			

	public static void main(String[] args) {
		
		try(//Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1", "root","root");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				PreparedStatement ps=con.prepareStatement(GET_PERSON_DOB_QUERY);
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
						   java.sql.Date sqdob=rs.getDate(1);  //gets DOB 
						   java.util.Date   sysDate=new java.util.Date();  //gets  System date and  time
						   long ageInMs=(sysDate.getTime()-sqdob.getTime());
						   float age=(ageInMs/(1000.0f * 60.0f * 60.0f * 24.0f * 365.25f));
						   System.out.println("Person  age::"+age);
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
