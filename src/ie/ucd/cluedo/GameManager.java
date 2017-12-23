/***************************************************************/
/* Game Manager Class
/* 
/* Implements full game of Cluedo
/***************************************************************/

package ie.ucd.cluedo;

import java.util.ArrayList;

import static ie.ucd.cluedo.GameValues.*;


public class GameManager 
{
	
	// Attributes
	static Game Cluedo;
	static ArrayList<Player> players;
	static Board gameBoard;
	static ArrayList<Card> cardDeck;
	
	
	// Main
	public static void main(String[] args) 
	{		
		
		players = new ArrayList<Player>(MAX_NUM_PLAYERS);
		int numPlayers;
		
		System.out.println("NEW GAME\n\n");
		
		Cluedo = new Game();
		
		numPlayers = Cluedo.getNumPlayers();
			
		Cluedo.makePlayers(numPlayers, players);
		
		gameBoard = Cluedo.makeBoard();
		
		Cluedo.getCharacters(players, gameBoard);
		
		cardDeck = Cluedo.createDeck();
		
		Cluedo.allocateCards(players, cardDeck);
		
		Cluedo.gameTurns(players, gameBoard);
	
	}
}
