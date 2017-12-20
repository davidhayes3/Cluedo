package ie.ucd.cluedo;

import java.util.ArrayList;

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
	
	// Returns notebook assigned to player
	public Notebook getNotebook()
	{
		return this.playerNotebook;
	}
}
