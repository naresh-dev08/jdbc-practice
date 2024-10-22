//DateConversionTest.java
package com.nt.jdbc1;

import java.text.SimpleDateFormat;

public class DateConvertionsTest {

	public static void main(String[] args) throws Exception {
	    String s1="10-12-2020"; //dd-MM-yyyy;
	    //Convert String  date value to java.text.SimpleDateFormat class object
	    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	    java.util.Date ud1=sdf.parse(s1);
	    System.out.println("util date ::"+ud1);
	    
	    //converting java.util.Date class object to  java.sql.Date class object
	    long ms=ud1.getTime(); // get Date in Milliseconds  w.r.t  jan 1st 1970 00:00 hours
	    java.sql.Date sqd1=new java.sql.Date(ms);
	    System.out.println("sql date is ::"+sqd1);
	    
	    // if String date value pattern is  "yyyy-MM-dd"  then it can conveted directly to
	    //  to java.sql.Date class obj   with out converting into java.util.Date class obj
	    String s2="1990-10-20";  //yyyy-MM-dd
	    java.sql.Date sqd2=java.sql.Date.valueOf(s2);   //static method of java.sql.Date class
	    System.out.println(sqd2);
	    
	    // Converting java.sql.Date/java.util.Date class obj to  String date value in the required pattern
	     SimpleDateFormat  sdf11=new SimpleDateFormat("MMM-dd-yyyy");  //enduser choice pattern
	     String  sd2=sdf11.format(sqd2);
	     System.out.println("String date value ::"+sd2);
	     
	     
	     
	    
	    System.out.println("===========================================");
	  
	    //coverting   String time value to java.sql.Time object
	    String  t1="20:10:20"; // hh:mm:ss
	  	    java.sql.Time sqt=java.sql.Time.valueOf(t1);
	    System.out.println("sql  time ::"+sqt);
	    
	    //converting java.sql.Time obj to   String  time value
	          String  st=sqt.toString();
	          System.out.println("String Time value::"+st);
	          
	           System.out.println("==========================");
	    
	    //Converting  String date and time to  java.sql.Timestamp obj
	    String  dt1="10-12-1990 2:12:34"; // dd-MM-yyyy hh:mm:ss
	    SimpleDateFormat  sdf2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	    java.util.Date ud2=sdf2.parse(dt1);
       long ms1=ud2.getTime();
       java.sql.Timestamp sqts=new java.sql.Timestamp(ms1);
       System.out.println(sqts);
	    
	    // if  the given date,time value is in  "YYYY-MM-dd hh:mm:ss" then
         // it converted  directly to java.sql.Timestamp object with out converting it 
       //  java.util.Date class obj and other formats..
         String dt2="1990-10-20 2:04:34";  // yyyy-MM-dd hh:mm:ss
          java.sql.Timestamp sqts1=java.sql.Timestamp.valueOf(dt2);
          System.out.println(" sql date ,time values ::"+sqts1);
          
          //Converting  java.sql.TimeStamp class obj to  String  Date,time value
          java.util.Date ud22= new java.util.Date(sqts1.getTime());
           SimpleDateFormat  sdf22=new SimpleDateFormat("MM-yyyy--dd mm:hh:ss");
           String  sts1=sdf22.format(ud22);
           System.out.println("String timestamp value::"+sts1);
           
           
          
	    
	    

	}

}
