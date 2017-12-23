package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;

public class Turn 
{
	
	ArrayList<Player> players;
	int playerTurn;
	int numPlayers;
	Board gameBoard;
	boolean gameOver;
	MoveChoice move;
	HypothesisManager hypothesisManager;
	AccusationManager accusationManager;
	
	public Turn(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard, boolean gameOver) 
	{
		this.players = players;
		this.playerTurn = playerTurn;
		this.numPlayers = numPlayers;
		this.gameBoard = gameBoard;
		this.gameOver = gameOver;
	}
	
	@SuppressWarnings("resource")
	public boolean beforeRollMove(boolean hasRolled, String playerChoice)
	{

		
		switch (playerChoice)
		{
			
			case "r":	MoveChoice move = new MoveChoice(this.players, this.playerTurn, this.numPlayers, this.gameBoard);
						move.chooseMove();
						return true;
						
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
	
	
	@SuppressWarnings("resource")
	public boolean beforeRollMoveInRoom(boolean hasRolled, String playerChoice)
	{


		switch (playerChoice)
		{
			
			case "r":	move = new MoveChoice(this.players, this.playerTurn, this.numPlayers, this.gameBoard);
						move.chooseMove();
						return true;
						
			case "c":	this.players.get(this.playerTurn).getPlayerHand().showHand();
						return hasRolled;
		
			case "h":	hypothesisManager = new HypothesisManager(this.players, this.gameBoard);
						hypothesisManager.simulateHypothesis(this.playerTurn);
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
	

	@SuppressWarnings("resource")
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
	
	
	@SuppressWarnings("resource")
	public boolean afterRollMoveInRoom(boolean hasRolled, String playerChoice)
	{

		
		switch (playerChoice)
		{
			case "h":	hypothesisManager = new HypothesisManager(this.players, this.gameBoard);
						hypothesisManager.simulateHypothesis(this.playerTurn);
						return hasRolled;
						
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
	
	public int getPlayerTurn()
	{
		return this.playerTurn;
	}
	
	public int getNumPlayers()
	{
		return this.numPlayers;
	}
	
	public boolean getGameOver()
	{
		return this.gameOver;
	}
	
}
