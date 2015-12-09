package controllers;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import edu.princeton.cs.introcs.In;
import models.Movie;
import models.User;
import models.Rating;
import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import java.util.Date;

public class Main {
	
	public RecommenderAPI recAPI;
	private ReadCSVFile csv;
	
	public Main() throws Exception
  {
    File datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);
    csv = new ReadCSVFile();

    recAPI = new RecommenderAPI(serializer);
    if (datastore.isFile())
    {
      recAPI.load();
    }
  }
	
	public static void main(String[] args) throws Exception
  {
    Main main = new Main();

    Shell shell = ShellFactory.createConsoleShell("Rec", "Welcome to the Movie Recommender App Console - ?help for instructions", main);
    shell.commandLoop();

    main.recAPI.store();
  }
	
	@Command(description="Create a new User")
  public void addUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName, 
                          @Param(name="age")      String age,     @Param(name="gender")  String gender,
                          @Param(name="occupation") 	String occupation)
  {
    recAPI.addUser(firstName, lastName, age, gender, occupation);
  }
	
	@Command(description="Delete a User")
  public void removeUser (@Param(name="id") Long id)
  {
    Optional<User> user = Optional.fromNullable(recAPI.getUserById(id));
    if (user.isPresent())
    {
      recAPI.deleteUser(user.get().id);
    }
  }
	
	@Command(description="Get all users details")
  public void listUsers()
  {
  	Collection<User> users = recAPI.getUsers();
  	System.out.println(users);
  }
	
	@Command(description="Store All Current Data In XML File")
	public void write() throws Exception
	{
		recAPI.store();
	}
	
	@Command(description="Loads All Current Data From XML File")
	public void load() throws Exception
	{
		recAPI.load();
	}
	
	@Command(description="adds users to database from csv file")
	public void addUsersFromCSV()
	{
		try {
	    recAPI.setUserIndex(csv.readUserFile());
	    readRatingsCSVFile();
    } catch (Exception e) {
	    e.printStackTrace();
    }
	}
	
	@Command(description="Loads In movies from the csv file")
	public void addMoviesFromCSV()
	{
		try {
	    recAPI.setMovieIndex(csv.readMovieFile());
    } catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
    }
	}
	
	@Command(description="Lists all movies currently in database")
	public void listMovies()
	{
		Collection<Movie> movies = recAPI.getMovies();
		System.out.println(movies);
	}
	
	@Command(description="adds a movie")
	public void addMovie(@Param(name="title") String title, @Param(name="year") String year, @Param(name="url") String url)
	{
		recAPI.addMovie(title,year,url);
	}
	
	@Command(description="Finds a movie and its details from id")
	public void findMovie(@Param(name="id") Long id)
	{
		recAPI.getMovieById(id);
	}
	
	@Command(description="get user ratings using id")
	public User getUserRatings(@Param(name="id")Long id)
	{
		return recAPI.getUserById(id);
	}
	
	@Command(description="add rating")
	public void addRating(@Param(name="userId")Long userId,@Param(name="movieId") Long movieId,@Param(name="rating") Long rating)
	{
		User user = recAPI.getUserById(userId);
		Rating r = new Rating(userId,movieId,rating);
		user.addRating(r);
	}
	
	@Command(description="get Movies reccommended for user reccomendations")
	public void getMoviesRec(@Param(name="userId")Long userId)
	{
		ArrayList<Movie> listOfMovies = new ArrayList<>();
		User user = recAPI.getUserById(userId);
		for(Movie movie : recAPI.getMovies())
		{
			for(Rating rating : user.ratings)
			{
				if(rating.getItemId().equals(movie.id))
				{
					break;
				}
				else
				{
					listOfMovies.add(movie);
				}
			}
		}
		
		for(Movie movie : listOfMovies)
		{
			
		}
	}
	
	
	private void readRatingsCSVFile() throws Exception
	{
		File ratingFile = new File("ratings5.dat");
	  In inMovies = new In(ratingFile);
	  //each field is separated(delimited) by a '|'
	  String delims = "[|]";
	  Map<Long,Movie> movies = new HashMap<>(); //Map For Read In Movies
	  while (!inMovies.isEmpty()) {
	      // get user and rating from data source
	      String userDetails = inMovies.readLine();
	      Rating rating = null;
	      // parse user details string
	      String[] userTokens = userDetails.split(delims);
	
	      // Rating is added to the appropriate User
	      if (userTokens.length == 4) {
	      	rating = new Rating(Long.parseLong(userTokens[0]),Long.parseLong(userTokens[1]),Long.parseLong(userTokens[2]),Long.parseLong(userTokens[3]));
	      	User user = recAPI.getUserById(Long.parseLong(userTokens[0]));
	        user.addRating(rating);
	        
	      }else {
	          throw new Exception("Invalid member length: "+userTokens.length);
	   } 
	  }
	  
	}
}
