package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPS_BY_SALARY_RANGE 
(
  STARTSALARY IN FLOAT 
, ENDSALARY IN FLOAT 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
  
  OPEN DETAILS FOR
     SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE SAL>=STARTSALARY AND SAL<=ENDSALARY; 
  
END P_GET_EMPS_BY_SALARY_RANGE;*/


import oracle.jdbc.internal.OracleTypes;

public class Cs_ProcedureCursorTest {
	private static final String  CALL_PROCEDURE_QUERY="{call  P_GET_EMPS_BY_SALARY_RANGE(?,?,?)}";
	
	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				Scanner sc=new Scanner(System.in);
				){
			  float startSalary=0.0f,endSalary=0.0f;
			  if(sc!=null) {
				 System.out.println("enter  Emp salary start range::");
				 startSalary=sc.nextFloat();
				 System.out.println("enter  Emp salary end range::");
				 endSalary=sc.nextFloat();
			 }
		
			  if(cs!=null) {
				  //register  OUT params with jdbc Types
				  cs.registerOutParameter(3, OracleTypes.CURSOR);
				  //set values  to IN params
				  cs.setFloat(1, startSalary);
				  cs.setFloat(2, endSalary);
				   //call PL/SQL Procedure
				   cs.execute();
				   //gather results from OUT params
				   try(ResultSet rs=(ResultSet)cs.getObject(3)){
					   //process the ResultSet obj
					     if(rs!=null) {
					    	 boolean isRSEmpty=true;
					    	 while(rs.next()) {
					    		 isRSEmpty=false;
					    		 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getFloat(4)+"  "+rs.getInt(5));
					    	 }//while
					    	 if(isRSEmpty) {
					    		 System.out.println(" Employees not found");
					    	 }
					    	 else {
					    		 System.out.println("Employees are found and display");
					    	 }
					     }//if
					   
				   }//try
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
