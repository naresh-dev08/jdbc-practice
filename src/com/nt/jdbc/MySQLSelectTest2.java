package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MySQLSelectTest2 {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB1","root","root");
				Statement st=con.createStatement();
				Scanner sc=new Scanner(System.in);	){
			
			float startPrice=0.0f,endPrice=0.0f;
			if(sc!=null) {
				System.out.println("Enter start Bill Range::");
				startPrice=sc.nextFloat();
				System.out.println("Emnter  end BiiAmt Range::");
				endPrice=sc.nextFloat();
			}
			
			// prepare the sQL Query
			          //SELECT * FROM PRODUCT WHERE PRICE>=10000 AND PRICE<=20000
			 String query="SELECT * FROM  Customer WHERE billAmt>="+startPrice+" AND billAmt<="+endPrice;
			 System.out.println(query);
			 
			 //send and execute  the SQL Query
			 try(ResultSet rs=st.executeQuery(query)){  //nested try with resource
				   //process the ResultSet obj
				 boolean isRSEmpty=true;
				  if(rs!=null) {
					  while(rs.next()) {
						  System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getFloat(4));
						  isRSEmpty=false;
					  }//while
				  }//if
					 if(isRSEmpty)
						 System.out.println("No Records found");
					 else
						 System.out.println("Records found and displayed");
			 }///try2
			
		
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
