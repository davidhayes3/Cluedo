/***************************************************************/
/* BoardSlot Class
/* 
/* Represents a corridor slot
/***************************************************************/

package ie.ucd.cluedo;

public class BoardSlot extends Slot
{
	
	// Attributes
	BoardButton boardButton;
	
	
	// Constructor
	public BoardSlot(int row, int col, BoardButton boardButton)
	{
		super(row, col);
		
		this.boardButton = boardButton;
	}
	
	
	/* Public Methods */
	
	
	// getButton() method
	public BoardButton getButton()
	{
		return this.boardButton;
	}

	// Overrides abstract method, room number is 0 to make for simpler logic later
	@Override
	public int getRoomNumber() 
	{
		return 0;
	}
}
