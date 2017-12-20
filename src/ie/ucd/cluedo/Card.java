package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

public class Card 
{	
	
	// Attributes of card
	private CardType cardType;
	private Name cardName;
	public int cardIndex;
	// Card constructor
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
	
	// Returns type of card
	public CardType getType()
	{
		return this.cardType;
	}
	
	// Returns name of card
	public Name getName()
	{
		return this.cardName;
	}
	
	public int getCardIndex(){
		return this.cardIndex;
	}
}
