package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Movie;
import models.Rating;
import models.User;
import utils.MovieComparator;
import utils.Read;
import utils.Serializer;

public class API {
	API api;
	Read read = new Read();
	private Serializer serializer;

	Map<Long, User> listOfUsers = new HashMap<>();
	Map<Long, Movie> listOfMovies = new HashMap<>();
	List<Rating> ratings = new ArrayList<>();

	Map<String, User> login = new HashMap<>();


	public API()
	{}

	public API(Serializer serializer) throws Exception
	{
		this.serializer = serializer;
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception
	{
		serializer.read();

		ratings      =   (List<Rating>)       serializer.pop();
		listOfMovies =   (Map<Long, Movie>)   serializer.pop();
		login        =   (Map<String, User>)  serializer.pop();
		listOfUsers  =   (Map<Long, User>)    serializer.pop();
	}

	public void store() throws Exception
	{
		serializer.push(listOfUsers);
		serializer.push(login);
		serializer.push(listOfMovies);
		serializer.push(ratings);
		serializer.write(); 
	}
	public void loadExternalData() throws Exception
	{

		String userFile = ("./File/small/users5.dat");
		String movieFile = ("./File/small/items5.dat");
		String ratingFile = ("./File/small/ratings5.dat");

		listOfUsers  = read.readUsers(userFile);
		listOfMovies = read.readMovies(movieFile);
		ratings      = read.readRatings(ratingFile);

		store();
	}


	//Add new user//
	public User addUser(String firstName, String lastName, int age, char gender, String occupation)
	{

		Long newUserId = 0L;
		for (Long key: listOfUsers.keySet())
		{
			if (key > newUserId)
			{
				newUserId = key;
			}
		}

		long userId = newUserId + 1;
		User user = new User(userId, firstName, lastName, age, gender, occupation);
		listOfUsers.put(user.userId, user);
		return user;
	}


	//add new movie//
	public Movie addMovie(String title, int year, String url)
	{
		long movieId = listOfMovies.size() + 1;
		Movie movie = new Movie(movieId, title, year, url);
		listOfMovies.put(movieId, movie);
		return movie;
	}


	//Remove user via user id//
	public void removeUser(long id) 
	{
		if (listOfUsers.containsKey(id))
		{
			listOfUsers.remove(id);			
		}
	}


	//remove movie via movieId//
	public void removeMovie(long id)
	{
		if(listOfMovies.containsKey(id))
		{
			listOfMovies.remove(id);
		}
	}

	//add rating by user (user.id) to a movie(movie.id)
	public Rating addRating(long userId, long movieId, int rating)
	{
		Rating newRating = new Rating(userId, movieId, rating);
		ratings.add(newRating);
		return newRating;

	}

	public Map<Long, Movie> getMovies()
	{
		return listOfMovies;
	}

	public Movie getMovieById(long id)
	{
		return listOfMovies.get(id);
	}

	public Map<Long, User> getUsers()
	{
		return listOfUsers;
	}

	public User getUserByUserName(String userName)
	{
		return login.get(userName);
	}

	public User getUserById(long id)
	{
		return listOfUsers.get(id);
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	//get the rating of a user via user Id//
	public int getUserRating(Long id)
	{
		int userRating = 0;
		for (Rating r : ratings) 
		{
			if (r.userId == id)
			{
				userRating = r.rating;
			}
		}
		return userRating;
	}

	//get the the top 10 movies in the movies Map//
	public List<Movie> getTopTenMovies()
	{
		MovieComparator compareMovie = new MovieComparator();
		List<Movie> topTenMovies = new ArrayList<>(listOfMovies.values());

		Collections.sort(topTenMovies, compareMovie);

		if (topTenMovies.size() > 10)
		{
			//return items from index 0 to 9 in the topTenMovies//
			return topTenMovies.subList(0, 9);
		}
		else
		{
			return topTenMovies;
		}
	}


	/*
	public double getUserRecomendation(Long id)
	{
		//get top ten ratings//
		//get average of the top ten ratings//
		//sort//
		//get top 5 ratings//
		//get the movieId of the top 5 ratings//
		//return//


		if(!ratings.isEmpty()) {
			for(Rating r : ratings){		
				Double sum += r.rating;
			}
			return sum.doubleValue() / r.rating.size();
		}	
	}
	 */


	//get the top ten ratings//

	public List<Rating> getTop10Ratings() 
	{
		ArrayList<Rating> colection = new ArrayList<>();
		Integer[] rateValues = new Integer[5];
		rateValues[0] = 5;
		rateValues[1] = 3;
		rateValues[2] = 1;
		rateValues[3] = -3;
		rateValues[4] = -5;

		int counter = 0;


		for (Rating r : ratings)
		{
			for(int i = 0; i < rateValues.length; i++)

				if (r.rating == rateValues[i] && counter < 10)
				{
					counter++;
					colection.add(r);
				} 
		} return colection;		
	}

}

