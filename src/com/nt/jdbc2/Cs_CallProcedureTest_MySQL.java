package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE DEFINER=`root`@`localhost` PROCEDURE `p_get_products_by_price_range`(IN startPrice float,
		  IN endPrice float)
BEGIN
SELECT PID,PNAME,PRICE,QTY FROM PRODUCT WHERE PRICE>=startPrice AND PRICE<=endPrice;
END
*/
public class Cs_CallProcedureTest_MySQL {
	private static final String  P_CALL_PROCEDURE="{ call  p_get_products_by_price_range(?,?) }";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1","root", "root");
				CallableStatement cs=con.prepareCall(P_CALL_PROCEDURE);
				Scanner sc=new Scanner(System.in); ){
			 float startPrice=0.0f, endPrice=0.0f;
			 if(sc!=null) {
				 //read inputs
				 System.out.println("Enter Product start price range::");
				 startPrice=sc.nextFloat();
				 System.out.println("Enter Product end price range::");
				 endPrice=sc.nextFloat();
			 }
			  if(cs!=null) {
				  //register  OUT params  with JDBC types
				    //not avaiable
				  //set values to IN params
				  cs.setFloat(1, startPrice);
				  cs.setFloat(2, endPrice);
				  //call  PL/SQL procedure
				  cs.execute();
				  //gather results
				  try(ResultSet rs=cs.getResultSet()){
					  //process the ResultSet
					  if(rs!=null) {
						  boolean isRSEmpty=true;
						  while(rs.next()) {
							  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getFloat(4));
							  isRSEmpty=false;
						  }//while
						  if(isRSEmpty) {
							  System.out.println("Record not found");
						  }
					  }//if
				  }//if
			  }//try2
		
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
