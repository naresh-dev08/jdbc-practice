package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBCapabilitesTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				//Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB1", "root", "root");
				){
			
			
			 if(con!=null) {
				 DatabaseMetaData dbmd=con.getMetaData();
				 if(dbmd!=null) {
					 System.out.println("DB s/w name ::"+dbmd.getDatabaseProductName());
					 System.out.println("DB s/w version::"+dbmd.getDatabaseProductVersion());
					 System.out.println("SQL keywords ::"+dbmd.getSQLKeywords());
					 System.out.println("Numeric functions::"+dbmd.getNumericFunctions());
					 System.out.println("Max Chars in db table name ::"+dbmd.getMaxTableNameLength());
					 System.out.println("Max Connections allowed ::"+dbmd.getMaxConnections());
					 System.out.println("supports storeed procedures ??"+dbmd.supportsStoredProcedures());
					 System.out.println("Max rowsize ::"+dbmd.getMaxRowSize());
					 System.out.println(" MAx chars in username ::"+dbmd.getMaxUserNameLength());
					 System.out.println("Max cols  count in DB table ::"+dbmd.getMaxColumnsInTable());
					 
				 }//if
			 }//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}

	}//main

}//class
