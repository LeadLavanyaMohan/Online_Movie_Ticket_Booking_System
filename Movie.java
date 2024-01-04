package com.AspireSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Movie {
	private String movieName;
	private String genre;
	private  Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement ;
	private ResultSet resultSet;
	public Movie() {
		
		
	}
	public Movie(int choice) {
		System.out.println(" Your movie list ");
		
	}
	public void addMovie() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("---------**********---------");
		System.out.println("Enter movie: ");
		this.movieName = scanner.nextLine();
		System.out.println("Enter movie genre: ");
		this.genre=scanner.nextLine();
		System.out.println("---------**********---------");
	}
	public static void showMovie()
	{
        System.out.printf("%-15s %-15s \n", "Movie Name", "Genre");
        System.out.println("-----------------------------------------------------");

        
       
			
			    System.out.printf("%-15s %-15s %-15d %-15d\n",movie.getMovieName(),movie.getGenre());
		
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
//	public void getMovieDetail()
//	{
//		System.out.println("---------**********---------");
//		System.out.println(this.movieName);
//		System.out.println(this.genre);
//		System.out.println("---------**********---------");
//		
//	}
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
		{
			try {
				Movie movie=new Movie(entry);
				Movie.getMovieDetail(movies);
			User.runApplicationForUser();
		} catch (SQLException exception) {
			System.out.println("runApplicationForUser method didn't invoke properly..");
			exception.printStackTrace();
		}
		break;
		}
		case 2:
		{
			try {
				Admin.runApplicationForAdmin();
			} catch (SQLException exception) {
				System.out.println("runApplicationForAdmin method didn't invoke properly..");
				exception.printStackTrace();
			}
		}
		}
	}
}
