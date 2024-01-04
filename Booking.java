package com.AspireSystem;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Booking {
	private static int lastbooking=0;
	private int bookingId;
	private String name;
	private String movie;
	private String theatreName;
	private Date date;
	private int ticket;
	private double amount;
	public int generateBookingId() throws SQLException
	{
		lastbooking=BookingConnectivity.getBookingId();
		return ++lastbooking;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		amount=this.ticket*150;
		this.amount = amount;
	}
	public Booking(int booking)
	{
		super();
	}
	public Booking() throws SQLException
	{
		Scanner scanner= new Scanner(System.in);
		System.out.println("----------------------******************************--------------------------");
		bookingId=generateBookingId();
		System.out.println("Your Booking ID: "+bookingId);
		System.out.println("Enter Your Name: ");
		name=scanner.nextLine();
		System.out.println("Enter your movie: ");
		movie=scanner.nextLine();
		System.out.println("Enter Your Theatre Name: ");
		theatreName=scanner.nextLine();
		System.out.println("Enter Your date dd-mm-yyyy ");
		String dataInput=scanner.next();
		System.out.println("Enter number of ticket you want : ");
		ticket=scanner.nextInt();
		amount=this.ticket*150;
		System.out.println("Amount: "+amount);
		System.out.println("----------------------******************************--------------------------");
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
		try {
			setDate(dateFormat.parse(dataInput));//convert a string into data 
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public boolean isAvailable()throws SQLException
	{
		TheatreConnectivity theatreconnectivity=new TheatreConnectivity();
		int capacity =theatreconnectivity.getCapacity(theatreName);
		BookingConnectivity bookingConnectivity=new BookingConnectivity();
		int booked=bookingConnectivity.getBookedCount(theatreName,getDate());
		System.out.println("Amount c: "+capacity);
		System.out.println("Amount b: "+booked);
		int available=capacity-booked;
		int requiredSeat=this.getTicket();
		if(requiredSeat<=available)
			System.out.println("Ticket is available....");
		else
			System.out.println(available+"Tickets is only available....");
		
		System.out.println("Booked: "+booked);
		System.out.println("Available: "+available);
		System.out.println("Required: "+requiredSeat);
		
		return requiredSeat<=available?true:false;
	}
	public void cancelBooking(int bookingId) throws SQLException
	{
		BookingConnectivity bookingConnectivity=new BookingConnectivity();
		if(bookingConnectivity.cancelBooking(bookingId))
		{
			System.out.println("---------**********---------");
			System.out.println("Your booking cancel sucessfully");
			System.out.println("---------**********---------");
		}
	}
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", name=" + name + ", movie=" + movie + ", theatreName="
				+ theatreName + ", date=" + getDate() + ", ticket=" + ticket + "]";
	}
	public void getBookingDetail()
	{
		System.out.println("---------**********---------");
		System.out.println(" Name: "+this.name);
		System.out.println("Theatre name: "+this.theatreName);
		System.out.println("Date "+this.getDate());
		System.out.println("---------**********---------");
	}
	public  int getBookingId() {
		return bookingId;
	}
	public  void setBookingId(int bookingId) {
		bookingId = bookingId;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static void getUserChoiceforBooking() throws SQLException
	{
		int choice=1;
		Scanner scanner=new Scanner(System.in);
		while(choice>=1)
		{
			System.out.println("\t----------------------User choice--------------------------");
			System.out.println("\n\t\t\t\tEnter your choice... \n\t\t\t\t1 ---> booking... \n\t\t\t\t2 ---> cancel booking... \n\t\t\t\t3 ---> View Booking...\n\t\t\t\t4 ---> view theatre detail \n\t\t\t\t5 ---> view profile..\n\t\t\t\t6 ---> Logout.. ");
			choice=scanner.nextInt();
			switch(choice)
			{
				case 1:
				{	System.out.println("----------------------Booking --------------------------");			
					Booking booking=new Booking();
					if(booking.isAvailable())
					{
						BookingConnectivity bookingConnectivity=new BookingConnectivity();
						bookingConnectivity.addBooking(booking);
						System.out.println("Your bookimg is confirmed... ");
					}else
						System.out.println("Sorry...!!!,Theatre is HOUSE FULL!. Please try another theatre or date... ");
					break;
					
					
	
					}
				case 2:
				{
					System.out.println("----------------------Cancel Booking--------------------------");
					System.out.println("Enter your bookingID to cancel: ");
					int bookingId=scanner.nextInt();
					Booking cancelBooking = new Booking(bookingId);
					cancelBooking.cancelBooking(bookingId);
					System.out.println("----------------------******************************--------------------------");
					break;
					
				}
				case 3:
				{
					System.out.println("----------------------View Booking detail--------------------------");
					BookingConnectivity.viewBooking();
					System.out.println("----------------------******************************--------------------------");
					break;
				}
				case 4:
				{
					System.out.println("----------------------View theatre detail--------------------------");
					TheatreConnectivity.displayTheatreDetail();
					System.out.println("----------------------******************************--------------------------");
					break;
				}
				case 5:
				{
					System.out.println("----------------------View Profile--------------------------");
					
					User.viewProfile();
					break;
				}
				case 6:
				{
					System.out.println("----------------------Log out--------------------------");
					User user= new User();
					user.logout();
					choice=0;
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
	public static void entry()
	{
		System.out.println("\n\n####################################################################################################################");
		System.out.println("\n\n\t\t\t\t W E L C O M E  T O  C I N I W O R L D ");
		System.out.println("\n\n####################################################################################################################");
		System.out.println("Enter \n1--> User action..\n2 --> Admin action..");
		Scanner scanner=new Scanner(System.in);
		int entry = scanner.nextInt();
		switch(entry)
		{
		case 1:
			try {
					Movie movie=new Movie();
					//Movie.getMovieDetail(movies);
				User.runApplicationForUser();
			} catch (SQLException exception) {
				System.out.println("runApplicationForUser method didn't invoke properly..");
				exception.printStackTrace();
			}
			break;
		case 2:
			try {
				Admin.runApplicationForAdmin();
			} catch (SQLException exception) {
				System.out.println("runApplicationForAdmin method didn't invoke properly..");
				exception.printStackTrace();
			}
		}
	}
		
		
		
	}
	
	
	
	

