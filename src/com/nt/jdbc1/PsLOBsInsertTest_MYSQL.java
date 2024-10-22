package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLOBsInsertTest_MYSQL {
   private static final String  ACTOR_INSERT_QUERY="INSERT INTO ACTOR_INFO(ANAME,PHOTO,RESUME) VALUES(?,?,?)";
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1","root", "root");
				PreparedStatement ps=con.prepareStatement(ACTOR_INSERT_QUERY);
				){
			 //read the input values
			String aname=null;
			String photoPath=null,resumePath=null;
			if(sc!=null) {
				System.out.println("Enter  actor name::");
				aname=sc.next();
				System.out.println("Enter actor's photo Path::");
				photoPath=sc.next();
				System.out.println("Enter actor's  resume Path::");
				resumePath=sc.next();
			}
			//create  streams  representing the  photo file and resume file
			try(InputStream is=new FileInputStream(photoPath);  // byte stream for  Binary file
					Reader reader=new FileReader(resumePath);  //character stream for text file
					){
				   //set values to query params
				    if(ps!=null) {
				    	ps.setString(1,aname);
				    	ps.setBinaryStream(2, is);
				    	ps.setCharacterStream(3,reader);
				    	//excute the  pre-compiled SQL Query
				    	int result=ps.executeUpdate();
				    	//process the Result
				    	if(result==0)
				    		System.out.println("Record not inserted");
				    	else
				    		System.out.println("record  inserted");
				    }//if
				
			}//try2
			  
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
