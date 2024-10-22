//UpdateTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
	
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read the inputs
			sc=new Scanner(System.in);
			int no=0;
			String newName=null, newAddrs=null;
			float newAvg=0.0f;
			 if(sc!=null) {
				 System.out.println("Enter existing student number ::");
				 no=sc.nextInt();  //gives 1010
				 System.out.println("Enter student's new  name::");
				 newName=sc.next();  //gives  suresh
				 System.out.println("Enter  Student's  new  address");
				 newAddrs=sc.next();  //gives delhi
				 System.out.println("enter  student's new avg::");
				 newAvg=sc.nextFloat();  //gvies  90.66
			 }
			 //convert the input values are required for the SQL Query
			 newName="'"+newName+"'"; //gives  'suresh'
			 newAddrs="'"+newAddrs+"'"; //gives 'delhi'
			 
			// Load jdbc driver class
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			 
			 //create The Statementn object
			 if(con!=null)
				 st=con.createStatement();
			 
			 //prepare the SQL Query
			      // UPDATE  STUDENT  SET SNAME='SURESH' , SADD='DELHI', AVG=90.88 WHERE SNO=1010
			    String query="UPDATE  STUDENT  SET SNAME="+newName+" , SADD="+newAddrs+", AVG= "+newAvg+" WHERE SNO= "+no;
			    System.out.println(query);
			    
			 
		
			 //send and execute SQL Query in Db s/w
			 int count=0;
			 if(st!=null)
				 count=st.executeUpdate(query);
			 
			 //process the result
			 if(count==0)
				 System.out.println("Problem in  Record updation");
			 else
				 System.out.println("Record update with new vlaues");
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
