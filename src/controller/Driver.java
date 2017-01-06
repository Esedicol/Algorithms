package controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import models.Movie;
import models.User;
import utils.In;
import utils.Serializer;
import utils.XMLSerializer;

public class Driver {
	Scanner input = new Scanner(System.in);
	API api;	
	Movie movie;
	User user;

	public Driver() throws Exception
	{
		File fileName = new File("likeMovies.xml");

		//if such file exist then that file is serialized//
		if (fileName.isFile()) 
		{
			System.out.println("\n" + "The File called >>>" + fileName + "<<< exist");
			Serializer serializer = new XMLSerializer(fileName);
			api = new API(serializer); 
			api.load();
		}
		else
		{
			System.out.println("\n" + "File cant be found");
			//create new file//
			Serializer newSerializer = new XMLSerializer(new File("likeMovies.xml")); 
			api = new API(newSerializer); 

			//reads the external files and load them onto file "likeMovies.xml//
			api.loadExternalData(); 
			api.load();
		}
	}


	public static void main(String args[]) throws Exception 
	{
		Driver app = new Driver();
		app.run();
	}

	
	
	// main menu//
	private int mainMenu() {
		System.out.println("-------------------------");
		System.out.println("Welcome to Main Menu");
		System.out.println("  1) Add User");
		System.out.println("  2) Add Movie");
		System.out.println("  3) Remove User");
		System.out.println("  4) Get movie detail");
		System.out.println("  5) Rate movie");
		System.out.println("  6) Get user rating");
		System.out.println("  7) Get top 10 movies");
		System.out.println("  8) Get top 10 rating");
		System.out.println("  9) Get user recomendations");
		System.out.println("-------------------------");
		System.out.println("  0) Exit");
		System.out.print("==>>");
		int option = input.nextInt();
		return option;
	}

	private void run() throws Exception {
		int option = mainMenu();
		while (option != 0) {
			switch (option) {
			case 1:
				addUser();
				break;

			case 2:
				addMovie();
				break;

			case 3:
				removeUser();
				break;

			case 4:
				getMovie();
				break;

			case 5:
				addRating();
				break;

			case 6:
				getUserRating();
				break;

			case 7:
				getMovie();
				break;

			case 8:
				getTopTenRatings();
				break;

			case 9:
				getMovie();
				break;

			default:
				System.out.println("Invalid option selected.");
				break;
			}
			run();
		}
		mainMenu();
	}


	// adding user//
	public void addUser() throws IOException {
		System.out.println("Enter First Name");
		String firstName = In.readString();

		System.out.println("Enter Last Name");
		String lastName = In.readString();

		System.out.println("Enter Age");

		//Loop if non-numerical or within range
		boolean realAge = false;
		int age = 0;
		while (!realAge) 
		{
			try 
			{
				System.out.println("PLEASE enter a real age!");
				age = input.nextInt();
				if (age >=0 && age <=99)
				{
					realAge = true;
				}
				else
				{
					System.out.println("You cannot possibly be negative aged!");
				}
			} 
			catch (Exception e) 
			{
				In.readInt();
				System.out.println("Positive numerical inputs only!");
			}
		}

		System.out.println("Enter Gender  (M/F)");
		char gender = In.readChar();

		System.out.println("Enter Occupation");
		String occupation = In.readString();

		api.addUser(firstName, lastName, age, gender, occupation);
	}

	//add movie//
	public void addMovie() {
		System.out.println("Enter Title");
		String title = input.next();

		System.out.println("Enter Year");
		int year = input.nextInt();

		System.out.println("Enter Url");
		String url = input.next();

		api.addMovie(title, year, url);
	}

	//add rating//
	public void addRating() {
		System.out.println("Enter userID");
		Long userId = input.nextLong();

		System.out.println("Enter movieID you want to rate");
		Long movieId = 0L;
		if (api.listOfMovies.containsKey(movieId)){
			movieId = input.nextLong();
		}

		System.out.println("Enter Rating (-5, -3, 1, 3, 5)");
		int rating =0;
		if(rating <=5 && rating >= 5) {
			rating = input.nextInt();
		} else { 
			System.out.println("You can only enter a rating specified");
		}

		api.addRating(userId, movieId, rating);
	}

	// remove user//
	public void removeUser() {
		System.out.println("Enter Id of the user you want to delete");
		Long userId = 0L;
		if(api.listOfUsers.containsKey(userId))
		{
			userId = input.nextLong();
		}

		api.removeUser(userId);
	}


	//get user rating//
	public void getUserRating() {
		System.out.println("Enter Id of the User you want to get");
		Long userId = 0L;
		if(api.ratings.contains(userId)) 
		{
			userId = input.nextLong();
		}
		api.getUserRating(userId);
	}

	//get movie//
	public void getMovie() {
		System.out.println("Enter Id of the Movie you want to get");
		Long movieId = 0L;
		if(api.listOfMovies.containsKey(movieId))
		{
			movieId = input.nextLong();
		}

		api.getMovieById(movieId);
	}

	public void getTopTenMovies(){
		System.out.println(api.getTopTenMovies());
	}

	public void getTopTenRatings(){
		System.out.println(api.getTop10Ratings());
	}

	/*
	public void getUserRecomendations() {
		movie.getAverageRating(api.getTop10Ratings(api.ratings));
	}*/
}