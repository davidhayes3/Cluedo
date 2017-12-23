/***************************************************************/
/* Turn Class
/* 
/* Implements a turn in the game of Cluedo
/***************************************************************/

package ie.ucd.cluedo;

import java.util.ArrayList;

public class Turn 
{
	
	// Attributes
	
	int playerTurn;
	int numPlayers;
	boolean gameOver;
	Movement move;
	HypothesisManager hypothesisManager;
	AccusationManager accusationManager;
	
	
	// Constructor
	public Turn(int playerTurn, int numPlayers, boolean gameOver) 
	{

		this.playerTurn = playerTurn;
		this.numPlayers = numPlayers;
		this.gameOver = gameOver;
	
	}
	
	
	/* Public Methods */
	
	
	// beforeRollMove() Method
	// Purpose: Implements a move where the player has not rolled yet and is not in a room
	// Input: hasRolled, true if player has already rolled; playerChoice, the players choice of move
	// Output: None
	public boolean beforeRollMove(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "r":	move = new Movement();
						move.chooseMove(players, this.playerTurn, this.numPlayers, gameBoard);
						return true;
						
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
			
			case "n":	players.get(this.playerTurn).getNotebook().showNoteBook();
						return hasRolled;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
		
		}
	}
	
	
	// beforeRollMoveInRoom() Method
	// Purpose: Implements a move where the player has not rolled yet and is already in a room
	// Input: hasRolled, true if player has already rolled; playerChoice, the players choice of move
	// Output: None
	public boolean beforeRollMoveInRoom(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{

		switch (playerChoice)
		{
			
			case "r":	move = new Movement();
						move.chooseMove(players, this.playerTurn, this.numPlayers, gameBoard);
						return true;
						
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "h":	hypothesisManager = new HypothesisManager(players, gameBoard);
						hypothesisManager.simulateHypothesis(this.playerTurn, this.numPlayers);
						this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
		
			case "a":	accusationManager = new AccusationManager(players);
						this.gameOver = accusationManager.simulateAccusation(this.playerTurn, this.numPlayers, this.gameOver);
						
						this.numPlayers--;
						this.playerTurn = this.playerTurn % this.numPlayers;
						
						return hasRolled;
			
			case "n":	players.get(playerTurn).getNotebook().showNoteBook();
						return hasRolled;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
		
		}
	}
	

	// afterRollMove() Method
	// Purpose: Implements a move where the player has already rolled and is not in a room
	// Input: hasRolled, true if player has already rolled; playerChoice, the players choice of move
	// Output: None
	public boolean afterRollMove(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "n":	players.get(playerTurn).getNotebook().showNoteBook();
						return hasRolled;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
						
		}
	}
	
	
	// afterRollMoveInRoom() Method
	// Purpose: Implements a move where the player has already rolled and is in a room
	// Input: hasRolled, true if player has already rolled; playerChoice, the players choice of move
	// Output: None
	public boolean afterRollMoveInRoom(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			case "h":	hypothesisManager = new HypothesisManager(players, gameBoard);
						hypothesisManager.simulateHypothesis(this.playerTurn, this.numPlayers);
						this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
						
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "a":	accusationManager = new AccusationManager(players);
						this.gameOver = accusationManager.simulateAccusation(this.playerTurn, this.numPlayers, this.gameOver);
						
						this.numPlayers--;
						this.playerTurn = this.playerTurn % this.numPlayers;
						
						return hasRolled;
			
			case "n":	players.get(playerTurn).getNotebook().showNoteBook();
						return hasRolled;			
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
		}
			
	}
	
	
	// getPlayerTurn() Method
	public int getPlayerTurn()
	{
		return this.playerTurn;
	}
	
	
	// getNumPlayers() Method
	public int getNumPlayers()
	{
		return this.numPlayers;
	}
	
	
	// getGameOver() Method
	public boolean getGameOver()
	{
		return this.gameOver;
	}
	
}
