package ie.ucd.cluedo;


public class SuspectPawn extends Pawn  
{
	Slot pawnPosition;
	public int playerX;
	public int playerY;
	public BoardButton suspectButton;
	// SuspectPawn constructor
	public SuspectPawn(int pawnIndex, Slot pawnPosition)
	{
		super(pawnIndex, pawnPosition);
		this.pawnPosition = pawnPosition;
	}
	
	// Returns slot where the suspect pawn is currently located
	public Slot getPosition()
	{
		return this.pawnPosition;
	}
	
	public void movePawn(BoardButton newPosition)
	{
		this.suspectButton = newPosition;
		this.playerX = newPosition.getXpos();
		this.playerY = newPosition.getYpos();
	}
	
	public int getX(){
		return this.playerX;
	}
	
	public int getY(){
		return this.playerY;
	}
	public BoardButton getSuspectButton(){
		return suspectButton;
		
	}
	// Changes the slot occupied by the suspect pawn
	public void movePosition(Slot newPosition)
	{
		this.pawnPosition = newPosition;
	}

}
