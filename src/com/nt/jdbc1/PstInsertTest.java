package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;





public class PstInsertTest {
	private static final String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				PreparedStatement ps=con.prepareStatement(INSERT_STUDENT_QUERY);	){
			  int count=0;
			  if(sc!=null) {
				  System.out.println("Enter students count::");
				  count=sc.nextInt();
			  }
			  
			  
			  if(sc!=null && ps!=null) {
				  for(int i=1;i<=count;++i) {
					  //read each student details from enduser
					  System.out.println("Enter "+i+" student details");
					  System.out.println("enter sno::");
					  int no=sc.nextInt();
					  
					  System.out.println("enter name::");
					  String name=sc.next();
					  
					  System.out.println("enter address::");
					  String addrs=sc.next();
					  
					  System.out.println("enter avg::");
					  float avg=sc.nextFloat();
					  //  set each student details to   the params of pre-compiled SQL Query represented by PreparedStatement obj
					  ps.setInt(1, no); ps.setString(2, name); ps.setString(3,addrs); ps.setFloat(4, avg);
					  
					  //execute the pre-compiled SQL Query
					  int result=ps.executeUpdate();
					  
					  //process the results
					  if(result==0)
						  System.out.println(i+" Student details are not inserted");
					  else
						  System.out.println(i+"Student details are inserted");
					  
				  }//for
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
