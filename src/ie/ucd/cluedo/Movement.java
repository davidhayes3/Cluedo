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
	
	MoveType moveType;
	
	
	// Constructor
	
	public Movement() 
	{
		moveType = new MoveType();
	}
	
	
	/* Public Methods */
	
	
	// chooseMove() Method
	// Purpose: Allows the player to choose what type of move they want to make and updates movesRemaining accordingly
	// Input: None
	// Output: numPlayers, the number of players in the game
	public void chooseMove(ArrayList<Player> players, int playerTurn, Board gameBoard)
	{
		
		String playerChoice;
		Scanner scanner;
		int diceScore = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
		int movesRemaining = diceScore;
		
		System.out.printf("\nYour dice score is " + diceScore + "\n");

		while (movesRemaining > 0)
		{
			
			System.out.printf("You have " + movesRemaining + " moves remaining.");
			
			Slot currentSlot = players.get(playerTurn).getSuspectPawn().getPosition();
			
			if (currentSlot instanceof BoardSlot)
			{
				
				scanner = new Scanner(System.in);
				
				System.out.printf("\nWhat do you want to do?\nMove Up [u]\nMove Down [d],\nMove Left [l]\nMove Right [r]\nFinish moving [f]\nOption: " );
				playerChoice = scanner.nextLine();	
				movesRemaining = moveType.boardMove(players, playerTurn, gameBoard, movesRemaining, playerChoice);
				
			}
			
			else if (currentSlot instanceof RoomSlot)
			{
				
				if (currentSlot.getRoomNumber() == 1 || currentSlot.getRoomNumber() == 3 || currentSlot.getRoomNumber() == 5 || currentSlot.getRoomNumber() == 7)
				{
					
					scanner = new Scanner(System.in);
					
					System.out.printf("\nLeave Room [l]\nStay in room [s]\nAccess Secret Passage [p]\nOption: " );				
					playerChoice = scanner.nextLine();
					movesRemaining = moveType.secretRoomMove(players, playerTurn, gameBoard, movesRemaining, playerChoice);	
					
				}
				
				else
				{
	
					scanner = new Scanner(System.in);
					
					System.out.printf("\nLeave Room [l]\nStay in room [s]\nOption: " );				
					playerChoice = scanner.nextLine();
					movesRemaining = moveType.normalRoomMove(players, playerTurn, gameBoard, movesRemaining, playerChoice);
				
				}
				
			}
			
			else if (currentSlot instanceof DoorSlot)
			{
				
				scanner = new Scanner(System.in);

				System.out.printf("\nWhat do you want to do?\nMove Up [u]\nMove Down [d],\nMove Left [l]\nMove Right [r]\nFinish moving [f]\nOption: " );
				playerChoice = scanner.nextLine();	
				movesRemaining = moveType.boardMove(players, playerTurn, gameBoard, movesRemaining, playerChoice);
			
			}
			
		}
		
		System.out.printf("\nYour moves are finished for this turn");

	}		
}
