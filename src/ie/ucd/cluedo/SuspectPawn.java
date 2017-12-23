/***************************************************************/
/* SuspectPawn Class
/* 
/***************************************************************/

package ie.ucd.cluedo;

import java.awt.Color;


public class SuspectPawn extends Pawn  
{
	
	// Attributes
	public Slot pawnPosition;
	public BoardButton suspectButton;
	public Color pawnColor;
	
	
	// Constructor
	public SuspectPawn(int pawnIndex, Color pawnColor)
	{
		super(pawnIndex);
		this.pawnColor = pawnColor;
	}
	
	
	/* Public Methods */
	
	
	// getPosition() method
	public Slot getPosition()
	{
		return this.pawnPosition;
	}
	
	
	// movePosition() method
	public void movePosition(Slot newPosition)
	{
		// Reset default of old button unless slot is pawn is currently not allocated to a slot
		if(this.pawnPosition != null)
		{
			this.pawnPosition.getButton().resetDefault();
		}
		
		// Move to new slot and update color
		this.pawnPosition = newPosition;
		this.pawnPosition.getButton().changeColor(this.pawnColor);
	}
	
	
	// getColor() method
	public Color getColor()
	{
		return this.pawnColor;	
	}
	
}
