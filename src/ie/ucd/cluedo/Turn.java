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
	boolean gameOver;
	Movement move;
	HypothesisManager hypothesisManager;
	AccusationManager accusationManager;
	
	
	// Constructor
	public Turn(int playerTurn, boolean gameOver) 
	{
		this.playerTurn = playerTurn;
		this.gameOver = gameOver;
	}
	
	
	/* Public Methods */
	
	
	// beforeRollMove() Method
	// Purpose: Implements a move where the player has not yet rolled and is not in a room, and returns whether a roll is allowed on the next turn
	public boolean beforeRollMove(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "r":	move = new Movement();
						move.chooseMove(players, this.playerTurn, gameBoard);
						return true;
						
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
			
			case "n":	players.get(this.playerTurn).getNotebook().showNoteBook();
						return hasRolled;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % players.size();
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
						
		}
		
	}
	
	
	// beforeRollMoveInRoom() Method
	// Purpose: Implements a move where the player has not rolled yet and is already in a room, and returns whether a roll is allowed on the next turn
	public boolean beforeRollMoveInRoom(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{

		switch (playerChoice)
		{
			
			case "r":	move = new Movement();
						move.chooseMove(players, this.playerTurn, gameBoard);
						return true;
						

		
			case "h":	hypothesisManager = new HypothesisManager();
						hypothesisManager.simulateHypothesis(players, gameBoard, this.playerTurn);
						
						// Increments playerTurn and resets to 0 if needed
						this.playerTurn = (this.playerTurn + 1) % players.size();
						
						return false;
		
			case "a":	accusationManager = new AccusationManager();
						this.gameOver = accusationManager.simulateAccusation(players, this.playerTurn, this.gameOver);
						
						// playerTurn remains the same as an unsuccessful accusation shortens the length of players. playerTurn reset to 0 if needed 
						this.playerTurn = this.playerTurn % players.size();
						
						return hasRolled;
						
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
			
			case "n":	players.get(playerTurn).getNotebook().showNoteBook();
						return hasRolled;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % players.size();
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
		
		}
	
	}
	

	// afterRollMove() Method
	// Purpose: Implements a move where the player has already rolled and is not in a room, and returns whether a roll is allowed on the next turn
	public boolean afterRollMove(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "n":	players.get(playerTurn).getNotebook().showNoteBook();
						return hasRolled;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % players.size();
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return hasRolled;
						
		}
	
	}
	
	
	// afterRollMoveInRoom() Method
	// Purpose: Implements a move where the player has already rolled and is in a room, and returns whether a roll is allowed on the next turn
	public boolean afterRollMoveInRoom(ArrayList<Player> players, Board gameBoard, boolean hasRolled, String playerChoice)
	{
		
		switch (playerChoice)
		{
			
			case "h":	hypothesisManager = new HypothesisManager();
						hypothesisManager.simulateHypothesis(players, gameBoard, this.playerTurn);
						
						this.playerTurn = (this.playerTurn + 1) % players.size();
						
						return false;
						
			case "a":	accusationManager = new AccusationManager();
						this.gameOver = accusationManager.simulateAccusation(players, this.playerTurn, this.gameOver);
			
						this.playerTurn = this.playerTurn % players.size();
						
						return hasRolled;
						
			case "c":	players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
			
			case "n":	players.get(playerTurn).getNotebook().showNoteBook();
						return hasRolled;			
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % players.size();
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
	
	
	// getGameOver() Method
	public boolean getGameOver()
	{
		return this.gameOver;
	}
	
}
