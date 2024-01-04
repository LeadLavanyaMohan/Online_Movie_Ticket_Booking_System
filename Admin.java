package com.AspireSystem;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Admin extends User {
	private static int lastAdminId=0;
	private int adminId;
	private String name;
	private String email;
	private String password;
	private static Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement ;
	private static ResultSet resultSet;
	public Admin() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=Connectivity.getConnection();
			
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	
	public void registration()
	{
		try {
			System.out.println("----------------------Admin Registration--------------------------");
			
			Scanner scanner= new Scanner(System.in);
			System.out.println("----------------------******************************--------------------------");
			adminId=generateAdminId();
			System.out.println("Enter your name: ");
			name=scanner.nextLine();
			System.out.println("Enter your email-ID: ");
			email=scanner.nextLine();
			System.out.println("Enter your password: ");
			password=scanner.nextLine();
			System.out.println("----------------------******************************--------------------------");
			String query="insert into admin values(?,?,?,?)";
			connection=Connectivity.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1,adminId);
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
	
	public Admin(String name) throws SQLException {
		super();
		this.name = name;
		System.out.println("Hi... "+name);
	}

	public static void login()
	{ try {
		System.out.println("----------------------Admin Login--------------------------");
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter your email-ID: ");
		String email=scanner.nextLine();
		System.out.println("Enter your password: ");
		String password=scanner.nextLine();
		String query="select * from admin where email=? and password=?;";
		Connection connections=Connectivity.getConnection();
		PreparedStatement preparedStatements=connections.prepareStatement(query);
		preparedStatements.setString(1,email);
		preparedStatements.setString(2,password);
		ResultSet resultSets = preparedStatements.executeQuery();
		if(resultSets.next()==true)
		{
			System.out.println("Login Sucessfully... ");
			getAdminChoice();
		}
		else
		{
			System.err.println("Invalid email or password!!!...");
			login();
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
		String query="select * from admin where adminName='"+name+"';";
		Connection connect=Connectivity.getConnection();
		Statement statement=connect.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		
		while(resultSet.next())
		{
			System.out.println("=================================");
			System.out.println("Admin ID : "+resultSet.getInt(1));
			System.out.println("Name : "+resultSet.getString(2));
			System.out.println("Email : "+resultSet.getString(3));
			System.out.println("=================================");
			
		}
		
	}
	public void logout()
	{
		super.logout();
			
	}
	public int generateAdminId() throws SQLException
	{
		String query="select max(adminid) from admin;";
		statement=connection.createStatement();
		resultSet=statement.executeQuery(query);
		resultSet.next();
		lastAdminId=resultSet.getInt(1);
		return ++lastAdminId;
	}
	
	public  static void getAdminChoice() throws SQLException
	{
		int adminChoice=1;
		Scanner scanner=new Scanner(System.in);
		
		while(adminChoice>=1)
		{
			System.out.println("=================================Admin Choice=======================================================");
			System.out.println("Enter your choice \n1 ---> viewBooking..\n2 ---> Add seat .. \n3 ---> Remove theatre details.. \n4 ---> Add theatre...\n5 ---> View theatre detail..\n6 ---> view Profile..\\n7 ---> Add movie ..\n8 ---> Logout..\n0 ---> Exit..");
			System.out.println("========================================================================================");
			adminChoice=scanner.nextInt();
			switch(adminChoice)
			{
				case 1:
				{
					System.out.println("----------------------ViewBooking--------------------------");
					BookingConnectivity.viewBooking();
					System.out.println("----------------------******************************--------------------------");
					
					break;
				}
				case 2:
				{
					System.out.println("----------------------Add seat --------------------------");
					TheatreConnectivity theatreconnectivity=new TheatreConnectivity();
					theatreconnectivity.addSeat();
					System.out.println("----------------------******************************--------------------------");
					
					break;
					
				}
				case 3:
				{
					System.out.println("----------------------Remove theatre--------------------------");
					TheatreConnectivity theatreconnectivity=new TheatreConnectivity();
					theatreconnectivity.removeTheatre();
					System.out.println("----------------------******************************--------------------------");
					
					break;
					
				}
				case 4:
				{
					System.out.println("----------------------Add theatre--------------------------");
					
					TheatreConnectivity theatreconnectivity=new TheatreConnectivity();
					theatreconnectivity.addTheatre();
					System.out.println("----------------------******************************--------------------------");
					break;
					
				}
				case 5:
				{
					System.out.println("----------------------View theatre detail--------------------------");
					TheatreConnectivity.displayTheatreDetail();
					System.out.println("----------------------******************************--------------------------");	
					break;
				}
				case 6:
				{
					System.out.println("----------------------View Profile--------------------------");
					viewProfile();
					System.out.println("----------------------******************************--------------------------");	
					break;
				}
				case 7:
				{
					System.out.println("----------------------Add Movie--------------------------");
					Movie movie=new Movie();
					movie.addMovie(movie);
					System.out.println("----------------------******************************--------------------------");	
					break;
				}
				case 8:
				{
					System.out.println("----------------------Log out--------------------------");
					Admin admin= new Admin();
					adminChoice=0;
					admin.logout();
					break;
				}
				default:
				{
					System.out.println("Invalid option... ");
					break;
				}
				
			}
		}
		
	}
	public static  void  runApplicationForAdmin() throws SQLException
	{
		Scanner scanner=new Scanner(System.in);
		
		
				System.out.println("Enter \n1 to Registeration ...\n2. to login... ");
				int useraction;
				useraction=scanner.nextInt();
				switch(useraction)
				{
					case 1:
					{
						Admin admin= new Admin();
						admin.registration();
						break;
					}
					case 2:
					{
						Admin admin= new Admin();
						admin.login();
						break;
					}
				}
				
		}

}
