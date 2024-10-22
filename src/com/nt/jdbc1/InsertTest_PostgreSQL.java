package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_PostgreSQL {
	private static final String  PRODUCT_INSERT_QUERY="INSERT INTO PRODUCT  VALUES(NEXTVAL('PID_SEQ'),?,?,?)";

	public static void main(String[] args) {

          try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ415db","postgres", "tiger");
        		  PreparedStatement ps=con.prepareStatement(PRODUCT_INSERT_QUERY); 
        		  Scanner sc=new Scanner(System.in);
        		  ){
        	     //read input values
        	     String  name=null;
        	     float price=0.0f, qty=0.0f;
        	    if(sc!=null) {
        	    	System.out.println("Enter product name::");
        	    	name=sc.next();
        	    	System.out.println("Enter  Product price::");
        	    	price=sc.nextFloat();
        	    	System.out.println("Enter Product  qty::");
        	    	qty=sc.nextFloat();
        	    }
        	    
        	     if(ps!=null) {
        	    	//set values to SQL Query params
        	    	 ps.setString(1, name);
        	    	 ps.setFloat(2, price);
        	    	 ps.setFloat(3, qty);
        	    	 //execute the SQL query
        	    	 int count=ps.executeUpdate();
        	    	 //process the results
        	    	 if(count==0)
        	    		 System.out.println("Record not inserted");
        	    	 else
        	    		 System.out.println("Record inserted");
        	     }
          }//try
          catch(SQLException se) {
        	  se.printStackTrace();
          }
          catch(Exception e) {
        	  e.printStackTrace();
          }

	}//main
}//class
