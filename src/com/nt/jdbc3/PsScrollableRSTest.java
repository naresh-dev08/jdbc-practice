package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PsScrollableRSTest {
	private static final String GET_STUDENTS_QUERY="SELECT * FROM STUDENT";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps=con.prepareStatement(GET_STUDENTS_QUERY,
						                                                                                     ResultSet.TYPE_SCROLL_SENSITIVE,
						                                                                                     ResultSet.CONCUR_UPDATABLE);
				//Statement st=con.createStatement();
				ResultSet rs=ps.executeQuery();
				){
			if(rs!=null) {
				
				System.out.println("records (top- bottom)");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}
				System.out.println("______________________________________");
				rs.afterLast();
				System.out.println("records (bottom - top)");
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}
				System.out.println("_______________________________");
				rs.first();
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				rs.last();
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				rs.relative(-2);
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				rs.relative(1);
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				rs.absolute(3);
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				rs.absolute(-2);
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				rs.absolute(-4);
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
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
