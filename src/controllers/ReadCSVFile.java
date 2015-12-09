package controllers;


import edu.princeton.cs.introcs.In;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import models.Movie;
import models.User;

@SuppressWarnings("unused")
public class ReadCSVFile {

	
	public static void main(String args[]) throws Exception{
	ReadCSVFile csv = new ReadCSVFile();
	csv.readMovieFile();
	}
	public Map<Long,User> readUserFile() throws Exception 
	{
	  File usersFile = new File("users5.dat");
	  In inUsers = new In(usersFile);
	  //each field is separated(delimited) by a '|'
	  String delims = "[|]";
	   Map<Long,User> users = new HashMap<>(); //Map For Read In Users
	  while (!inUsers.isEmpty()) {
	      // get user and rating from data source
	      String userDetails = inUsers.readLine();
	
	      // parse user details string
	      String[] userTokens = userDetails.split(delims);
	      User user = null;
	      // output user data to console.
	    	if (userTokens.length == 7) {
          user = new User(userTokens[2],userTokens[3],userTokens[4],userTokens[5],userTokens[6]);
          users.put(user.id,user);
	      }else
	      {
	          throw new Exception("Invalid member length: "+userTokens.length);
	      }
  		}
	  
	  System.out.println(users);
	  return users;
	}
	
	
	public Map<Long,Movie> readMovieFile() throws Exception
	{
		File movieFile = new File("items5.dat");
	  In inMovies = new In(movieFile);
	  //each field is separated(delimited) by a '|'
	  String delims = "[|]";
	  Map<Long,Movie> movies = new HashMap<>(); //Map For Read In Movies
	  while (!inMovies.isEmpty()) {
	      // get user and rating from data source
	      String userDetails = inMovies.readLine();
	      Movie movie = null;
	      // parse user details string
	      String[] userTokens = userDetails.split(delims);
	
	      // output user data to console.
	      if (userTokens.length == 23) {
	      	movie = new Movie(userTokens[1],userTokens[2],userTokens[3]);
	      	for(int i=4;i<21;i++)
	      	{
		      	movie.genreCheck(Integer.parseInt(userTokens[i])); //Passes Through genre Tokens and converts to boolean values
		      	movies.put(movie.id,movie);
	      	
	      	}
	      }else
	      
	          throw new Exception("Invalid member length: "+userTokens.length);
	      }
	  System.out.println(movies);
	  return movies;
	}

}