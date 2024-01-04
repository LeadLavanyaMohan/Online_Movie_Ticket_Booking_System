package com.AspireSystem;
import java.sql.SQLException;
import java.util.Scanner;

public class Theatre {
	private static int lastTheatreID=0;
	private int theatreId;
	private String theatreName;
	private boolean ac;
	private String location;
	private int seat;
	
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public Theatre() throws SQLException {
		
		Scanner scanner= new Scanner(System.in);
		System.out.println("----------------------******************************--------------------------");
		this.theatreId=generateTheatreId();
		System.out.println("TheatreId : "+theatreId);
		System.out.println("Enter TheatreName : ");
		this.theatreName=scanner.nextLine();
		System.out.println("Enter Theatre location : ");
		this.location=scanner.nextLine();
		System.out.println("Enter true if theatre is AC or else false : ");
		this.ac=scanner.nextBoolean();	
		System.out.println("Enter count Theatre Seat : ");
		this.seat=scanner.nextInt();
		System.out.println("Enter count Theatre loc : "+this.getLocation());
		System.out.println("----------------------******************************--------------------------");
	}
	public int generateTheatreId() throws SQLException
	{
		lastTheatreID=TheatreConnectivity.getTheatreId();
		return ++lastTheatreID;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public boolean isAc() {
		return ac;
	}
	public void setAc(boolean ac) {
		this.ac = ac;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Theatre [TheatreName=" + theatreName + ", Ac=" + ac+ "Location="+location+ ", Seat=" + seat  +"]";
	}
	public void getTheatreDetail()
	{
		System.out.println("---------**********---------");
		System.out.println("Theatre Name: "+this.theatreName);
		System.out.println("AC: "+this.ac);
		System.out.println("Location: "+this.location);
		System.out.println("Seat : "+this.seat);
		System.out.println("Morning Show: 9:00-12:30");
		System.out.println("Afternoon Show: 1:00-4:30");
		System.out.println("Evening Show: 5:00-8:30");
		System.out.println("Night Show: 9:00-12:30");
		System.out.println("---------**********---------");
			
	}
	

}
