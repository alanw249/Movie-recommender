package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import utils.ToJsonString;

import com.google.common.base.Objects;




@SuppressWarnings("unused")
public class User {

	
static Long counter = (long) 01;
public Long id;
public String firstName;
public String lastName;
public String age;
public String gender;   //True = Male | False = Female
public String occupation;

public List<Rating> ratings = new ArrayList<>();
	
	/**
	 * Constructor for the User Object
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param occupation
	 */
	public User(String firstName, String lastName, String age, String gender, String occupation)
	{
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}
	
	/**
	 * Constructor for the User Object, used for tests
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param occupation
	 */
	public User(Long id,String firstName, String lastName, String age, String gender, String occupation)
	{
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}
	
	/**
	 * Adds a rating to the 'ratings' List.
	 * @param r
	 */
	public void addRating(Rating r)
	{
		ratings.add(r);
	}
	
	public Long getId()
	{
		return id;
	}
	public List<Rating> getRatings()
	{
		return ratings;
	}
	
	/**
	 * Cleans up toString on the object.
	 */
	@Override
	public int hashCode()  
  {  
     return Objects.hashCode(this.lastName, this.firstName, this.age, this.gender,this.occupation);  
  } 
	
	/**
	 * Fixes problems when comparing two objects which are the same, but technically aren't due to identity issues.
	 */
	@Override
  public boolean equals(final Object obj)
  {
  	if(obj instanceof User)
  	{
  		final User other = (User) obj;
  		return Objects.equal(firstName, other.firstName)
  		&& Objects.equal(lastName,  other.lastName)
      && Objects.equal(age,     other.age)
      && Objects.equal(gender,  other.gender)
      && Objects.equal(occupation, other.occupation);
  	}
  	else { return false; }
  }
	
	/**
	 * ToString method which uses ' ToJsonString.Java ' to return a neater version of toString.
	 */
	@Override
  public String toString()
  {
    return new ToJsonString(getClass(), this).toString();

  }

}
