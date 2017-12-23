/***************************************************************/
/* Player Class
/* 
/* Represents a player of the Cluedo game
/***************************************************************/

package ie.ucd.cluedo;

import java.util.ArrayList;

public class Player 
{

	// Attributes 
	private int playerNumber;
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private SuspectPawn playerPawn;
	private Notebook playerNotebook;
	public PlayerHand playerHand;
	
	
	// Constructor, playerNumber and playerNotebook initialized
	public Player(int playerNumber, Notebook playerNotebook)
	{
		this.playerNumber = playerNumber;
		this.playerNotebook = playerNotebook;
	}
	
	
	/* Public Methods */
	
	
	// getPlayerNumber() method
	public int getPlayerNumber()
	{
		return this.playerNumber;
	}
	
	
	// giveCard() method, allocates a single card to a player
	public void giveCard(Card card)
	{
		this.playerCards.add(card);
	}
	
	
	// getCards() method
	public ArrayList<Card> getCards()
	{
		return this.playerCards;
	}
	
	
	// giveSuspectPawn() method
	public void giveSuspectPawn(SuspectPawn playerPawn)
	{
		this.playerPawn = playerPawn;
	}
	
	
	// getSuspectPawn() method
	public SuspectPawn getSuspectPawn()
	{
		return this.playerPawn;
	}
	
	
	// getNotebook() method
	public Notebook getNotebook()
	{
		return this.playerNotebook;
	}
	
	
	// giveHand() method
	public void giveHand(PlayerHand playerHand)
	{	
		this.playerHand = playerHand;
	}
	
	
	// getPlayerHand() method
	public PlayerHand getHand()
	{
		return this.playerHand;
	}
}
