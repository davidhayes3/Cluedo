package ie.ucd.cluedo;

public class GameManager {
	
	static boolean gameOver = false;
	
	public static void main(String[] args) {
		
		System.out.println("NEW GAME\n\n");
		
		Game Cluedo = new Game();
		// Get number of players
		Cluedo.getNumPlayers();
			
		// Print details of murder
		//Cluedo.printMurderDetails();
			
		// Instantiate all players, assign a suspect pawn to each player and place these suspect pawns at a slot
		Cluedo.makePlayers();
		
		// Create deck of cards excluding the murder cards
		Cluedo.createDeck();
		//printDeckInPlayDetails();
		
		// Allocate cards to players
		Cluedo.allocateCards();
		//printCardAllocation();
						
		// Setup Board
		//gameBoard = new Board(players);
		Cluedo.makeBoard();
		
		// Game play
		while(!gameOver)
		{
			gameOver = Cluedo.Turn();
		}
		
		System.out.println("\n\nGAME OVER\n\n");

	}

}
