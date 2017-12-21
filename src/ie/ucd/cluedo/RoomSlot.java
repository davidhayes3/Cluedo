package ie.ucd.cluedo;

public class RoomSlot extends Slot 
{
	
	int roomNumber;
	RoomButton roomButton;
	
	public RoomSlot(int x, int y, int roomNumber, RoomButton roomButton) 
	{
		super(x, y);
		
		this.roomNumber = roomNumber;
		this.roomButton = roomButton;
	}
	
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
		
	public RoomButton getButton()
	{
		return this.roomButton;
	}
		
}
