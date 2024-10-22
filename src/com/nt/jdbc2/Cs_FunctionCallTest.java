package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_STUD_DETAILS_BY_SNO 
(  NO IN NUMBER , NAME OUT VARCHAR2 , ADDRS OUT VARCHAR2 ) RETURN FLOAT
AS 
  PERCENTAGE FLOAT;
BEGIN
   SELECT SNAME,SADD,AVG INTO NAME,ADDRS,PERCENTAGE FROM STUDENT  WHERE SNO=NO;
  RETURN PERCENTAGE;
  
END FX_GET_STUD_DETAILS_BY_SNO;*/

public class Cs_FunctionCallTest {
	private static final String  CALL_FUNCTION_QUERY="{ ?=call FX_GET_STUD_DETAILS_BY_SNO(?,?,?)   }";
			
			

	public static void main(String[] args) {
		
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
				CallableStatement cs=con.prepareCall(CALL_FUNCTION_QUERY);
				Scanner sc=new Scanner(System.in); ){
			
			int no=0;
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt();
			}
			
			if(cs!=null) {
				//register return, OUT params with JDBC data types
				cs.registerOutParameter(1, Types.FLOAT);  //return param
				cs.registerOutParameter(3, Types.VARCHAR);  //out param
				cs.registerOutParameter(4, Types.VARCHAR);  //out param
				 //set values to IN params
				cs.setInt(2, no);
				//call the PL/SQL function
				cs.execute();
				//gather results from OUT,RETURN Params
				System.out.println("student name ::"+cs.getString(3)); //OUT param
				System.out.println("Student address::"+cs.getString(4));  //OUT param
				System.out.println("STudent  avg::  "+cs.getFloat(1));  //return param
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
