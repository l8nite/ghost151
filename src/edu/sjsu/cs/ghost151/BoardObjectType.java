/**
 * 
 */
package edu.sjsu.cs.ghost151;

/**
 * @author shaung
 *
 */
public enum BoardObjectType {
	Empty(" "),
	Target("@"),
	Wall("+"),
	Ghost("&");
	
	private final String representation;
	
	private BoardObjectType(String representation)
	{
		this.representation = representation;
	}
	
	public String toString()
	{
		return representation;
	}
}
