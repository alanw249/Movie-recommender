package models;


import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import utils.ToJsonString;

import com.google.common.base.Objects;


@SuppressWarnings("unused")
public class Movie {

	static Long counter = (long) 01;
	public Long id = (long) 0;
	public String title;
	public String releaseDate; //TODO try add date api
	public String imdbUrl;
	public List<Boolean> genre = new ArrayList<>(); //|| True = Is that Genre || False = Is not that Genre ||
	public int averageRating;
	
	/**
	 * Contructor for the movie object
	 * @param title
	 * @param releaseDate
	 * @param imdbUrl
	 */
	public Movie(String title, String releaseDate, String imdbUrl)
	{
		counter++;
		this.id = counter;
		this.title = title;
		this.releaseDate = releaseDate;
		this.imdbUrl = imdbUrl;
	}
	
	/**
	 * Contructor for the movie object used for tests
	 * 
	 * @param title
	 * @param releaseDate
	 * @param imdbUrl
	 */
	public Movie(Long id,String title, String releaseDate, String imdbUrl)
	{
		counter++;
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.imdbUrl = imdbUrl;
	}
	
	/**
	 * converts 1&0's of read in genre values to boolean
	 * @param g
	 */
	public void genreCheck(int g)
	{
		if(g == 1)
		{
			genre.add(true);
		}
		else
		{
			genre.add(false);
		}
		
	}
	
	/**
	 * Cleans up toString on the object.
	 */
	@Override
	public int hashCode()  
  {  
     return Objects.hashCode(this.title, this.releaseDate, this.imdbUrl);  
  } 
	
	/**
	 * Fixes problems when comparing two objects which are the same, but technically aren't due to identity issues.
	 */
	@Override
  public boolean equals(final Object obj)
  {
  	if(obj instanceof User)
  	{
  		final Movie other = (Movie) obj;
  		return Objects.equal(title, other.title)
  		&& Objects.equal(releaseDate,  other.releaseDate)
      && Objects.equal(imdbUrl,     other.imdbUrl);
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
