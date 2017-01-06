package models;


import static com.google.common.base.MoreObjects.toStringHelper;
import com.google.common.base.Objects;

public class Rating
{

	public long userId;
	public long movieId;
	public int rating;
	
	public Rating(long userId, long movieId, int rating)
	{
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

	public String toString()
	{
		return toStringHelper(this)
				.addValue(userId)
				.addValue(movieId)
				.addValue(rating)
				.toString();
	}

	@Override  
	public int hashCode()  
	{  
		return Objects.hashCode(this.userId, this.movieId, this.rating);  
	}  

	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Rating)
		{
			final Rating other = (Rating) obj;
			return Objects.equal(userId, other.userId) 
					&& Objects.equal(movieId,  other.movieId)
					&& Objects.equal(rating,  other.rating);
		}
		else
		{
			return false;
		}
	}

}

