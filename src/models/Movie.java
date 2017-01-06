package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

public class Movie
{
	public long movieId;
	public String title;
	public int year;
	public String url;

	//add rating on movie by  user id//
	public Map<Long, Rating> ratingsByUser = new HashMap<>();

	public Movie(long movieId, String title, int year, String url)
	{
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.url = url;
	}

	public void addUserRatings(Long userId, Rating rating)
	{
		ratingsByUser.put(userId, rating);
	}

	public double getAverageRating()
	{
		double getTotal = 0.0; 
		double getAverage = 0.0;

		for (Rating rating: ratingsByUser.values())
		{
			getTotal += rating.rating;
		}
		if (ratingsByUser.size() > 0)
		{
			return getAverage = getTotal/ratingsByUser.size();
		}
		return getAverage;
	}


	@Override
	public String toString()
	{
		return toStringHelper(this)
				.addValue(title)
				.addValue(year)   
				.addValue(url)
				.toString();
	}

	@Override  
	public int hashCode()  
	{  
		return Objects.hashCode(this.title, this.year, this.url);  
	} 

}
