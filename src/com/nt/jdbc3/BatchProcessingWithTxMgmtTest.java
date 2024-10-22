package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchProcessingWithTxMgmtTest {

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				Statement st=con.createStatement();
				){
			//read inputs
			long srcAcno=0, destAcno=0;
			float amount=0.0f;
			 if(sc!=null) {
				 System.out.println("Enter the source Account number::");
				 srcAcno=sc.nextLong();
				 System.out.println("Enter the Dest Account number::");
				 destAcno=sc.nextLong();
				 System.out.println("Enter  the amount to transfre ::");
				  amount=sc.nextFloat();
			 }
			 
			 // Begin the Tx
			  if(con!=null)
				   con.setAutoCommit(false);
			  
			  //add the queries to the batch
			  if(st!=null) {
				    //for withdraw operation
				  st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE-"+amount+" WHERE ACNO="+srcAcno);
				  //for deposite operation
				  st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE+"+amount+" WHERE ACNO="+destAcno);
				  
				  // execute the Batch
				  int result[]=st.executeBatch();
				  
				  //perform Tx mgmt on the resutls of Batch processing 
				  boolean  txFlag=true;
				  if(result!=null) {
					  for(int i=0;i<result.length;++i) {
						      if(result[i]==0) {
						    	  txFlag=false;
						    	  break;
						      }//if
					  }//for
				  }//if
				  
				  if(txFlag) {
					   con.commit();
					  System.out.println("Tx is  committed and Money is transffered");
				  }
				  else {
					   con.rollback();
						  System.out.println("Tx is not  committed and Money is not transffered");
				  }
				  
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
