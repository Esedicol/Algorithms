package controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Movie;
import models.Rating;
import models.User;

public class APITest 
{
	private API api;

	public User[] listOfUsers = 
		{
				new User (1L, "Leonard", "Hernandez", 24, 'M', "technician"),
				new User (2L, "Melody", "Roberson", 53, 'F', "other"),
				new User (3L, "Gregory", "Newton", 23, 'M', "writer"),
				new User (4L, "Oliver", "George", 24, 'M', "technician"),
				new User (5L, "Jenna", "Parker", 33, 'F', "other")
		};


	public static Movie[] listOfMovies =
		{
				new Movie(1, "Toy Story (1995)", 1995, "http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)"),
				new Movie(2, "GoldenEye (1995)", 1995, "http://us.imdb.com/M/title-exact?GoldenEye%20(1995)"),
				new Movie(3, "Four Rooms (1995)", 1995, "http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)"),
				new Movie(4, "Get Shorty (1995)", 1995, "http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)"),
				new Movie(5, "Copycat (1995)", 1995, "http://us.imdb.com/M/title-exact?Copycat%20(1995)"),
				new Movie(6, "Shanghai Triad (Yao a yao yao dao waipo qiao) (1995)", 1995, "http://us.imdb.com/Title?Yao+a+yao+yao+dao+waipo+qiao+(1995)"),
				new Movie(7, "Twelve Monkeys (1995)", 1995, "http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995)"),
				new Movie(8, "Babe (1995)", 1995, "http://us.imdb.com/M/title-exact?Babe%20(1995)"),
				new Movie(9, "Dead Man Walking (1995)", 1995, "http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(1995)"),
				new Movie(10, "Richard III (1995)", 1996, "http://us.imdb.com/M/title-exact?Richard%20III%20(1995)")
		};


	public Rating[] ratings = 
		{
				new Rating(1, 7, 1), 
				new Rating(1, 2, 5), 
				new Rating(5, 3, 1), 
				new Rating(5, 5, -3), 
				new Rating(5, 1, 3),
		};


	@Before
	public void setUp() throws Exception
	{
		api = new API();

		for (User user: listOfUsers)
		{
			api.addUser(user.firstName, user.lastName, user.age, user.gender, 
					user.occupation);
		}
		for (Movie movie: listOfMovies)
		{
			api.addMovie(movie.title, movie.year, movie.url);
		}
		for (Rating rating: ratings)
		{
			api.addRating(rating.userId, rating.movieId, rating.rating);
		}

	}



	@After
	public void tearDown() throws Exception
	{
		api = null;
	}

	@Test
	public void testUser() 
	{
		//test the length of listOfUsers before we add a new user//
		assertEquals(listOfUsers.length, api.getUsers().size());

		//add new user test//
		api.addUser("Emmanuel", "Sedicol", 20, 'M', "Student");

		//test length of map after new user is added//
		assertEquals(listOfUsers.length + 1, api.getUsers().size());


		long id = listOfUsers.length + 1;
		assertEquals("Emmanuel", api.getUserById(id).firstName);
		assertEquals("Sedicol", api.getUserById(id).lastName);
		assertEquals(20, api.getUserById(id).age);
		assertEquals('M', api.getUserById(id).gender);
		assertEquals("Student", api.getUserById(id).occupation);

	}

	@Test
	public void testGetUser()
	{
		for(User user: listOfUsers)
		{
			assertEquals(user, api.getUserById(user.userId));
		}			
	}

	@Test
	public void testRemoveUser()
	{
		//test the length of listOfUsers before we remove user//
		assertEquals(listOfUsers.length, api.getUsers().size());

		//remove user//
		User user1 = api.getUserById(1L);
		api.removeUser(user1.userId);
		assertEquals(null, api.getUserById(user1.userId));

		//test that length is -1//
		assertEquals(listOfUsers.length - 1, api.getUsers().size());
	}


	@Test
	public void testMovie()
	{
		//test length of listOfMovies before new movie added//
		assertEquals(listOfMovies.length, api.getMovies().size());

		//add new movie//
		api.addMovie("The Simpsons", 1860, "http://www.theSimpsons.com");

		//new listOfMovies size//
		assertEquals(listOfMovies.length + 1, api.getMovies().size());
	}

	@Test
	public void testGetMovie()
	{
		for(Movie movie: listOfMovies)
		{
			assertEquals(movie, api.getMovieById(movie.movieId));
		}
	}

	@Test
	public void testRemoveMovie()
	{
		//test the length of listOfMovies before we remove movie//
		assertEquals(listOfMovies.length, api.getMovies().size());

		//remove movie//
		Movie movie = api.getMovieById(1);
		api.removeMovie(movie.movieId);
		assertEquals(null, api.getMovieById(movie.movieId));

		//test that length is -1//
		assertEquals(listOfMovies.length - 1, api.getMovies().size());

	}

	@Test
	public void testRating()
	{
		//ratings size before new rating added//
		assertEquals(ratings.length, api.getRatings().size());

		//add rating//
		api.addRating(1L, 1, 5);

		//new ratings size//
		assertEquals(ratings.length + 1, api.getRatings().size());	

		//test getUserRating//
		assertEquals(5, api.getUserRating(1L));

	}


}
