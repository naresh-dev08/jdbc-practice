//RSMetaDataTest.java
package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class RSMetaDataTest {

	public static void main(String[] args) {
		
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
				){
			   ResultSetMetaData rsmd=null;
			    if(rs!=null) {
			    	rsmd=rs.getMetaData();
			    }
			    if(rsmd!=null && rs!=null) {
			    	int colCount=rsmd.getColumnCount();
			    	for(int i=1;i<=colCount;++i) {
			    		System.out.print(rsmd.getColumnName(i)+"("+rsmd.getColumnTypeName(i)+")              ");
			    	}
			    	System.out.println();
			    	
			    	while(rs.next()) {  //goes through records
			    		for(int i=1;i<=colCount;++i) {  //goes through the cols of record
			    			System.out.print(rs.getString(i)+" \t\t\t\t\t\t");
			    		}
			    		System.out.println();
			    	}
			    	
			    }
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
