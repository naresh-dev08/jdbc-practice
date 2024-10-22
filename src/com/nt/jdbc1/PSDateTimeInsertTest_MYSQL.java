package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class PSDateTimeInsertTest_MYSQL {
	private static final String INSERT_CUSTOMER_QUERY="INSERT INTO CUSTOMER_INFO(CNAME,BILLAMOUNT,DOB,TOP,ORDER_DATE_TIME) VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1", "root", "root");
				PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc=new Scanner(System.in);
				){
			
			 String name=null;
			 float billamount=0.0f;
			 String  sdob=null, stop=null, sodt=null;
			  if(sc!=null) {
				  //read input values from the enduser
				  System.out.println("Enter  customer name::");
				  name=sc.next();
				  System.out.println("Enter  customer Bill Amount::");
				  billamount=sc.nextFloat();
				  System.out.println("Enter  customer DOB(dd-MM-yyyy)::");
				  sdob=sc.next();
				  System.out.println("Enter  customer Time Of Purchase (TOP) (hh:mm:ss)::");
				  stop=sc.next();
				  System.out.println("Enter  customer Order Date and Time () (dd-MM-yyyy hh:mm:ss)::");
				             sc.nextLine();
				  sodt=sc.nextLine();
			  }
			  //convert the  String date values to  java.sql.Date class obj
			   SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			   java.util.Date udob=sdf1.parse(sdob);
			   long ms=udob.getTime();
			   java.sql.Date sqdob=new java.sql.Date(ms);
			   //convert   the String time value to  java.sql.Time class obj
			   java.sql.Time  sqtop=java.sql.Time.valueOf(stop);
			   //convert  the  String  date,time value to java.sql.TimeStamp class obj
			   SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			   java.util.Date  uodt=sdf2.parse(sodt);
			   long ms1=uodt.getTime();
			   java.sql.Timestamp  sqodt=new java.sql.Timestamp(ms1);
				
			   //set values  to the SQL Query params (?) of pre-compiled SQL Query
			    if(ps!=null) {
			    	ps.setString(1, name);
			    	ps.setFloat(2, billamount);
			    	ps.setDate(3, sqdob);
			    	ps.setTime(4, sqtop);
			    	ps.setTimestamp(5, sqodt);
			    	
			    	//execute the  non-select SQL Query
			    	int count=ps.executeUpdate();
			    	//process the result
			    	if(count==0)
			    		System.out.println("Customer record is not inserted");
			    	else
			    		System.out.println("Customer record is inserted");
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
