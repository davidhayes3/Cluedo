package ie.ucd.cluedo;

public class Slot 
{
	
	// Slot attributes
	public int x;
	public int y;
	
	// Slot constructor
	public Slot(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	// Returns x co-ordinate of slot
	public int getXPosition()
	{
		return this.x;
	}
	
	// Returns y co-ordinate of slot
	public int getYPosition()
	{
		return this.y;
	}
	
}
