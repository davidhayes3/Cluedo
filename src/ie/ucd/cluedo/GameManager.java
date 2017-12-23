/***************************************************************/
/* Game Manager Class
/* 
/* Implements full game of Cluedo
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.MAX_NUM_PLAYERS;
import java.util.ArrayList;

public class GameManager 
{
	
	// Attributes
	static Game Cluedo;
	static ArrayList<Player> players;
	
	// Main
	public static void main(String[] args) 
	{
	
		int numPlayers;
		players = new ArrayList<Player>(MAX_NUM_PLAYERS);
		
		System.out.println("NEW GAME\n\n");
		
		Cluedo = new Game();
		
		numPlayers = Cluedo.getNumPlayers();
			
		Cluedo.makePlayers(numPlayers, players);
		
		Cluedo.makeBoard();
		
		Cluedo.getCharacters(players);
		
		Cluedo.createDeck();
		
		Cluedo.allocateCards(players, numPlayers);
		
		Cluedo.gameTurns(players, numPlayers);

	}

}
