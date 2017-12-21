package ie.ucd.cluedo;

public class BoardSlot extends Slot
{
	BoardButton boardButton;
	
	public BoardSlot(int x, int y, BoardButton boardButton)
	{
		super(x, y);
		
		this.boardButton = boardButton;
	}
	
	public BoardButton getButton()
	{
		return this.boardButton;
	}

	@Override
	public int getRoomNumber() 
	{
		return 0;
	}
}
