package ie.ucd.cluedo;

import java.util.ArrayList;


public class Hypothesis {
	
	int playerTurn =0;
	//Array list to hold the cards to be compared
	private ArrayList<Card> activePlayerCards;
	private ArrayList<Card> playerCards;
	
	private int suspectHypothesis;
	private int weaponHypothesis;
	private int roomHypothesis;
	
	//Need to change to get room player is in
	public Hypothesis(int suspectHypothesis, int weaponHypothesis, int roomHypothesis){
		this.suspectHypothesis = suspectHypothesis;
		this.weaponHypothesis = weaponHypothesis;
		this.roomHypothesis = roomHypothesis;
		
	}
	
	public void checkPlayersCards(ArrayList<Player> players, int playerTurn){
		
		
		for(int i = 0; i < players.size(); i++)
			if(i == playerTurn){
				continue;
			}
			else{
				playerCards = players.get(i).getCards();
				for(int j = 0; j < playerCards.size(); j++){
					if(playerCards.get(j).getCardIndex() == suspectHypothesis || playerCards.get(j).getCardIndex() == roomHypothesis){
						System.out.println("Player " + i + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j));
						break;
					}
					else if(playerCards.get(j).getCardIndex() == weaponHypothesis){
						System.out.println("Player " + i + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j));
						break;
					}
					else if(playerCards.get(j).getCardIndex() == roomHypothesis){
						System.out.println("Player " + i + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j));
						break;
					}
					else{
						System.out.println("The hypothesis was not refuted");
						
					}
				}
				
			}
	}
	
	public void updateNotebook(Player accuser, Player refuter, Card cardShown){
		
		accuser.getNotebook().makeEntry("Player " + refuter.getPlayerNumber() + " refuted the hypthesis with " + cardShown.getName());
		
	}
}
