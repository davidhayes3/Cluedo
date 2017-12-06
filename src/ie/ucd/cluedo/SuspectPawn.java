package ie.ucd.cluedo;

import java.awt.Color;

public class SuspectPawn extends Pawn  
{
	Slot pawnPosition;
	public BoardButton suspectButton;
	public Color pawnColour;
	
	// SuspectPawn constructor
	public SuspectPawn(int pawnIndex, Color pawnColour)
	{
		super(pawnIndex);
		this.pawnColour = pawnColour;
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
	
	public Color getColor()
	{
		return this.pawnColour;	
	}
}
