//SelectTest2.java
package com.nt.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {

	public static void main(String[] args) {
		
		//read inputs
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			int dno=0;
			if(sc!=null) {
				System.out.println("Enter  department number ::");
				dno=sc.nextInt();
			}
			//Load  jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			 
			 //create the JDBC Statement object
			 if(con!=null)
				 st=con.createStatement();
			 
			 //create the SQL Query
			     //SELECT * FROM  DEPT WHERE DEPTNO=10
			 String query="SELECT * FROM  DEPT WHERE DEPTNO="+dno;
			  System.out.println(query);
			  
			  //send and execute the SQL Query in Db s/w
			  if(st!=null)
				  rs=st.executeQuery(query);
			  
			  //process the ResultSet obj
			  if(rs!=null) {
				  if(rs.next()) {
					  System.out.println("The Dept details are ::");
					   System.out.println(rs.getInt(1)+" \t "+rs.getString(2)+"\t "+rs.getString(3));
				  }//if
				  else {
					  System.out.println("Department is not found");
				  }//else
			  }//if
			 
			
		}//try
		catch(SQLException se) {  //For known exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {  //for known exception
			cnf.printStackTrace();
		}
		catch(Exception e) { //  for unknown  exceptions
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
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
			
		}//finally
		
		

	}//main

}//class
