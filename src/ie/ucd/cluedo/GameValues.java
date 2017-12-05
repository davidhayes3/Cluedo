package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public final class GameValues 
{
	
	// Enumeration type for cards
	enum CardType
	{
		SUSPECT, WEAPON, ROOM;
	}

	// Enumeration type for pawns
	enum PawnType
	{
		SUSPECT, WEAPON;
	}

	// Enumeration type for names of suspects, weapons and rooms
	enum Name
	{
		MISS_SCARLET, PROFESSOR_PLUM, MRS_PEACOCK, REVEREND_MR_GREEN, COLONEL_MUSTARD, MRS_WHITE,
		CANDLE_STICK, KNIFE, LEAD_PIPE, REVOLVER, ROPE, POISON,
		KITCHEN, BALLROOM, CONSERVATORY, DINING_ROOM, LOUNGE, HALL, STUDY, BILLIARD_ROOM, LIBRARY;
	}
	
	// List of constants needed for logic
	public static final int MIN_NUM_PLAYERS = 2;
	public static final int MAX_NUM_PLAYERS = 6;		
	public static final int MIN_DIES_SCORE = 2;
	public static final int MAX_DIES_SCORE = 12;
	public static final int NUM_SUSPECTS = 6;
	public static final int NUM_WEAPONS = 6;
	public static final int NUM_ROOMS = 9;
	public static final int NUM_PAWNS = 12;
	public static final int NUM_CARDS_IN_DECK = 21;
	public static final int NUM_MURDER_CARDS = 3;
	public static final int NUM_CARDS_IN_PLAY = NUM_CARDS_IN_DECK - NUM_MURDER_CARDS;
	
	// List of constants needed for board
	public static final int BOARD_WIDTH = 24;
	public static final int BOARD_HEIGHT = 24;
	public static final int BUTTON_PIXEL_WIDTH = 21;
	public static final int BUTTON_PIXEL_HEIGHT = 21;
	
	// List of constants needed for button press type;
	public static final int BOARD_BUTTON_PRESS = 1;
	public static final int DIES_BUTTON_PRESS = 2;
	public static final int END_TURN_BUTTON_PRESS = 3;
	public static final int NOTEBOOK_BUTTON_PRESS = 4; 
	public static final int DOOR_BUTTON_PRESS = 5; 
	
	// Hash map for button colors
	

	
	// List of all possible suspects, weapons and rooms 
	public static final ArrayList<Name> gameList = new ArrayList<Name>(EnumSet.allOf(Name.class));
	
	// Select the murder details at random
	public static final int murderSuspectIndex = ThreadLocalRandom.current().nextInt(0, NUM_SUSPECTS);
	public static final int murderWeaponIndex = ThreadLocalRandom.current().nextInt(NUM_SUSPECTS, NUM_SUSPECTS + NUM_WEAPONS);
	public static final int murderRoomIndex = ThreadLocalRandom.current().nextInt(NUM_SUSPECTS + NUM_WEAPONS, NUM_SUSPECTS + NUM_WEAPONS + NUM_ROOMS);
	public static final Name murderSuspect = gameList.get(murderSuspectIndex);
	public static final Name murderWeapon = gameList.get(murderWeaponIndex);
	public static final Name murderRoom = gameList.get(murderRoomIndex);
	
}
