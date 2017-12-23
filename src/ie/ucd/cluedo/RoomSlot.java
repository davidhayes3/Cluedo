/***************************************************************/
/* RoomSlot Class
/* 
/* Represents a room slot
/***************************************************************/

package ie.ucd.cluedo;

public class RoomSlot extends Slot 
{
	
	// Attributes
	int roomNumber;
	RoomButton roomButton;
	
	
	// Constructor
	public RoomSlot(int row, int col, int roomNumber, RoomButton roomButton) 
	{
		super(row, col);
		
		this.roomNumber = roomNumber;
		this.roomButton = roomButton;
	}
	
	
	/* Public Methods */
	
	
	// getRoomNumber() method
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
		
	
	// getButton() method
	public RoomButton getButton()
	{
		return this.roomButton;
	}
		
}
