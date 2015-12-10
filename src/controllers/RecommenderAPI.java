package controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.google.common.base.Optional;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import utils.Serializer;





import utils.Serializer;
import models.Rating;
import models.User;
import models.Movie;

@SuppressWarnings("unused")
public class RecommenderAPI {

	private Map<Long, User>     userIndex       = new HashMap<>();
	private Map<Long, Movie>			movieIndex 			= new HashMap<>();
	private Serializer serializer;
	private ReadCSVFile csv;
	
	/**
	 * Blank Constructor for RecommenderAPI that throws exception
	 * @throws Exception
	 */
	public RecommenderAPI() throws Exception 
	{
	}
	
	/**
	 * Constructor for recommenderAPI that initializes the serializer.
	 * @param serializer
	 */
	public RecommenderAPI(Serializer serializer)
  {
  	this.serializer = serializer;
  }
	
	/**
	 * Creates a user object, then adds to the userIndex.
	 * @param fName
	 * @param lName
	 * @param age
	 * @param gender
	 * @param occupation
	 * @return
	 */
	public User addUser(String fName, String lName, String age, String gender, String occupation)
	{
		User user = new User(fName,lName,age,gender,occupation);
		userIndex.put(user.id,user);			//Adds to userIndex
		return user;		//returns user object (Allowed for the object to be printed onto console screen easier)
	}
	
	/**
	 * Creates a movie object, then adds to the movieIndex.
	 * @param title
	 * @param year
	 * @param url
	 * @return
	 */
	public Movie addMovie(String title, String year, String url)
	{
		Movie movie = new Movie ( title,year,url);
		movieIndex.put(movie.id, movie);			//Adds to movieIndex
		return movie;		//returns movie object (Allowed for the object to be printed onto console screen easier)
	}
	
	/**
	 * Allows the userIndex to be iniatilized and also added to by using another map.
	 * @param users
	 */
	public void setUserIndex(Map<Long,User> users)
	{
		userIndex.putAll(users); //Merges Maps So you don't lose Objects
	}
	
	/**
	 * Allows the movieIndex to be iniatilized and also added to by using another map.
	 * @param movies
	 */
	public void setMovieIndex(Map<Long,Movie> movies)
	{
		movieIndex.putAll(movies); //Same As Above
	}
	
	/**
	 * returns the userIndex collection.
	 * @return
	 */
	public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
	
	/**
	 * Deletes a user from the running instance of the app.
	 * @param id
	 */
	public  void deleteUser(Long id) 
  {
		User user = userIndex.remove(id);
  }
	
	/**
	 * returns the movieIndex collection.
	 * @return
	 */
	public Collection<Movie> getMovies ()
  {
    return movieIndex.values();
  }
	
	/**
	 * Finds a user by using the id variable associated with them
	 * @param id
	 * @return
	 */
	public User getUserById(Long id)
	{
		
		return userIndex.get(id);
    
	}
	
	/**
	 * Finds a movie by using the id variable associated with them
	 * @param id
	 * @return
	 */
	public Movie getMovieById(Long id)
	{
		return movieIndex.get(id);
	}
	
	/**
	 * Deletes a movie from the running system
	 */
	public  void deleteMovie(Long id) 
  {
  	movieIndex.remove(id);
  }
	
	/**
	 * Loads data stored in the XML File.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
  public void load() throws Exception
  {
		serializer.read();
		movieIndex			= (Map<Long, Movie>)		serializer.pop(); //Reversed Because of the stack
    userIndex       = (Map<Long, User>)     serializer.pop();
  }
	
	/**
	 * Writes data to the XML File.
	 * @throws Exception
	 */
  public void store() throws Exception
  {
    serializer.push(userIndex); //Opposite direction to load
    serializer.push(movieIndex);
    serializer.write(); 
  }
  
//  public void recommendedMovies(Long id)
//  {
//  	ArrayList<Movie> unSeenMovies = new ArrayList<Movie>();
//  	User user = getUserById(id);
//  	for(int i=0; i < movieIndex.size();i++)
//  	{
//  		int counter = 0;
//	  	for(Rating rating : user.getRatings())
//	  	{
//	  		if(i == rating.getItemId())
//	  		{
//	  			break;
//	  		}
//	  		else{counter++;}
//	  	}
//	  	if(counter ==user.getRatings().size())
//	  	{
//	  		unSeenMovies.add(getMovieById(id));
//	  	}
//  	}
//  	
//  	for(Movie movie : unSeenMovies)
//  	{
//  		ArrayList<Integer> averageMovieRating = new ArrayList<Integer>();
//	  	for(Long i=(long)0; i<userIndex.size();i++)
//	  	{
//	  		User u = getUserById(i);
//	  		for(Rating rating : u.getRatings())
//	  		{
//	  			if(rating.getItemId() == movie.id)
//	  			{
//	  				averageMovieRating.add(rating.getRating());
//	  			}
//	  		}
//	  		movie.averageRating = getAverageRating(averageMovieRating);
//	  	}
//  	}
//  	System.out.println(unSeenMovies);
//  	
//  	
//  }
//  private int getAverageRating(ArrayList<Integer> array)
//  {
//  	int i = array.size() , j = 0;
//  	
//  	for(int x=0; x<array.size();x++)
//  	{
//  		j += array.get(x);
//  	}
//  	
//  	return j;
//  }

	
	
	
}
