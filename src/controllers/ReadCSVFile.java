package controllers;


import edu.princeton.cs.introcs.In;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import models.Movie;
import models.User;

@SuppressWarnings("unused")
public class ReadCSVFile {

	/**
	 * Used to read in user Objects from a CSV File.
	 * @return
	 * @throws Exception
	 */
	public Map<Long,User> readUserFile() throws Exception 
	{ 
	   File usersFile = new File("users5.dat");
	   In inUsers = new In(usersFile);
	  
	   //each field is separated(delimited) by a '|'
	   String delims = "[|]";
	   Map<Long,User> users = new HashMap<>(); //Map For Users already read in
	   
	   while (!inUsers.isEmpty()) {
	  	 
	      // get user and rating from data source
	      String userDetails = inUsers.readLine();
	
	      // parse user details string
	      String[] userTokens = userDetails.split(delims);
	      User user = null; //Initializes User Object
	      
	      // Creates user object
	    	if (userTokens.length == 7) {
          user = new User(userTokens[2],userTokens[3],userTokens[4],userTokens[5],userTokens[6]);
          users.put(user.id,user); //Adds user to map
	      }else
	      {
	          throw new Exception("Invalid member length: "+userTokens.length);
	      }
  		}
	  
	  System.out.println(users); //Prints users.
	  return users;
	}
	
	/**
	 * used to read in Movie Objects from a CSV File
	 * @return
	 * @throws Exception
	 */
	public Map<Long,Movie> readMovieFile() throws Exception
	{
		File movieFile = new File("items5.dat");
	  In inMovies = new In(movieFile);
	  
	  //each field is separated(delimited) by a '|'
	  String delims = "[|]";
	  Map<Long,Movie> movies = new HashMap<>(); //Map For Movies already read in and created
	  
	  while (!inMovies.isEmpty()) {
	  	
	      // get user and rating from data source
	      String movieDetails = inMovies.readLine();
	      
	      
	      // parse user details string
	      String[] movieTokens = movieDetails.split(delims);
	      Movie movie = null;		//Initializes Movie Object
	      
	      // Creates movie objects
	      if (movieTokens.length == 23) {
	      	movie = new Movie(movieTokens[1],movieTokens[2],movieTokens[3]);
	      	for(int i=4;i<21;i++)
	      	{
		      	movie.genreCheck(Integer.parseInt(movieTokens[i])); //Passes Through genre Tokens and converts to boolean values
		      	movies.put(movie.id,movie);
	      	
	      	}
	      }else
	      
	          throw new Exception("Invalid member length: "+movieTokens.length);
	      }
	  System.out.println(movies);
	  return movies;
	}

}