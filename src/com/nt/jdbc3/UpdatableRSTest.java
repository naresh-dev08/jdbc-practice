// SensitiveRSTest.java
package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UpdatableRSTest {
	private static final String GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                                                                ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(GET_STUDENTS_QUERY);	){
			if(rs!=null) {
				System.out.println("records (top- bottom)(Select operation)");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}
				
			/*	//insert operation
				rs.moveToInsertRow();
				rs.updateInt(1, 345);
				rs.updateString(2,"raja");
				rs.updateString(3, "hyd");
				rs.updateFloat(4, 56.78f);
				rs.insertRow();
				System.out.println("new record is inserted"); */
				
				/* rs.absolute(3);
				rs.updateString(3, "vizag1");
				rs.updateRow();
			    System.out.println("record updated"); */
				
				rs.absolute(4);
				rs.deleteRow();
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
