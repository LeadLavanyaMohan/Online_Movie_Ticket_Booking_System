package com.AspireSystem;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class User{ 
	private static int lastUserId=0;
	private int userId;
	private String name;
	private String email;
	private String password;
	private  Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement ;
	private ResultSet resultSet;
	public User() throws SQLException {

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=Connectivity.getConnection();
			
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User(String name)
	{
		
	}
	public void  registration()
	{
		try {
			
			Scanner scanner= new Scanner(System.in);
			System.out.println("----------------------******************************--------------------------");
			System.out.println("----------------------User Registration--------------------------");
			userId=generateUser();
			System.out.println("UserId :"+userId);
			System.out.println("Enter your name: ");
			name=scanner.nextLine();
			System.out.println("Enter your email-ID: ");
			email=scanner.nextLine();
			System.out.println("Enter your password: ");
			password=scanner.nextLine();
			System.out.println("----------------------******************************--------------------------");
			
			String query="insert into users values(?,?,?,?)";
			//connection=Connectivity.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1,userId);
			preparedStatement.setString(2,name);
			preparedStatement.setString(3,email);
			preparedStatement.setString(4,password);
			int rows = preparedStatement.executeUpdate();
			if(rows>0)
			{
				System.out.println("Registered Sucessfully... ");
				login();
			}
			else
			{
				System.err.println("Not registered...");
				registration();
			}
		} catch (SQLException exception) {
			System.err.println("Not registered...");
			exception.printStackTrace();
		}
		 
		
		
	}
	public  static void login()
	{ try {
		System.out.println("----------------------UserLogin--------------------------");
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter your email-ID: ");
		String email=scanner.nextLine();
		System.out.println("Enter your password: ");
		String password=scanner.nextLine();
		String query="select * from users where email=? and password=?;";
		Connection connections=Connectivity.getConnection();
		PreparedStatement preparedStatements=connections.prepareStatement(query);
		preparedStatements.setString(1,email);
		preparedStatements.setString(2,password);
		ResultSet resultSets = preparedStatements.executeQuery();
		boolean isLogin;
		if(resultSets.next()==true)
		{
			System.out.println("Login Sucessfully... ");
			Booking.getUserChoiceforBooking();
			isLogin= true;
		}
		else
		{
			System.err.println("Invalid email or password!!!...");
			isLogin= false;
		}
		} catch (SQLException exception) {
			
			exception.printStackTrace();
		}
	
		
	}
	public static void viewProfile() throws SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter your name to view your profile.. ");
		String name= scanner.nextLine();
		String query="select * from users where userName='"+name+"';";
		Connection connect=Connectivity.getConnection();
		Statement statement=connect.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		
		while(resultSet.next())
		{
			System.out.println("=================================");
			System.out.println("User ID : "+resultSet.getInt(1));
			System.out.println("Name : "+resultSet.getString(2));
			System.out.println("Email : "+resultSet.getString(3));
			System.out.println("=================================");
			
		}
		
	}
	public void logout()
	{
		try
		{
			if(connection!=null&&!connection.isClosed())
				connection.close();
			
			System.err.println(" Logout Successfully...");
			System.out.println(". . . T H A N K S  F O R  V I S I T I N G . . .\n");
			Booking.entry();
		}catch (SQLException exception) {
			System.err.println("Unsuccessful logout...");
			exception.printStackTrace();
		}
		
		
	}
	public int generateUser() throws SQLException
	{
		String query="select max(userid) from users;";
		statement=connection.createStatement();
		resultSet=statement.executeQuery(query);
		resultSet.next();
		lastUserId=resultSet.getInt(1);
		return ++lastUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	public static void runApplicationForUser() throws SQLException
	{
		
		User user=new User();
		Scanner scanner=new Scanner(System.in);
		
		
				System.out.println("Enter \n1 to Registeration ...\n2. to login... ");
				int useraction;
				useraction=scanner.nextInt();
				switch(useraction)
				{
					case 1:
					{
						
						user.registration();
						break;
			}
					case 2:
					{
						
						User login=new User();
						login.login();
						break;
					}
				}
				
		}
			
			
			
		
	}
