/***************************************************************/
/* Card Class
/* 
/* Represent a card held by a player
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;


public class Card 
{	
	
	// Attributes
	private CardType cardType;
	private Name cardName;
	public int cardIndex;
	
	
	// Constructor
	public Card(int cardIndex)
	{
		this.cardIndex = cardIndex;
		
		// Decide type of card based on index number of card
		if (cardIndex < NUM_SUSPECTS)
		{
			this.cardType = CardType.SUSPECT;
		}
		else if (cardIndex < NUM_PAWNS)
		{
			this.cardType = CardType.WEAPON;
		}
		else if (cardIndex < NUM_CARDS_IN_DECK) 
		{
			this.cardType = CardType.ROOM;
		}
		
		this.cardName = gameList.get(cardIndex);
	}
	
	
	/* Public Methods */ 
	
	
	// getType() method
	public CardType getType()
	{
		return this.cardType;
	}
	
	
	// getName() method
	public Name getName()
	{
		return this.cardName;
	}
	
	
	// getCardIndex()
	public int getCardIndex()
	{
		return this.cardIndex;
	}
	
}
