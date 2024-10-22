// LoginAppTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginAppTest {

	public static void main(String[] args) {
		
		  try(Scanner  sc=new Scanner(System.in);
				  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				  Statement  st=con.createStatement();
				  ){
			   String  user=null;
			   String  pass=null;
			   //read the input values
			    if(sc!=null) {
			    	System.out.println("Enter username::");
			    	user=sc.nextLine();  //gives raja
			    	System.out.println("Enter password::");
			    	pass=sc.nextLine();  //gives rani
			    	}
			    //convert the input values  as the required for the SQL Query
			     user="'"+user+"'";  //gives  'raja'
			     pass="'"+pass+"'";  //gives  'rani'
			     
			     //prepare SQL Query
			              // SELECT COUNT(*) FROM USERSINFO WHERE USERNAME='raja' AND PASSWORD='rani'
			      String query=" SELECT COUNT(*) FROM USERSINFO WHERE USERNAME="+user+" AND PASSWORD="+pass;
			      System.out.println(query);
			      
			      //send and execute the SQL Query
			      try(ResultSet rs=st.executeQuery(query)){
			    	    //process the reuslt object
			    	    if(rs!=null) {
			    	    	rs.next();
			    	    	int count=rs.getInt(1);
			    	    	if(count==0)
			    	    		System.out.println("Invalid Credentials");
			    	    	else
			    	    		System.out.println("Valid Credentials");
			    	    }//if
			      }//if
			      
			  
		  }//try1
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }

	}//main

}//class
