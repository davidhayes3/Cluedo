package ie.ucd.cluedo;

public class SecretSlot extends Slot 
{
	SecretButton secretButton;
	
	public SecretSlot(int x, int y, SecretButton secretButton) 
	{
		super(x, y);
		
		this.secretButton = secretButton;
	}
	
	public SecretButton getButton()
	{
		return this.secretButton;
	}
	
}
