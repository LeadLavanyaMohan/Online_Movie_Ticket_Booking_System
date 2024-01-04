package com.AspireSystem;

import java.sql.*;
import java.util.Scanner;

public class TheatreConnectivity {
	public static void displayTheatreDetail() throws SQLException
	{
		String query="select * from theatre;";
		Connection connect=Connectivity.getConnection();
		Statement statement=connect.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		
		while(resultSet.next())
		{
			System.out.println("**********************************");
			System.out.println("Theatre ID : "+resultSet.getInt(1));
			System.out.println("Theatre Name : "+resultSet.getString(2));
			if(resultSet.getInt(3)==0)
				System.out.println("AC hall : no ");
			else
				System.out.println("AC hall : yes ");
			
			System.out.println("Location : "+resultSet.getString(4));
			System.out.println("Seats : "+resultSet.getInt(5));
			System.out.println("**********************************");
			
		}
		
	}
	public int getCapacity(String theatreName) throws SQLException
	{
		String query="select seat from theatre where theatrename='"+theatreName+"';";
		Connection connect=Connectivity.getConnection();
		Statement statement=connect.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		resultSet.next();
		return resultSet.getInt(1);
		
	}
	public static int getTheatreId() throws SQLException
	{
		String query="select max(id) from theatre;";
		Connection connect=Connectivity.getConnection();
		Statement statement=connect.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		resultSet.next();
		return resultSet.getInt(1);
		
	}
	public static void addSeat() throws SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter theatre name to update...");
		String theatreName;
		theatreName = scanner.nextLine();
		scanner.nextLine();
		System.out.println("Enter seat count ...");
		int seat=scanner.nextInt();
		String query="update theatre set seat="+seat+" where theatrename='"+theatreName+"';";
		Connection connect=Connectivity.getConnection();
		Statement statement =connect.createStatement();
		int rows =statement.executeUpdate(query);
			if(rows>0)
			{
				System.out.println("theatre name: "+theatreName+"seat :"+seat);
				System.out.println("seat detail upadate sucessfully...");
				
			}
			else
				System.err.println("seat detail upadate unsucessfully...");
			
	}
//	public static void addSeat(String theatreName,int seat) throws SQLException
//	{
//		//String query="update theatre set seat=? where theatrename=?;";
//		Connection connect=Connectivity.getConnection();
//		CallableStatement callableStatement= connect.prepareCall("{call addSeat(??)}");
//		callableStatement.setString(1, theatreName);
//		callableStatement.setInt(2,seat);
//		ResultSet resultSet=callableStatement.executeQuery();
//		while(resultSet.next())
//		{
//			System.out.println("Theatre ID : "+resultSet.getInt(1));
//			System.out.println("Theatre Name : "+resultSet.getString(2));
//			if(resultSet.getInt(3)==0)
//				System.out.println("AC hall : no ");
//			else
//				System.out.println("AC hall : yes ");
//			
//			System.out.println("Location : "+resultSet.getString(4));
//			System.out.println("Seats : "+resultSet.getInt(5));
//			
//		}
//		
//	}
	public static void removeTheatre() throws SQLException
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter theatre name to Remove...");
		String theatreName=scanner.nextLine();
		
		String query="delete from theatre where theatrename='"+ theatreName +"';";
		Connection connect=Connectivity.getConnection();
		Statement statement =connect.createStatement();
		int rows =statement.executeUpdate(query);
			if(rows>0)
			{
				System.out.println("theatre name:"+theatreName);
				System.out.println("theatre removed sucessfully...");
			}
			else
				System.err.println("theatre removed unsucessfully...");
			
	}
	public void addTheatre()throws SQLException
	{
		Theatre theatre = new Theatre();
		String query="insert into theatre values(?,?,?,?,?);";
		System.out.println("from c"+theatre.getLocation());
		Connection connect=Connectivity.getConnection();
		PreparedStatement preparedStatement=connect.prepareStatement(query);
		
		preparedStatement.setInt(1,theatre.getTheatreId());
		preparedStatement.setString(2,theatre.getTheatreName());
		preparedStatement.setBoolean(3,theatre.isAc());
		preparedStatement.setString(4,theatre.getLocation());
		preparedStatement.setInt(5,theatre.getSeat());
		int rows=preparedStatement.executeUpdate();
		
		if(rows>0)
			System.out.println("theatre added sucessfully...");
		else
			System.err.println("theatre added unsucessfully...");
		
	}
//	public int getCapacity(String theatreName) throws SQLException
//	{
//		//String query="select seat from theatre where theatrename='"+theatreName+"';";
//		Connection connect=Connectivity.getConnection();
//		CallableStatement callableStatement=connect.prepareCall("{call getCapacity(?)}");
//		callableStatement.setString(1, theatreName);
//		ResultSet resultSet=callableStatement.executeQuery();
//		resultSet.next();
//		return resultSet.getInt(1);
//		
//	}

}
