package ie.ucd.cluedo;

public abstract class Slot 
{
	// Slot attributes
	int x;
	int y;
	
	// Slot constructor
	public Slot(int row, int col)
	{
		this.x = col;
		this.y = row;
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
	
	public abstract Button getButton();
	
}
