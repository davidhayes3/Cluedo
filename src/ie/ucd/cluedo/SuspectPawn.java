package ie.ucd.cluedo;


public class SuspectPawn extends Pawn  
{
	Slot pawnPosition;
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
	
	// Changes the slot occupied by the suspect pawn
	public void movePosition(Slot newPosition)
	{
		this.pawnPosition = newPosition;
	}

}
