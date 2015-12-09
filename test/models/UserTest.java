package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.Fixtures.users;

public class UserTest {
	
	User homer = new User("Homer","Simpson","30","M","Technician");
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());
  }
	
	@Test
	public void testEquals()
	{
		User homer2 = new User ("Homer","Simpson","30","M","Technician"); 
    User bart   = new User ("Hugo","Simpson","13","M","Demon"); 

    assertSame(homer, homer);
    assertNotSame(homer, homer2);
    assertNotEquals(homer, bart);
	}
	
	@Test
  public void testToString()
  {
    assertEquals ("User{" + homer.id + ", Homer, Simpson, 30, M, Technician}", homer.toString());
  }
	
	@After
	public void tearDown() throws Exception {
	}

}
