package models;

public class Fixtures {
	
	public static User[] users =
	  {
	    new User ((long)11,"Homer","Simpson","30","M","Technician"),
	    new User ((long)22,"Marge","Simpson","32","F","HouseWife"),
	    new User ((long)33,"Lisa","Simpson","12","F","SchoolGirl"),
	    new User ((long)44,"Bart","Simpson","13","M","SchoolBoy")
	  };
	
	public static Movie[] movies =
		{
			new Movie ((long)112,"Jack","1995","www.film.com"),
			new Movie ((long)223,"Saw V","1998","www.sawV.com"),
			new Movie ((long)334,"Toy Story 3","2010","www.imdb/toystory.com"),
			new Movie ((long)445,"boom room","1997","www.imdb/boomroom.com")
		};
	
	public static Rating[] ratings =
		{
			new Rating((long)11,(long)223,3),
			new Rating((long)44,(long)334,1),
			new Rating((long)22,(long)334,-5),
			new Rating((long)22,(long)112,5),
			
		};

}
