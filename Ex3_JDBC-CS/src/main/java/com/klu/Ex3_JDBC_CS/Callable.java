package com.klu.Ex3_JDBC_CS;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Scanner;

//import com.mysql.cj.jdbc.CallableStatement;

public class Callable {
	
	public void storedProcedureWithoutParameter() throws Exception
	{
		Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Class Loaded");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klu12","root","root");
        System.out.println("Connection Established");
        
        CallableStatement cstmt = con.prepareCall("{ CALL  getDetails() }");
        ResultSet rs = cstmt.executeQuery();
        
        while(rs.next()) {
        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getDouble(4)+" "+rs.getString(5));
        }
        
        con.close();
        
	}

	public void with_OUT_Parameter() throws Exception 
	{
		Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Class Loaded");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klu12","root","root");
        System.out.println("Connection Established");
        
        CallableStatement cstmt = con.prepareCall("{ CALL  getCount(?) }");
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.executeQuery();
        int n = cstmt.getInt(1);
        System.out.println("Total Number of records= "+n);
        
       con.close();
	}	
	
	public void  with_IN_Parameter(Scanner sc) throws Exception
	{
		Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Class Loaded");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klu12","root","root");
        System.out.println("Connection Established");
        
        CallableStatement cstmt = con.prepareCall("{ CALL  getById(?) }");
        System.out.println("Enter ID: ");
        int Id = sc.nextInt();
        cstmt.setInt(1, Id);
        cstmt.execute();
        ResultSet rs = cstmt.executeQuery();
        while(rs.next())
    	{
    		System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" \t"+rs.getInt(3)+" \t"+rs.getDouble(4)+" "+rs.getString(5));
    	}
        con.close();
        cstmt.close();
	}
	 
	public void with_INOUT_Parameter(Scanner sc) throws Exception 
	{
		Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Class Loaded");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klu12","root","root");
        System.out.println("Connection Established");
        
        CallableStatement cstmt = con.prepareCall("{ CALL  getCountByID(?) }");
        System.out.println("Enter ID: ");
        int Id = sc.nextInt();
        cstmt.setInt(1, Id);
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.executeUpdate();
        int n = cstmt.getInt(1);
        System.out.println("Total Number of records= "+n);
        
       con.close();
       cstmt.close();

	}
	
	public static void main(String[] args)  throws Exception
	{
		  Callable c = new Callable();
		  Scanner sc = new Scanner(System.in);
	        boolean flag = true;
	        while(flag) {
	          System.out.println("1.WITHOUT\n2.OUT\n3.IN\n4.IN_OUT\n5.Exit\n");
	          int option = sc.nextInt();
		          switch (option) {
			        case 1:
			        	c.storedProcedureWithoutParameter();
			        	break;
			        case 2:
			        	c.with_OUT_Parameter();
			        	break;
			        case 3:
			        	c.with_IN_Parameter(sc);
			        	break;
			        case 4:
			        	c.with_INOUT_Parameter(sc);
			        	break;
			        default:
			        	flag = false;
			        	System.out.println("Exiting the program");
			        	break;
		        }
	        }
	        sc.close();
		  
		  
		  
		  
		    //c.storedProcedureWithoutParameter();
		   // c.with_OUT_Parameter();
		  //c.with_IN_Parameter();
		 //c.with_INOUT_Parameter();
	}

}
