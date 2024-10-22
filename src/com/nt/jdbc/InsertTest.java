//InsertTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
	
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read the inputs
			sc=new Scanner(System.in);
			int no=0;
			String name=null, addrs=null;
			float avg=0.0f;
			 if(sc!=null) {
				 System.out.println("Enter student number ::");
				 no=sc.nextInt();  //gives 1010
				 System.out.println("Enter student name::");
				 name=sc.next();  //gives  raja
				 System.out.println("Enter  Student address");
				 addrs=sc.next();  //gives hyd
				 System.out.println("enter  student avg::");
				 avg=sc.nextFloat();  //gvies  90.66
			 }
			 //convert the input values are required for the SQL Query
			 name="'"+name+"'"; //gives  'raja'
			 addrs="'"+addrs+"'"; //gives 'hyd'
			 
			// Load jdbc driver class
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			 
			 //create The Statementn object
			 if(con!=null)
				 st=con.createStatement();
			 
			 //prepare the SQL Query
			      // INSERT INTO STUDENT VALUES(1010,'raja','hyd',67.8)
			 String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			  System.out.println(query);
			 
			 //send and execute SQL Query in Db s/w
			 int count=0;
			 if(st!=null)
				 count=st.executeUpdate(query);
			 
			 //process the result
			 if(count==0)
				 System.out.println("Problem in  Record insertion");
			 else
				 System.out.println("Record inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
