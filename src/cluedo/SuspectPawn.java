package cluedo;

public class SuspectPawn extends Pawn 
{
	
	// SuspectPawn constructor
	public SuspectPawn(int pawnIndex, Slot startingSlot)
	{
		super(pawnIndex, startingSlot);
	}
	
	// Returns slot where the suspect pawn is currently located
	public Slot getPosition()
	{
		return this.pawnPosition;
	}
	
	// Changes the slot occupied by the suspect pawn
	public void movePosition(Slot newPosition)
	{
		// Knowledge of board needed to implement
	}
	
	// Checks if the slot occupied the suspect pawn is in a specific room
	public void inRoom() 
	{
		// Knowledge of board needed to implement
	}
	
}
