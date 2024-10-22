// SensitiveRSTest.java
package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SensitiveRSTest {
	private static final String GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(GET_STUDENTS_QUERY);
				){
			if(rs!=null) {
				
				System.out.println("records (top- bottom)");
				int count=0;
				while(rs.next()) {
					if(count==0)
						Thread.sleep(30000);
					rs.refreshRow();
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
					count++;
				}
			
			}
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
