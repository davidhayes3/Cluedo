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
	public void chooseMove(ArrayList<Player> players, int playerTurn, Board gameBoard)
	{
		
		String playerChoice;
		Scanner scanner;
		
		// Die score can be any value between 2 and 12
		int dieScore = ThreadLocalRandom.current().nextInt(MIN_DIE_SCORE, MAX_DIE_SCORE + 1);
		System.out.printf("\nYour dice score is " + dieScore + "\n");
		
		int movesRemaining = dieScore;

		// Continue until the player has no moves left
		while (movesRemaining > 0)
		{
			
			System.out.printf("You have " + movesRemaining + " moves remaining.");
			
			// Get current position of player's SuspectPawn
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
				
				// If room has secret passage
				if (currentSlot.getRoomNumber() == KITCHEN_ROOM_NUMBER || currentSlot.getRoomNumber() == CONSERVATORY_ROOM_NUMBER 
						|| currentSlot.getRoomNumber() == LOUNGE_ROOM_NUMBER || currentSlot.getRoomNumber() == STUDY_ROOM_NUMBER)
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
