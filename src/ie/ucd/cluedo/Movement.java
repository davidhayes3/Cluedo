/***************************************************************/
/* Movement Class
/* 
/* Implements movement on the board 
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Movement
{
	
	// Attributes
	
	ArrayList<Player> players;
	int playerTurn;
	int numPlayers;
	Board gameBoard;
	MoveType typemove;
	
	
	// Constructor
	
	public Movement(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard) 
	{
		this.players = players;
		this.playerTurn = playerTurn;
		this.numPlayers = numPlayers;
		this.gameBoard = gameBoard;
	}
	
	
	/* Public Methods */
	
	
	// chooseMove() Method
	// Purpose: Allows the player to choose what type of move they want to make and updates movesRemaining accordingly
	// Input: None
	// Output: numPlayers, the number of players in the game
	@SuppressWarnings("resource")
	public void chooseMove()
	{
		
		MoveType moveType = new MoveType(this.players, this.playerTurn, this.numPlayers, this.gameBoard);
		int diceScore = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
		int movesRemaining = diceScore;
		
		System.out.printf("\nYour dice score is " + diceScore + "\n");

		while (movesRemaining > 0)
		{
			
			System.out.printf("You have " + movesRemaining + " moves remaining.");
			
			Slot currentSlot = this.players.get(this.playerTurn).getSuspectPawn().getPosition();
			
			if (currentSlot instanceof BoardSlot)
			{
				
				Scanner scanner = new Scanner(System.in);
				
				System.out.printf("\nWhat do you want to do?\nMove Up [u]\nMove Down [d],\nMove Left [l]\nMove Right [r]\nFinish moving [f]\nOption: " );
				String playerChoice = scanner.nextLine();	
				movesRemaining = moveType.boardMove(movesRemaining, playerChoice);
				
			}
			
			else if (currentSlot instanceof RoomSlot)
			{
				
				if (currentSlot.getRoomNumber() == 1 || currentSlot.getRoomNumber() == 3 || currentSlot.getRoomNumber() == 5 || currentSlot.getRoomNumber() == 7)
				{
					
					String playerChoice;
					Scanner scanner = new Scanner(System.in);
					
					System.out.printf("\nLeave Room [l]\nStay in room [s]\nAccess Secret Passage [p]\nOption: " );				
					playerChoice = scanner.nextLine();
					movesRemaining = typemove.secretRoomMove(movesRemaining, playerChoice);				
					
				}
				
				else
				{
					
					movesRemaining = moveType.normalRoomMove(movesRemaining);
				
				}
				
			}
			
			else if (currentSlot instanceof DoorSlot)
			{
				
				Scanner scanner = new Scanner(System.in);

				System.out.printf("\nWhat do you want to do?\nMove Up [u]\nMove Down [d],\nMove Left [l]\nMove Right [r]\nFinish moving [f]\nOption: " );
				String playerChoice = scanner.nextLine();	
				movesRemaining = moveType.boardMove(movesRemaining, playerChoice);
			
			}
			
		}
		
		System.out.printf("\nYour moves are finished for this turn");

	}		
}
