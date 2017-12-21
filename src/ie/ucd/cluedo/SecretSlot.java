package ie.ucd.cluedo;

public class SecretSlot extends Slot 
{
	int roomNumber;
	SecretButton secretButton;
	
	public SecretSlot(int x, int y, int roomNumber, SecretButton secretButton) 
	{
		super(x, y);
		
		this.roomNumber = roomNumber;
		this.secretButton = secretButton;
	}
	
	public int getRoomNumber()
	{
		return this.roomNumber;
	}
	
	public SecretButton getButton()
	{
		return this.secretButton;
	}
	
}
