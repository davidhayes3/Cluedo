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
	
	ArrayList<Player> players;
	int playerTurn;
	int numPlayers;
	Board gameBoard;
	boolean gameOver;
	Movement move;
	HypothesisManager hypothesisManager;
	AccusationManager accusationManager;
	
	
	// Constructor
	public Turn(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard, boolean gameOver) 
	{
		
		this.players = players;
		this.playerTurn = playerTurn;
		this.numPlayers = numPlayers;
		this.gameBoard = gameBoard;
		this.gameOver = gameOver;
	
	}
	
	
	/* Public Methods */
	
	
	// beforeRollMove() Method
	// Purpose: Implements a move where the player has not rolled yet and is not in a room
	// Input: hasRolled, true if player has already rolled; playerChoice, the players choice of move
	// Output: None
	public boolean beforeRollMove(boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "r":	move = new Movement(this.players, this.playerTurn, this.numPlayers, this.gameBoard);
						move.chooseMove();
						return true;
						
			case "c":	this.players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
			
			case "n":	this.players.get(this.playerTurn).getNotebook().showNoteBook();
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
	public boolean beforeRollMoveInRoom(boolean hasRolled, String playerChoice)
	{

		switch (playerChoice)
		{
			
			case "r":	move = new Movement(this.players, this.playerTurn, this.numPlayers, this.gameBoard);
						move.chooseMove();
						return true;
						
			case "c":	this.players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "h":	hypothesisManager = new HypothesisManager(this.players, this.gameBoard);
						hypothesisManager.simulateHypothesis(this.playerTurn, this.numPlayers);
						this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
		
			case "a":	accusationManager = new AccusationManager(this.players);
						this.gameOver = accusationManager.simulateAccusation(this.playerTurn, this.numPlayers, this.gameOver);
						
						this.numPlayers--;
						this.playerTurn = this.playerTurn % this.numPlayers;
						
						return hasRolled;
			
			case "n":	this.players.get(playerTurn).getNotebook().showNoteBook();
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
	public boolean afterRollMove(boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "c":	this.players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "n":	this.players.get(playerTurn).getNotebook().showNoteBook();
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
	public boolean afterRollMoveInRoom(boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			case "h":	hypothesisManager = new HypothesisManager(this.players, this.gameBoard);
						hypothesisManager.simulateHypothesis(this.playerTurn, this.numPlayers);
						this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
						
			case "c":	this.players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "a":	accusationManager = new AccusationManager(this.players);
						this.gameOver = accusationManager.simulateAccusation(this.playerTurn, this.numPlayers, this.gameOver);
						
						this.numPlayers--;
						this.playerTurn = this.playerTurn % this.numPlayers;
						
						return hasRolled;
			
			case "n":	this.players.get(playerTurn).getNotebook().showNoteBook();
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
