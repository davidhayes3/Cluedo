package ie.ucd.cluedo;

public class DoorSlot extends Slot
{

	int roomNumber;
	DoorButton doorButton;
	
	public DoorSlot(int x, int y, int roomNumber, DoorButton doorButton) 
	{
		super(x, y);
		
		this.roomNumber = roomNumber;
		this.doorButton = doorButton;
	}	
	
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
	
	public DoorButton getButton()
	{
		return this.doorButton;
	}
	
}
