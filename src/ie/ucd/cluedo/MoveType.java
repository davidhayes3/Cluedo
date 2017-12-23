/***************************************************************/
/* MoveType Class
/* 
/* Implements the types of move possible on the board 
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.util.ArrayList;
public class MoveType 
{
	
	// Constructor
	public MoveType() 
	{

	}
	
	
	/* Public Methods */
	
	
	// normalRoomMove() Method
	// Purpose: Implements possible actions when in a room
	public int normalRoomMove(ArrayList<Player> players, int playerTurn, Board gameBoard, int movesRemaining, String playerChoice)
	{
		
		switch (playerChoice)
		{
		
			case "l":	// Choosing to leave moves the player to the rooms door without taking a move away
						Slot doorSlot = getDoorSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard);
						players.get(playerTurn).getSuspectPawn().movePosition(doorSlot);
	
						break;
						
			
			case "s":	// Choosing to stay ends the players movement for that turn
						movesRemaining = 0;
						break;
			
			default:	break;
		
		}
		
		return movesRemaining;
		
	}
	
	
	// secretRoomMove() Method
	// Purpose: Implements possible actions when in a room with a secret passage
	public int secretRoomMove(ArrayList<Player> players, int playerTurn, Board gameBoard, int movesRemaining, String playerChoice)
	{
		
		switch (playerChoice)
		{
		
			case "l":	Slot doorSlot = getDoorSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard);
						players.get(playerTurn).getSuspectPawn().movePosition(doorSlot);
				
						break;
						
			case "s":	movesRemaining = 0;
						break;
						
			case "p":	// Moves player to room at other side of secret passage
						Slot secretSlot = getSecretSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard, players);
						players.get(playerTurn).getSuspectPawn().movePosition(secretSlot);
			
						movesRemaining--;
			
			default:	break;
		
		}
		
		return movesRemaining;
		
	}
	
	
	// boardMove() Method
	// Purpose: Implements possible actions when in the corridor
	public int boardMove(ArrayList<Player> players, int playerTurn, Board gameBoard, int movesRemaining, String playerChoice)
	{
		
		int newCol, newRow;
		boolean canMove;
		Slot currentPosition = players.get(playerTurn).getSuspectPawn().getPosition();
				
		switch (playerChoice)
		{
			
			case "u":	newRow = currentPosition.getRow() - 1;
						newCol = currentPosition.getCol();
						
						// Checks if player can move to indicated postion of preference
						canMove = canMove(currentPosition, newCol, newRow, gameBoard, players);
					
						// If the players intended move is legal, move the player
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						// If player moves on to a board button, move the player to a room slot within that room
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							
							Slot roomSlot = getRoomSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard, players);
							
							players.get(playerTurn).getSuspectPawn().movePosition(roomSlot);
							
						}
						
						return movesRemaining;
			
			
			case "d":	newRow = currentPosition.getRow() + 1;
						newCol = currentPosition.getCol();
			
						canMove = canMove(currentPosition, newCol, newRow, gameBoard, players);
					
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard, players);
							
							players.get(playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
			
			
			case "l":	newRow = currentPosition.getRow();
						newCol = currentPosition.getCol() - 1;
	
						canMove = canMove(currentPosition, newCol, newRow, gameBoard, players);
						
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard, players);
							
							players.get(playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
				
			
			case "r":	newRow = currentPosition.getRow();
						newCol = currentPosition.getCol() + 1;

						canMove = canMove(currentPosition, newCol, newRow, gameBoard, players);
	
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard, players);
							
							players.get(playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
			
			
			case "f":	// If the player wants to finish moving without using all its possible moves
						movesRemaining = 0;
						return movesRemaining;
				
			
			default:	System.out.println("Please enter a valid option");
						return movesRemaining;
							
		}
	}
	
	
	/* Private Methods */
	
	
	// boardMove() Method
	// Purpose: Implements possible actions when in the corridor
	boolean canMove(Slot currentPosition, int newCol, int newRow, Board gameBoard, ArrayList<Player> players)
	{
		
		// If desired position is not on board
		if (newRow < 0 || newRow > BOARD_HEIGHT - 1 || newCol < 0 || newCol > BOARD_WIDTH - 1)
		{
			System.out.println("That position is not on the board.");
			return false;
		}

		
		// If player tries to access room other than through a door
		else if (gameBoard.getSlots()[newRow][newCol] == null || gameBoard.getSlots()[newRow][newCol] instanceof RoomSlot)
		{
			
			// If not from a door slot, print illegal move
			if (!(currentPosition instanceof DoorSlot))
			{
				System.out.println("Cannot access a room through a wall.");
			}
			
			return false;
		}
		
		
		// If there's already a suspect pawn at the desired position
		else
		{
			for (Player p: players)
			{
				if (p.getSuspectPawn().getPosition() == gameBoard.getSlots()[newRow][newCol])
				{
					System.out.println("There's already a player at that position.");
					return false;
				}
			}
			
			for (SuspectPawn s: gameBoard.getSuspectPawns())
			{
				if (s.getPosition() == gameBoard.getSlots()[newRow][newCol])
				{
					System.out.println("There's already a pawn at that position.");
					return false;
				}
					
			}
		}

		return true;
	
	}
	
	// getSecretSlot() Method
	// Purpose: Finds a free slot in the room at the other side of the secret passage
	private Slot getSecretSlot(int roomNumber, Board gameBoard, ArrayList<Player> players)
	{
		
		// Method can only be called from 4 rooms with a secret passage, so only 4 possible values of roomNumber
		switch(roomNumber)
		{
		
			case 1:		return getRoomSlot(STUDY_ROOM_NUMBER, gameBoard, players);
			
			case 3:		return getRoomSlot(LOUNGE_ROOM_NUMBER, gameBoard, players);
					
			case 5: 	return getRoomSlot(CONSERVATORY_ROOM_NUMBER, gameBoard, players);
					
			case 7:		return getRoomSlot(KITCHEN_ROOM_NUMBER, gameBoard, players);
					
			default:	return null;
			
		}
		
	}
	
	// getRoomSlot() Method
	// Purpose: Finds a free slot in the room the player is trying to enter
	private Slot getRoomSlot(int roomNumber, Board gameBoard, ArrayList<Player> players)
	{
		
		boolean slotOccupied;
		
		ArrayList<RoomSlot> roomSlots = gameBoard.getRoomSlots();
		
		for (RoomSlot rs: roomSlots)
		{
			slotOccupied = false;
			
			if (rs.getRoomNumber() == roomNumber)
			{
				
				// Check if any players have suspect pawns at the slot in question. If so, discard slot 
				for (Player p: players)
				{
					if (p.getSuspectPawn().getPosition() == gameBoard.getSlots()[rs.getRow()][rs.getCol()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				// Check if any remaining suspect pawns are at the slot in question. If so, discard slot 
				for (SuspectPawn s: gameBoard.getSuspectPawns())
				{
					if (s.getPosition() == gameBoard.getSlots()[rs.getRow()][rs.getCol()])
					{
						slotOccupied = true;
						break;
					}				
				}
				
				// Return the first free slot in room found
				if (!slotOccupied)
				{
					return gameBoard.getSlots()[rs.getRow()][rs.getCol()];
				}
			
			}
		
		}
		
		return null;
	}
	
	
	// getDoorSlot() Method
	// Purpose: Finds the slot of the door for a given room
	private Slot getDoorSlot(int roomNumber, Board gameBoard) 
	{
		
		ArrayList<DoorSlot> doorSlots = gameBoard.getDoorSlots();
		
		for (DoorSlot ds: doorSlots)
		{
			if (ds.getRoomNumber() == roomNumber)
			{
				return gameBoard.getSlots()[ds.getRow()][ds.getCol()];
			}
		}
		
		return null;
		
	}	
	
}
