//CS_ProcedureCallTest2.java
package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_NO 
(  NO IN NUMBER 
, NAME OUT VARCHAR2 
, SALARY OUT NUMBER 
) AS 
BEGIN
   SELECT  ENAME , SAL INTO NAME,SALARY FROM EMP WHERE EMPNO=NO;
END P_GET_EMP_DETAILS_BY_NO;  */

public class CS_ProcedureCallTest2 {
	private static final String  CALL_PROCEDURE_QUERY="{CALL P_GET_EMP_DETAILS_BY_NO(?,?,?) }";

	public static void main(String[] args) {
	
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system1","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				Scanner sc=new Scanner(System.in);
				){
			 //read inputs
			int no=0;
			if(sc!=null) {
				System.out.println("Enter  Employee number::");
				no=sc.nextInt();
			}
			
			if(cs!=null) {
				// register  the OUT params with JDBC types
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3, Types.INTEGER);
				//set values to IN params
				cs.setInt(1, no);
				// call PL/SQL procedure
				cs.execute();
				//gather results from OUT params
				String name=cs.getString(2);
				int salary=cs.getInt(3);
				System.out.println("Employee name is::"+name);
				System.out.println("Employee  salary is::"+salary);
			}//if
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Employee not found");
			else if(se.getErrorCode()==1017)
				System.out.println("Invalid Credentials");
			else
				System.out.println("unknown DB problem");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
