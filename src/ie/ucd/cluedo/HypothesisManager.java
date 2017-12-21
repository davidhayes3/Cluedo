package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;

public class HypothesisManager {
	
	private ArrayList<Player> players;
	
	public HypothesisManager(ArrayList<Player> players){
		
		this.players = players;
	
	}
	
	public void checkPlayersCards(Hypothesis hypothesis, int playerTurn){
		
		boolean notRefuted = true;
		int suspectHypothesis = hypothesis.getSuspect();
		int weaponHypothesis = hypothesis.getWeapon();
		int roomHypothesis = hypothesis.getRoom();
		
		ArrayList<Card> playerCards;
	
		
		
			for(int i = 0; i < players.size(); i++)
					if(i == playerTurn){
						continue;
					}
					else{
						playerCards = players.get(i).getCards();
						for(int j = 0; j < playerCards.size(); j++){
							if(playerCards.get(j).getCardIndex() == suspectHypothesis){
								System.out.println("Player " + i + " refuted the hypothesis");
								updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j));
								return;
							}
							else if(playerCards.get(j).getCardIndex() == weaponHypothesis){
								System.out.println("Player " + i + " refuted the hypothesis");
								updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j));
								return;
							}
							else if(playerCards.get(j).getCardIndex() == roomHypothesis){
								System.out.println("Player " + i + " refuted the hypothesis");
								updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j));
								return;
							}

						}
							System.out.println("The hypothesis was not refuted");

					}
				}
	
	public void updateNotebook(Player accuser, Player refuter, Card cardShown)
	{
		
		accuser.getNotebook().makeEntry("Player " + refuter.getPlayerNumber() + " refuted the hypothesis with " + cardShown.getName());
		refuter.getNotebook().makeEntry("I refuted  Player " + accuser.getPlayerNumber() + "'s hypothesis with the card" + cardShown.getName());
		
		for(int i = 0; i < players.size(); i++)
		{
			int j = i + 1;
			if(j == refuter.getPlayerNumber() || j == accuser.getPlayerNumber())
			{
				continue;
			}
			else{
				players.get(i).getNotebook().makeEntry("Player " + accuser.getPlayerNumber() + " made a hypothesis. Player " + refuter.getPlayerNumber() + " refuted the hyothesis");

			}
			
				
		}
	}
}
