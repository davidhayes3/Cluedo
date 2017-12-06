package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

public abstract class Pawn
{

	// Pawn attributes
	private PawnType pawnType;
	private Name pawnName;
	public Slot pawnPosition;
	
	// Pawn constructor
	public Pawn(int pawnIndex)
	{

		// Decide type of pawn based on index number of pawn
		if (pawnIndex < NUM_SUSPECTS)
		{
			this.pawnType = PawnType.SUSPECT;
		}
		else if (pawnIndex < NUM_PAWNS)
		{
			this.pawnType = PawnType.WEAPON;
		}
		
		this.pawnName = gameList.get(pawnIndex-1);
	}
	
	// Returns type of pawn, suspect or weapon
	public PawnType getType()
	{
		return this.pawnType;
	}
	
	// Returns name of pawn
	public Name getName()
	{
		return this.pawnName;
	}

}
