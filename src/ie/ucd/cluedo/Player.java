package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import static ie.ucd.cluedo.GameValues.*;

public class Player 
{

	// Attributes of player 
	private int playerNumber;
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private SuspectPawn playerPawn;
	private Notebook playerNotebook;

	// Player constructor
	// Arguments: player number, suspect pawn allocated to player, notebook assigned to player, cards assigned to player later
	public Player(int playerNumber, Notebook playerNotebook)
	{
		this.playerNumber = playerNumber;
		this.playerNotebook = playerNotebook;
	}
	
	// Returns player's number
	public int getPlayerNumber()
	{
		return this.playerNumber;
	}
	
	// Allocates a single card to a player
	public void giveCard(Card card)
	{
		this.playerCards.add(card);
	}
	
	// Returns the list of cards held by a player. Used to refute hypotheses
	public ArrayList<Card> getCards()
	{
		return this.playerCards;
	}
	
	// Assigns suspect pawn to player
	public void giveSuspectPawn(SuspectPawn playerPawn)
	{
		this.playerPawn = playerPawn;
	}
	
	// Returns suspect pawn assigned to player
	public SuspectPawn getSuspectPawn()
	{
		return this.playerPawn;
	}
	
	// Returns current occupied by the player's suspect pawn
	public Slot getPosition()
	{
		return this.playerPawn.getPosition();
	}
	
	// Simulates the rolling of two dies by a player and getting the sum of the dice scores. Returns a random number between 2 and 12.
	public int rollDies()
	{
		return ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
	}
	
	// Moves the player's suspect pawn to a different slot on the board
	public void movePawn(Slot newPosition)
	{
		playerPawn.movePosition(newPosition);
	}
	
	 
	// Moves the player's suspect pawn into a room 
	public void enterRoom()
	{
		// Knowledge of board required to implement
	}
	
	// Adds to contents of notebook based on events of game
	public void updateNotebook(String event)
	{
		this.playerNotebook.makeEntry(event);
	}
	
	// Returns the entire contents to date of the player's notebook
	public String inspectNotebook()
	{
		return this.playerNotebook.getContents();
	}
	
	// Allows player to make hypothesis of murder
	public void makeHypothesis()
	{
		// Coded in later release
	}
	
	// Allows player to make accusation of murder
	public void makeAccusation()
	{
		// Coded in later release
	}
	
}
