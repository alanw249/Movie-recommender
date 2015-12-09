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
import models.User;
import models.Movie;

@SuppressWarnings("unused")
public class RecommenderAPI {

	private Map<Long, User>     userIndex       = new HashMap<>();
	private Map<Long, Movie>			movieIndex 			= new HashMap<>();
	private Serializer serializer;
	private ReadCSVFile csv;
	
	
	public RecommenderAPI() throws Exception
	{
		
	}
	
	public RecommenderAPI(Serializer serializer)
  {
  	this.serializer = serializer;
  }
	
	public User addUser(String fName, String lName, String age, String gender, String occupation)
	{
		User user = new User(fName,lName,age,gender,occupation);
		userIndex.put(user.id,user);
		return user;
	}
	
	public Movie addMovie(String title, String year, String url)
	{
		Movie movie = new Movie ( title,year,url);
		movieIndex.put(movie.id, movie);
		return movie;
	}
	
	public void setUserIndex(Map<Long,User> users)
	{
		userIndex.putAll(users); //Merges Maps So you don't lose Objects
	}
	
	public void setMovieIndex(Map<Long,Movie> movies)
	{
		movieIndex.putAll(movies); //Same As Above
	}
	
	public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
	
	public  void deleteUser(Long id) 
  {
		User user = userIndex.remove(id);
  }
	
	public Collection<Movie> getMovies ()
  {
    return movieIndex.values();
  }
	
	public User getUserById(Long id)
	{
		return userIndex.get(id);
	}
	
	public Movie getMovieById(Long id)
	{
		return movieIndex.get(id);
	}
	
	public  void deleteMovie() 
  {
  	movieIndex.clear();
  }
	
	@SuppressWarnings("unchecked")
  public void load() throws Exception
  {
		serializer.read();
		movieIndex			= (Map<Long, Movie>)		serializer.pop(); //Reversed Because of the stack
    userIndex       = (Map<Long, User>)     serializer.pop();
  }

  public void store() throws Exception
  {
    serializer.push(userIndex); //Opposite direction to load
    serializer.push(movieIndex);
    serializer.write(); 
  }

	
	
	
}
