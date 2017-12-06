package ie.ucd.cluedo;

public class DoorSlot extends Slot
{

	DoorButton doorButton;
	
	public DoorSlot(int x, int y, DoorButton doorButton) 
	{
		super(x, y);
		
		this.doorButton = doorButton;
	}	
	
	public DoorButton getButton()
	{
		return this.doorButton;
	}
	
}
