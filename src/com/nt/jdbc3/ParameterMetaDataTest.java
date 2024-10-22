package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest {
	private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		
		
		try(//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1", "root", "root");
				PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
				){
			 ParameterMetaData pmd=null;
			 if(ps!=null) {
				 pmd=ps.getParameterMetaData();
			 }
			if(pmd!=null) {
				int paramsCount=pmd.getParameterCount();
				for(int i=1;i<paramsCount;++i) {
					System.out.println("parameter count::"+i);
					System.out.println("parameter mode::"+pmd.getParameterMode(i));
					System.out.println("parmaeter type ::"+pmd.getParameterTypeName(i));
					System.out.println("Is parameter Nullable ?? "+pmd.isNullable(i));
					System.out.println("Is parameter Signed ?? "+pmd.isSigned(i));
					System.out.println("Parmaeter precision value:: "+pmd.getPrecision(i));
					System.out.println("parmaeter scale value ::"+pmd.getScale(i));
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
