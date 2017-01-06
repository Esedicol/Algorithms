package utils;

import java.util.Comparator;

import models.Movie;

public class MovieComparator implements Comparator<Movie>{


	//get average rating of one movie and compare it to the average of another movie//
	@Override
	public int compare(Movie movieOne, Movie movieTwo)
	{
		//if rating of movie one is less than than rating on movie two return -1//
		if (movieOne.getAverageRating() < movieTwo.getAverageRating()) 
			return +1;
		
		//if rating of movie one is greater than rating on movie two return -1//
		else if (movieOne.getAverageRating() > movieTwo.getAverageRating()) 
			return -1;
		
		//if both are equal return 0//
		else
			return 0;
	}

}
