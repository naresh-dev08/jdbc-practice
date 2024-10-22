//PropertiesFileTest.java
package com.nt.jdbc3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesFileTest {

	public static void main(String[] args) {
		
		try(InputStream is=new FileInputStream("src/com/nt/commons/Info.properties");){
		    // Load the properties file content to java.util.Properties class obj
			    Properties props=new Properties();
		        props.load(is);
		       
		        System.out.println("Properties class obj data ::"+props);
		        System.out.println(" per.name key value :: "+props.getProperty("per.name"));
		        System.out.println(" per.age key value :: "+props.getProperty("per.age"));

		}
		catch(IOException ioe) {
	      ioe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
