package com.nt.jdbc1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsLOBsRetrieve_Oracle {
	 private static final String  GET_ACTOR_INFO_BY_ID="SELECT * FROM ACTOR_INFO WHERE AID=?";

	public static void main(String[] args) {

         try(Scanner sc=new Scanner(System.in);
        		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
        		 PreparedStatement ps=con.prepareStatement(GET_ACTOR_INFO_BY_ID);
        		 ){
        	   //read input values
        	  int id=0;
        	   if(sc!=null) {
        		   System.out.println("Enter  Actor id::");
        		   id=sc.nextInt();
        	   }
        	   
        	    if(ps!=null) {
        	    	//set value to the query param
        	    	ps.setInt(1, id);
        	    	 
        	    	 //execute the SQL Query
        	    	try(ResultSet rs=ps.executeQuery();
        	    			OutputStream os=new FileOutputStream("retrieve_photo.jpeg");
        	    			Writer writer=new FileWriter("retrieve_bio_data.txt");
        	    			){   //nested try with resource
        	    		
        	    		  
        	    
        	    		if(rs!=null) {
        	    			 if(rs.next()) {
        	    				 int no=rs.getInt(1);
        	    				//  get InputStream ,Reader object  from ResultSet object representing the BLOB, CLOB cols data of DB table
        	    				 String name=rs.getString(2);
        	    			try(InputStream	 is=rs.getBinaryStream(3);
        	    				 Reader reader=rs.getCharacterStream(4);){   //nested try with resource
        	    				 // write InputStream, Reader obj related files content to  dest files pointed by OutputStream , Writer objs
        	    				 IOUtils.copy(is,os);
        	    				 IOUtils.copy(reader,writer);
        	    				 System.out.println(no+"....."+name+" Actor's   resume, photo content is retriveed from the DB table cols and placed in to the files");
        	    			   }//try3
        	    			 }//if
        	    			 else {
        	    				 System.out.println("Actor  Information is not found");
        	    			 }//else
        	    			
        	    		}//if
        	    		
        	    	}//try2
        	    }//if
        	 
         }//try1
         catch(SQLException se) {
        	 se.printStackTrace();
         }
         catch(IOException ioe) {
        	 ioe.printStackTrace();
         }
         catch(Exception e) {
        	 e.printStackTrace();
         }

	}//main
}//class
