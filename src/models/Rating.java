package models;

import utils.ToJsonString;

import com.google.common.base.Objects;

public class Rating {

	private int rating;
	private Long userId;
	private Long itemId;
	private Long timeStamp;
	
	
	/**
	 * Constructor for the ratings object, without timestamp
	 * @param userId
	 * @param itemId
	 * @param rating
	 */
	public Rating(Long userId, Long itemId, int rating) 
	{
		this.userId = userId;
		this.itemId = itemId;
		this.rating = rating;
	}
	
	/**
	 * Constructor for the ratings object with timestamp (for the CSV file objects)
	 * @param userId
	 * @param itemId
	 * @param rating
	 * @param timeStamp
	 */
	public Rating(Long userId, Long itemId, int rating, Long timeStamp) 
	{
		this.userId = userId;
		this.itemId = itemId;
		this.rating = rating;
		this.timeStamp = timeStamp;
	}
	
	/**
	 * Returns the value of the rating.
	 * @return
	 */
	public int getRating()
	{
		return rating;
	}
	


	
	/**
	 * Returns the itemId
	 * @return
	 */
	public Long getItemId()
	{
		return itemId;
	}
	
	/**
	 * Cleans up toString on the object.
	 */
	@Override
	public int hashCode()  
  {  
     return Objects.hashCode(this.userId, this.itemId, this.rating, this.timeStamp);  
  } 
	
	/**
	 * Fixes problems when comparing two objects which are the same, but technically aren't due to identity issues.
	 */
	@Override
  public boolean equals(final Object obj)
  {
  	if(obj instanceof User)
  	{
  		final Rating other = (Rating) obj;
  		return Objects.equal(userId, other.userId)
  		&& Objects.equal(itemId,  other.itemId)
      && Objects.equal(rating,     other.rating)
      && Objects.equal(timeStamp,  other.timeStamp);
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
