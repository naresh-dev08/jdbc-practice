//UpdateTest_TWR.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest_TWR {

	public static void main(String[] args) {
	
	
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement  st=con.createStatement();		){
			//read the inputs
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
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
