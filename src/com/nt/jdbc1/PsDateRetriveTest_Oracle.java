package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class PsDateRetriveTest_Oracle {
	private  static final String   GET_DATE_QUERY="SELECT  CNO,CNAME,BILLAMOUNT,DOB,TOP,ORDER_DATE_TIME FROM  CUSTOMER_INFO";
	
	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
				PreparedStatement ps=con.prepareStatement(GET_DATE_QUERY);  // Here we can use Simple Statement obj also
				ResultSet rs= ps.executeQuery();
				){
			//process the ResultSet obj
			   if(rs!=null) {
				   while(rs.next()) {
					   int no=rs.getInt(1);
					   String name=rs.getString(2);
					   float  billAmt=rs.getFloat(3);
					   java.sql.Date sqdob=rs.getDate(4);
					   java.sql.Time  sqtop=rs.getTime(5);
					   java.sql.Timestamp sqodt=rs.getTimestamp(6);
					   System.out.println(no+"  "+name+"   "+billAmt+"  "+sqdob+"  "+sqtop+"  "+sqodt);
					   
					   //convert  java.sql.Date class obj to String date vlaue in the required pattern
					     SimpleDateFormat  sdf1=new SimpleDateFormat("dd-MM-yyyy");
					     String sdob=sdf1.format(sqdob);
					     //covert java.sql.Time obj  to String  date value
					      String stop=sqtop.toString();
					     //convert  java.sql.Timestamp class obj to String date vlaue in the required pattern
					      long ms=sqodt.getTime();
					      java.util.Date udodt=new java.util.Date(ms);   
					      SimpleDateFormat  sdf2=new SimpleDateFormat("dd-yyyy-MMM hh:ss:mm");
					      String sodt=sdf2.format(udodt);
					      
					      System.out.println(no+"  "+name+"   "+billAmt+"  "+sdob+"  "+stop+"  "+sodt);
					      
				   }//while
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
