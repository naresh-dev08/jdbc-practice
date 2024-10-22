//Cs_ProcedureCallTest.java
package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


/*create or replace procedure  P_SUM( x in number, y in number,z out number)as
begin
 z:=x+y;
end;
/
*/

public class Cs_ProcedureCallTest {
	private static final String  CALL_PROCEDURE_QUERY="{call p_sum(?,?,?)}";
			

	public static void main(String[] args) {
		
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				CallableStatement  cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				Scanner  sc=new Scanner(System.in);){
			
			  //read input values
			  int val1=0,val2=0;
			 if(sc!=null) {
				 System.out.println("Enter  Value1::");
				 val1=sc.nextInt();
				 System.out.println("Enter value2::");
				 val2=sc.nextInt();
			 }
			
			  if(cs!=null) {
				  //register OUT params with JDBC data types
				  cs.registerOutParameter(3, Types.INTEGER);
				  //set values to Query IN params
				cs.setInt(1, val1);  
				cs.setInt(2, val2);
				// call PL/SQL procedure
				 cs.execute();
				 //get result from OUT param
				 int result=cs.getInt(3);
				 //display the result
				 System.out.println("Sum is ::"+result);
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
