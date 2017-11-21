package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

public class Room extends Slot
{
	
	// Attributes of room
	private int roomIndex;
	
	// Room constructor
	public Room(int x, int y, int roomIndex)
	{
		super(x, y);
		this.roomIndex = roomIndex;
	}
	
	// Returns name of room
	public Name getRoom()
	{
		return gameList.get(NUM_PAWNS + roomIndex - 1);
	}
}
