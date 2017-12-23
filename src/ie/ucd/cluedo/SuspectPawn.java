package ie.ucd.cluedo;

import java.awt.Color;

public class SuspectPawn extends Pawn  
{
	public Slot pawnPosition;
	public BoardButton suspectButton;
	public Color pawnColor;
	int pawnIndex;
	
	// SuspectPawn constructor
	public SuspectPawn(int pawnIndex, Color pawnColor)
	{
		super(pawnIndex);
		this.pawnIndex = pawnIndex;
		this.pawnColor = pawnColor;
	}
	
	// Returns slot where the suspect pawn is currently located
	public Slot getPosition()
	{
		return this.pawnPosition;
	}
	
	// Changes the slot occupied by the suspect pawn
	public void movePosition(Slot newPosition)
	{
		if(this.pawnPosition != null)
		{
			this.pawnPosition.getButton().resetDefault();
		}
		
		this.pawnPosition = newPosition;
		this.pawnPosition.getButton().changeColor(this.pawnColor);
	}
	
	public Color getColor()
	{
		return this.pawnColor;	
	}
	
	public int getPawnIndex()
	{
		return this.pawnIndex;
	}
	
}
