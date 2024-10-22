//DeletTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeletTest {

	public static void main(String[] args) {
	
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read the inputs
			sc=new Scanner(System.in);
			int no=0;
			 if(sc!=null) {
				 System.out.println("Enter student number ::");
				 no=sc.nextInt();
			 }
			// Load jdbc driver class
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			 
			 //create The Statementn object
			 if(con!=null)
				 st=con.createStatement();
			 //prepare the SQL Query
			            // DELETE FROM STUDENT WHERE SNO=101
			 String query=" DELETE FROM STUDENT WHERE SNO="+no;
			 System.out.println(query);
			 
			 //send and execute SQL Query in Db s/w
			 int count=0;
			 if(st!=null)
				 count=st.executeUpdate(query);
			 
			 //process the result
			 if(count==0)
				 System.out.println("no records are found for deletion");
			 else
				 System.out.println(count+" no.of records are found and deleted");
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
