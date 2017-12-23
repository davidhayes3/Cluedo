/***************************************************************/
/* Slot Class
/* 
/* Abstract class representing a slot
/***************************************************************/

package ie.ucd.cluedo;


public abstract class Slot 
{
	
	// Attributes
	int row;
	int col;
	
	// Slot constructor
	public Slot(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	
	/* Public Methods */
	
	
	// getRow() method
	public int getRow()
	{
		return this.row;
	}
	
	
	// getCol() method
	public int getCol()
	{
		return this.col;
	}
	
	// Abstract method to get room number of slot
	public abstract int getRoomNumber();
	
	// Abstract method to get button of slot
	public abstract Button getButton();

}
