package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PsSelectTest1 {
	private static final String GET_EMP_QUERY="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB=?";
	
   public static void main(String[] args) {
	
	   try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			   PreparedStatement ps=con.prepareStatement(GET_EMP_QUERY);    ){
		   
		      
		      if(ps!=null) {
		    	//set query param values
		    	    ps.setString(1, "CLERK");
		      }
		       //execute the SQL Query
		   if(ps!=null) {
		  	  try(ResultSet rs=ps.executeQuery()){
		     //process the ResultSet object
		      if(rs!=null) {
		    	 boolean isRSEmpty=true;
		    	  while(rs.next()) {
		    		  isRSEmpty=false;
		    		  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4)+"  "+rs.getInt(5));
		    	  }//while
		    	   if(isRSEmpty) {
		    		   System.out.println("No Records  found -- Table is empty");
		    	   }
		    	   else {
		    		   System.out.println("Records found and displayed as shown above");
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
