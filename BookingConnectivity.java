package com.AspireSystem;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class BookingConnectivity {

	public int getBookedCount(String theatreName,Date date)throws SQLException
	{
		String query="select sum(seat) from booking where theatrename=? and visit_date=?";
		Connection connect=Connectivity.getConnection();
		PreparedStatement preparedStatement=connect.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());//convert a java date into sql date
		preparedStatement.setString(1, theatreName);
		preparedStatement.setDate(2, sqldate);
		ResultSet resultSet =preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getInt(1);
		
	}
	
	public void addBooking(Booking booking)throws SQLException
	{
		String query="insert into booking values(?,?,?,?,?,?,?);";
		Connection connect=Connectivity.getConnection();
		PreparedStatement preparedStatement=connect.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(booking.getDate().getTime());//convert a java date into sql date
		preparedStatement.setInt(1, booking.getBookingId());
		preparedStatement.setString(2,booking.getName());
		preparedStatement.setString(3,booking.getMovie());
		preparedStatement.setString(4,booking.getTheatreName());
		preparedStatement.setDate(5,sqldate);
		preparedStatement.setInt(6,booking.getTicket());
		preparedStatement.setDouble(7,booking.getAmount());
		preparedStatement.executeUpdate();
		
	}
	public static void viewBooking()throws SQLException
	{
		String query="select * from booking";
		Connection connect=Connectivity.getConnection();
		Statement statement =connect.createStatement();
		ResultSet resultSet =statement.executeQuery(query);
		
		while(resultSet.next())
		{
			System.out.println("**********************************");
			System.out.println("Booking ID :"+resultSet.getInt(1));
			System.out.println("Name : "+resultSet.getString(2));
			System.out.println("Movie : "+resultSet.getString(3));
			System.out.println("Theatre : "+resultSet.getString(4));
			System.out.println("Date : "+resultSet.getDate(5));
			System.out.println("Seat: "+resultSet.getInt(6));
			System.out.println("Amount: "+resultSet.getDouble(7));
			System.out.println("**********************************");
		}
		
	}
	public boolean cancelBooking(int bookingId)throws SQLException
	{
		String query="delete from booking where BookingId="+bookingId;
		Connection connect=Connectivity.getConnection();
		Statement statement =connect.createStatement();
		int cancel =statement.executeUpdate(query);
		if(cancel>0)
			return true;
		else
			return false;
	}
	public static int getBookingId() throws SQLException
	{
		String query="select max(BookingId) from booking;";
		Connection connect=Connectivity.getConnection();
		Statement statement=connect.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		resultSet.next();
		return resultSet.getInt(1);
		
	}
}
