package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest_PostgreSQL {
	private static final String  PRODUCT_SELECT_QUERY="SELECT * FROM PRODUCT";

	public static void main(String[] args) {

          try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ415db","postgres", "tiger");
        		  PreparedStatement ps=con.prepareStatement(PRODUCT_SELECT_QUERY); 
        		  ResultSet rs=ps.executeQuery();
        		  ){
        	     //process the ResultSet obj
        	   if(rs!=null) {
        		    while(rs.next()) {
        		    	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getFloat(4));
        		    }//while
        	   }//if
        	  
          }//try
          catch(Exception e) {
        	  e.printStackTrace();
          }

	}//main
}//class
