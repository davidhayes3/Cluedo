package ie.ucd.cluedo;

import java.util.ArrayList;

import static ie.ucd.cluedo.GameValues.*;


public class HypothesisManager 
{
	
	ArrayList<Player> players;
	
	public HypothesisManager(ArrayList<Player> players)
	{
		
		this.players = players;	
	
	}
	
	public void checkPlayersCards(Hypothesis hypothesis, int playerTurn)
	{
		
		ArrayList<Card> playerCards;
		
		int suspectHypothesis = hypothesis.getSuspect();
		int weaponHypothesis = hypothesis.getWeapon();
		int roomHypothesis = hypothesis.getRoom();
		
		for (int i = 0; i < players.size(); i++)
		{
			if (i == playerTurn)
			{
				continue;
			}
			
			else
			{
				
				playerCards = players.get(i).getCards();
				
				for(int j = 0; j < playerCards.size(); j++)
				{
					if (playerCards.get(j).getCardIndex() == suspectHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}

					else if (playerCards.get(j).getCardIndex() == weaponHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}
				
					else if (playerCards.get(j).getCardIndex() == roomHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}

				}
			}
		}
		
		System.out.println("The hypothesis was not refuted");
		
	}
	
	public void updateNotebook(Player accuser, Player refuter, Card cardShown, int suspectHypothesis, int weaponHypothesis, int roomHypothesis)
	{
		
		// Two entries for accuser
		accuser.getNotebook().makeEntry(" I formulated the hypothesis " + gameList.get(suspectHypothesis) + 
								 ", " + gameList.get(weaponHypothesis) + ", " + gameList.get(roomHypothesis) + ".");
		accuser.getNotebook().makeEntry(refuter.getSuspectPawn().getName() + " refuted the hypothesis with the card " + cardShown.getName());
		
		// Two entries for refuter
		refuter.getNotebook().makeEntry(accuser.getSuspectPawn().getName() + " formulated the hypothesis " + gameList.get(suspectHypothesis) + 
				 ", " + gameList.get(weaponHypothesis) + ", " + gameList.get(roomHypothesis) + ".");
		
		refuter.getNotebook().makeEntry("I refuted " + accuser.getSuspectPawn().getName() + "'s hypothesis with the card " + cardShown.getName());
		
		for (int i = 0; i < players.size(); i++)
		{
			
			if (i + 1 == refuter.getPlayerNumber() || i + 1 == accuser.getPlayerNumber())
			{
				continue;
			}
			
			else
			{
				players.get(i).getNotebook().makeEntry(accuser.getSuspectPawn().getName() + "made the hypothesis " + gameList.get(suspectHypothesis) 
				+ ", " + gameList.get(weaponHypothesis) + ", " + gameList.get(roomHypothesis) + ". "+ refuter.getSuspectPawn().getName() 
				+ " refuted the hyothesis.");
				
				players.get(i).getNotebook().makeEntry(refuter.getSuspectPawn().getName() + "refuted the hypothesis showing the card" + cardShown.getName());
			}				
		}
	}
}
