/***************************************************************/
/* Pawn Class
/* 
/* Represents a pawn on the board in the game
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;


public abstract class Pawn
{

	// Attributes
	int pawnIndex;
	private PawnType pawnType;
	private Name pawnName;
	public Slot pawnPosition;
	
	
	// Constructor
	public Pawn(int pawnIndex)
	{
		this.pawnIndex = pawnIndex;
		
		// Decide type of pawn based on index number of pawn
		if (pawnIndex < NUM_SUSPECTS)
		{
			this.pawnType = PawnType.SUSPECT;
		}
		else if (pawnIndex < NUM_PAWNS)
		{
			this.pawnType = PawnType.WEAPON;
		}
		
		this.pawnName = gameList.get(pawnIndex);
	}
	
	
	/* Public Methods */
	
	
	// getPawnIndex() method
	public int getPawnIndex()
	{
		return this.pawnIndex;
	}
	
	
	// getType() method
	public PawnType getType()
	{
		return this.pawnType;
	}
	
	
	// getName() method
	public Name getName()
	{
		return this.pawnName;
	}

}
