/***************************************************************/
/* DoorSlot Class
/* 
/* Represents a slot for the door of a room
/***************************************************************/

package ie.ucd.cluedo;

public class DoorSlot extends Slot
{

	// Attributes
	int roomNumber;
	DoorButton doorButton;
	
	
	// Constructor
	public DoorSlot(int row, int col, int roomNumber, DoorButton doorButton) 
	{
		super(row, col);
		
		this.roomNumber = roomNumber;
		this.doorButton = doorButton;
	}	
	
	
	/* Public Methods */
	
	
	// getRoomNumber() Method
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
	
	
	// getButton() Method
	public DoorButton getButton()
	{
		return this.doorButton;
	}
	
}
