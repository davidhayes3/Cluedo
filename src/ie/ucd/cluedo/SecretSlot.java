/***************************************************************/
/* SecretSlot Class
/* 
/* Represents a slot for the secret passage way between some rooms
/***************************************************************/

package ie.ucd.cluedo;


public class SecretSlot extends Slot 
{

	// Attributes
	int roomNumber;
	SecretButton secretButton;
	
	
	// Constructor
	public SecretSlot(int row, int col, int roomNumber, SecretButton secretButton) 
	{
		super(row, col);
		
		this.roomNumber = roomNumber;
		this.secretButton = secretButton;
	}
	
	
	/* Public Methods */
	
	
	// getRoomNumber() method
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
	
	
	// getButton() method
	public SecretButton getButton()
	{
		return this.secretButton;
	}
	
}
