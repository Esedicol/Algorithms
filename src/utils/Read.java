package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Movie;
import models.Rating;
import models.User;

public class Read {

	Map<Long, User> listOfUsers = new HashMap<>(); 
	Map<Long, Movie> listOfMovies = new HashMap<>(); 
	List<Rating> ratings = new ArrayList<>(); 

	//read external users file//
	public Map<Long, User> readUsers(String file) throws Exception
	{
		File fileName = new File(file);
		In inUsers = new In(fileName);

		String delims = "[|]";
		List<User> users = new ArrayList<User>();
		while(!inUsers.isEmpty())
		{
			String userDetails = inUsers.readLine();
			String[] userTokens = userDetails.split(delims);
			if (userTokens.length == 7)
			{
				long userId			= Long.parseLong(userTokens[0]);
				String firstName	= userTokens[1];
				String lastName		= userTokens[2];
				int age				= Integer.parseInt(userTokens[3]);
				char gender			= userTokens[4].charAt(0);
				String occupation	= userTokens[5];

				users.add(new User(userId, firstName, lastName,  age, gender, occupation));
			}			
			else
			{
				throw new Exception("Invalid member length: " + userTokens.length);
			}
		}
		return listOfUsers;
	}

	//read external movie file//
	public Map<Long, Movie> readMovies(String file) throws Exception 
	{
		File fileName = new File(file);
		In inMovies = new In(fileName);
		String delims = "[|]";

		while (!inMovies.isEmpty())
		{
			String movieDetails = inMovies.readLine();
			String[] movieTokens = movieDetails.split(delims);

			if (movieTokens.length == 23) 
			{
				long movieId = Long.parseLong(movieTokens[0]);
				String title = movieTokens[1];
				String theYear = movieTokens[2];
				int year = 0;
				if (theYear != null && theYear.length() > 0)
				{
					year = Integer.parseInt(theYear.substring(7));
				}
				else
				{
					year = 1996;
				}
				String url = movieTokens[3];

				Movie movie = new Movie(movieId, title, year, url);
				listOfMovies.put(movieId, movie);
			}
			else
			{
				throw new Exception("Invalid member length: "+ movieTokens.length);
			}
		}
		inMovies.close();
		return listOfMovies;
	}

	
	//read external rating file//
	public List<Rating> readRatings(String file) throws Exception
	{
		File fileName = new File(file);
		In inRatings = new In(fileName);
		String delims = "[|]";

		while (!inRatings.isEmpty())
		{
			String ratingDetails = inRatings.readLine();
			String[] ratingTokens = ratingDetails.split(delims);

			if (ratingTokens.length == 4) 
			{
				long userId = Long.parseLong(ratingTokens[0]);
				long movieId = Long.parseLong(ratingTokens[1]);
				Integer rating = Integer.parseInt(ratingTokens[2]);
				Rating r = new Rating(userId, movieId, rating);

				ratings.add(r);

			}
			else
			{
				throw new Exception("Invalid member length: "+ ratingTokens.length);
			}
		}
		inRatings.close();
		return ratings;
	}

}


