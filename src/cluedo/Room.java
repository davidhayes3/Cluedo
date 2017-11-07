package cluedo;

import static cluedo.GameValues.*;

public class Room extends Slot
{
	
	// Attributes of room
	public Name roomName;
	
	// Room constructor
	public Room(int x, int y, Name roomName)
	{
		super(x, y);
		this.roomName = roomName;
	}
	
	// Returns name of room
	public Name getRoom()
	{
		return this.roomName;
	}
}
