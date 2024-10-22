//RSMetaDataTest1.java
package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class RSMetaDataTest1 {

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
			    		System.out.println("column count::"+i);
			    		System.out.println("column name ::"+rsmd.getColumnLabel(i));
			    		System.out.println("colmun type ::"+rsmd.getColumnTypeName(i));
			    		System.out.println("column display size ::"+rsmd.getColumnDisplaySize(i));
			    		System.out.println("column precision value::"+rsmd.getPrecision(i));
			    		System.out.println("column scale value::"+rsmd.getScale(i));
			    		System.out.println("Column is Nullable ??"+rsmd.isNullable(i));
			    		System.out.println("Column is Signed ??"+rsmd.isSigned(i));
			    		System.out.println("+++++++++++++++++++++++++++++++++");
			    	}//for
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
