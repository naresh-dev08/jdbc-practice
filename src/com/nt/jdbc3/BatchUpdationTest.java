package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdationTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement st=con.createStatement();
				){
			if(st!=null) {
				//add queries to the batch
				st.addBatch("INSERT INTO STUDENT VALUES(110,'raja1','hyd1',78.99)");
				st.addBatch("UPDATE STUDENT SET AVG=AVG+2.0 WHERE SADD='hyd' ");
				st.addBatch("DELETE FROM STUDENT WHERE SNO>=1000");
				
				 //execute the batch
				int[] result=st.executeBatch();
				
				//process the results
				int sum=0;
				if(result!=null) {
					for(int i=0;i<result.length;++i)
						sum=sum+result[i];
				}//if
				System.out.println("no.of records that are effected ::"+sum);
			}//if
			
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
