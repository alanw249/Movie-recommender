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
	public List<Boolean> genre = new ArrayList<>();
	public Movie()
	{
		
	}
	
	public Movie(String title, String releaseDate, String imdbUrl)
	{
		counter++;
		if (id==0)
		{
			this.id = counter;
		}
		
		this.title = title;
		this.releaseDate = releaseDate;
		this.imdbUrl = imdbUrl;
	}
	
	
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
	
	@Override
	public int hashCode()  
  {  
     return Objects.hashCode(this.title, this.releaseDate, this.imdbUrl);  
  } 
	
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
	
	@Override
  public String toString()
  {
    return new ToJsonString(getClass(), this).toString();

  }
	
}
