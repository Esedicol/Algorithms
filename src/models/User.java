package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class User
{
	//variables
	public long userId;
	public String firstName;
	public String lastName;
	public char gender;
	public int age;
	public String occupation;
	
	//constructor
	public User(long userId, String firstName, String lastName, int age, char gender, String occupation)
	{
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.occupation = occupation;
	}

	//toString
	@Override
	public String toString()
	{
		return toStringHelper(this)
				.addValue(userId)
				.addValue(firstName)
				.addValue(lastName)   
				.addValue(age)
				.addValue(gender)
				.addValue(occupation)
				.toString();
	}

	@Override  
	public int hashCode()  
	{  
		return Objects.hashCode(this.userId, this.firstName, this.lastName, this.age, this.gender,this.occupation);  
	} 

	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof User)
		{
			final User other = (User) obj;
			return Objects.equal(userId, other.userId) 
					&& Objects.equal(firstName,  other.firstName)
					&& Objects.equal(lastName,  other.lastName)
					&& Objects.equal(age,  other.age)
					&& Objects.equal(gender,  other.gender)
					&& Objects.equal(occupation,  other.occupation);
		}
		else
		{
			return false;
		}
	}

}
