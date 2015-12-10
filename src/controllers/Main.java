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

/**
 * 
 * @author alanw_000
 * @version 1.0.0
 * Movie Recommender application that reads in CSV Files
 */
public class Main {
	
	public RecommenderAPI recAPI;
	private ReadCSVFile csv;
	
	/**
	 * Loads the XML File 'datastore.xml' and initializes the Serializer.
	 * @throws Exception
	 */
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
	
	/**
	 * Initialisises the command line interface used in the app.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
  {
    Main main = new Main();

    Shell shell = ShellFactory.createConsoleShell("Rec", "Welcome to the Movie Recommender App Console - ?help for instructions", main);
    shell.commandLoop();

    main.recAPI.store(); 
  }
	
	/**
	 * Used as a command in the shell app. to create users.
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param occupation
	 */
	@Command(description="Create a new User") //@Command tag is used to recognize the method as a shell command
  public void addUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName, 
                          @Param(name="age")      String age,     @Param(name="gender")  String gender,
                          @Param(name="occupation") 	String occupation)
  {
    recAPI.addUser(firstName, lastName, age, gender, occupation);
  }
	
	/**
	 * Used as a command in the shell app. to delete users
	 * @param id
	 */
	@Command(description="Delete a User")  //@Command tag is used to recognize the method as a shell command
  public void removeUser (@Param(name="id") Long id)
  {
    Optional<User> user = Optional.fromNullable(recAPI.getUserById(id));
    if (user.isPresent()) //Checking if the user exists
    {
      recAPI.deleteUser(user.get().id); //If the user does exist. delete 
    }
  }
	
	/**
	 * Used as a command in the shell app. To list all users currently loaded/present in the database.
	 */
	@Command(description="Get all users details")  //@Command tag is used to recognize the method as a shell command
  public void listUsers()
  {
  	Collection<User> users = recAPI.getUsers(); //Creates a copy object of 'users' from recAPI
  	System.out.println(users); //Prints 'users' to the console
  }
	
	/**
	 * Used as a command in the shell app. It writes currently stored data to the xml file
	 * @throws Exception
	 */
	@Command(description="Store All Current Data In XML File")  //@Command tag is used to recognize the method as a shell command
	public void write() throws Exception
	{
		recAPI.store(); //Uses the store command from recAPI to write to the XML file.
	}
	
	/**
	 * Used as a command in the shell app. It loads data stored in the XML file to the app.
	 * @throws Exception
	 */
	@Command(description="Loads All Current Data From XML File")  //@Command tag is used to recognize the method as a shell command
	public void load() throws Exception
	{
		recAPI.load(); //Uses the load command from recAPI to load data from the XML file.
	}
	
	/**
	 * Used as a command in the shell app. It adds the users from the csv file to currently loaded members
	 */
	@Command(description="adds users to database from csv file")  //@Command tag is used to recognize the method as a shell command
	public void addUsersFromCSV()
	{
		try {
	    recAPI.setUserIndex(csv.readUserFile()); //The Current index of users in 'userIndex' (Found in recAPI), is merged with the users loaded from CSV file.
	    readRatingsCSVFile();	//Reads the CSV file containing ratings (Ratings are added to user internally in said method)
    } catch (Exception e) {
	    e.printStackTrace();   //Auto-generated try-catch to deal with exception thrown from 'readUserFile'.
    }
	}
	/**
	 * Used as a command in the shell app. It loads Movie objects from a CSV file
	 */
	@Command(description="Loads In movies from the csv file")  //@Command tag is used to recognize the method as a shell command
	public void addMoviesFromCSV()
	{
		try {
	    recAPI.setMovieIndex(csv.readMovieFile()); //The Current index of movies in 'movieIndex' is merged with the movies loaded from CSV file.
    } catch (Exception e) {
	    e.printStackTrace(); //Auto-generated try-catch to deal with exception thrown from 'readMovieFile'.
    }
	}
	
	/**
	 *  Used as a command in the shell app. It lists all movies currently loaded/present, into the app.
	 */
	@Command(description="Lists all movies currently in database")  //@Command tag is used to recognize the method as a shell command
	public void listMovies()
	{
		Collection<Movie> movies = recAPI.getMovies();  //Creates a copy of the 'movies' collection in recAPI.
		System.out.println(movies);		//Prints all movies found to the console screen.
	}
	
	/**
	 * Used as a command in the shell app. It creates a new movie object and adds it to the running list of movies
	 * @param title
	 * @param year
	 * @param url
	 */
	@Command(description="adds a movie")  //@Command tag is used to recognize the method as a shell command
	public void addMovie(@Param(name="title") String title, @Param(name="year") String year, @Param(name="url") String url)  
	{
		recAPI.addMovie(title,year,url); //Creates a new movie object (Added to 'movies' collection in 'addMovie' method).
	}
	
	/**
	 * Used as a command in the shell app. Finds a movie using id in the currently loaded/present movies in the app.
	 * @param id
	 */
	@Command(description="Finds a movie and its details from id")  //@Command tag is used to recognize the method as a shell command
	public void findMovie(@Param(name="id") Long id)
	{
		recAPI.getMovieById(id); //Finds a movie by using the id associated with it
	}
	
	/**
	 * Used as a command in the shell app. Finds a user using id, in the currently loaded/present users in the app.
	 * @param id
	 */
	@Command(description="get user ratings using id")  //@Command tag is used to recognize the method as a shell command
	public User getUserRatings(@Param(name="id")Long id)
	{
		return recAPI.getUserById(id);  //Finds a user by using the id associated with it
	}

	/**
	 * Used as a command in the shell app. Adds a rating object to a user object.
	 * @param userId
	 * @param movieId
	 * @param rating
	 */
	@Command(description="add rating")  //@Command tag is used to recognize the method as a shell command
	public void addRating(@Param(name="userId")Long userId,@Param(name="movieId") Long movieId,@Param(name="rating") int rating) throws Exception
	{
		Optional<User> user = Optional.fromNullable(recAPI.getUserById(userId));
		Optional<Movie> movie = Optional.fromNullable(recAPI.getMovieById(movieId));
    if (!user.isPresent() || !movie.isPresent()) //Checking if the user exists
    {
       throw new Exception("ERROR. Please make sure the UserId & MovieId are correct");
    }
    else{
				if(rating == -5 || rating == -3 || rating == -1 || rating == 1 || rating == 3 || rating == 5)
				{
					User user1 = recAPI.getUserById(userId);  	//Finds a user by the id associated with them
					Rating r = new Rating(userId,movieId,rating); 	//Creates a new rating object using parameters.
					user1.addRating(r);		//Adds rating to User object.
				}
				else{
					throw new Exception("rating must have a value of (-5,-3,-1,1,3,5), you Input: " + rating);
				}
    }
		
	}
	
	@Command(description="get Movies reccommended for user reccomendations")  //@Command tag is used to recognize the method as a shell command
	public void getMoviesRec(@Param(name="userId")Long userId)
	{
		//TODO
	}
	
	@Command(description="get top 10 movies")
	public void getTop10Movies()
	{
		//TODO
	}
	/**
	 * Used to read in a Rating object from a given CSV File.
	 * @throws Exception
	 */
	private void readRatingsCSVFile() throws Exception
	{
		
		File ratingFile = new File("ratings5.dat");		
	  In inMovies = new In(ratingFile);
	  
	  //each field is separated(delimited) by a '|'
	  String delims = "[|]";
	  
	  Map<Long,Movie> movies = new HashMap<>(); //Map For Read In Movies
	  while (!inMovies.isEmpty()) {
	  	
	      // get user and rating from data source
	      String ratingDetails = inMovies.readLine();
	      Rating rating = null;
	      
	      // parse user details string
	      String[] ratingTokens = ratingDetails.split(delims);
	
	      // Rating is added to the appropriate User
	      if (ratingTokens.length == 4) {														//ParseLong is used to comply with constructor of the rating object.
	      	rating = new Rating(Long.parseLong(ratingTokens[0]),Long.parseLong(ratingTokens[1]),Integer.parseInt(ratingTokens[2]),Long.parseLong(ratingTokens[3]));
	      	User user = recAPI.getUserById(Long.parseLong(ratingTokens[0]));
	        user.addRating(rating);
	        
	      }else {
	          throw new Exception("Invalid member length: "+ratingTokens.length);  //Throws exception if the tokens length is not correct
	   } 
	  }
	  
	}
}
