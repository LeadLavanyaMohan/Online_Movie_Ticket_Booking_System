package com.AspireSystem;
import java.sql.*;
import java.lang.*;
import java.util.*;
public class Connectivity {
	private static String url="jdbc:mysql://localhost:3306/TicketBooking";
	private static String userName="root";
	private static String passWord="Aspire@12345";
	
	public static Connection getConnection()throws SQLException{
		return DriverManager.getConnection(url,userName,passWord);
		
	}

}
