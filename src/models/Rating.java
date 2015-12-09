package models;

import utils.ToJsonString;

import com.google.common.base.Objects;

public class Rating {

	private Long rating;
	private Long userId;
	private Long itemId;
	private Long timeStamp;
	
	public Rating(Long userId, Long itemId, Long rating) 
	{
		this.userId = userId;
		this.itemId = itemId;
		this.rating = rating;
	}
	
	public Rating(Long userId, Long itemId, Long rating, Long timeStamp) 
	{
		this.userId = userId;
		this.itemId = itemId;
		this.rating = rating;
		this.timeStamp = timeStamp;
	}
	
	public Long getRating()
	{
		return rating;
	}
	
	public Long getItemId()
	{
		return itemId;
	}
	
	@Override
	public int hashCode()  
  {  
     return Objects.hashCode(this.userId, this.itemId, this.rating, this.timeStamp);  
  } 
	
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
	
	@Override
  public String toString()
  {
    return new ToJsonString(getClass(), this).toString();

  }
	
}
