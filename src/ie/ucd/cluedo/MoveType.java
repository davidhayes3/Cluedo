package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.BOARD_HEIGHT;
import static ie.ucd.cluedo.GameValues.BOARD_WIDTH;

import java.util.ArrayList;
import java.util.Scanner;

public class MoveType 
{
	
	// Constructor
	public MoveType() 
	{

	}
	
	
	public int normalRoomMove(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard, int movesRemaining, String playerChoice)
	{
		
		switch (playerChoice)
		{
		
			case "l":	Slot doorSlot = getDoorSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard);
						players.get(playerTurn).getSuspectPawn().movePosition(doorSlot);
	
						break;
						
			
			case "s":	movesRemaining = 0;
						break;
			
			default:	break;
		
		}
		
		return movesRemaining;
	}
	
	public int secretRoomMove(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard, int movesRemaining, String playerChoice)
	{
		
		switch (playerChoice)
		{
		
			case "l":	Slot doorSlot = getDoorSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard);
				
						players.get(playerTurn).getSuspectPawn().movePosition(doorSlot);
				
						break;
						
			case "s":	movesRemaining = 0;
						break;
						
			case "p":	Slot secretSlot = getSecretSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber(), gameBoard, players);
				
						players.get(playerTurn).getSuspectPawn().movePosition(secretSlot);
			
						movesRemaining--;
			
			default:	break;
		
		}
		
		return movesRemaining;
	}
	
	
	public int boardMove(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard, int movesRemaining, String playerChoice)
	{
		
		int newCol, newRow;
		boolean canMove;
				
		switch (playerChoice)
		{
			
			case "u":	newRow = players.get(playerTurn).getSuspectPawn().getPosition().getYPosition() - 1;
						newCol = players.get(playerTurn).getSuspectPawn().getPosition().getXPosition();
						
						canMove = canMove(newCol, newRow, gameBoard, players);
					
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
			
			case "d":	newRow = players.get(playerTurn).getSuspectPawn().getPosition().getYPosition() + 1;
						newCol = players.get(playerTurn).getSuspectPawn().getPosition().getXPosition();
			
						canMove = canMove(newCol, newRow, gameBoard, players);
					
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
			
			case "l":	newRow = players.get(playerTurn).getSuspectPawn().getPosition().getYPosition();
						newCol = players.get(playerTurn).getSuspectPawn().getPosition().getXPosition() - 1;
	
						canMove = canMove(newCol, newRow, gameBoard, players);
						
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
				
			case "r":	newRow = players.get(playerTurn).getSuspectPawn().getPosition().getYPosition();
						newCol = players.get(playerTurn).getSuspectPawn().getPosition().getXPosition() + 1;

						canMove = canMove(newCol, newRow, gameBoard, players);
	
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
			
			case "f":	movesRemaining = 0;
						return movesRemaining;
				
			default:	System.out.println("Please enter a valid option");
						return movesRemaining;
							
		}
	}
	


	boolean canMove(int newCol, int newRow, Board gameBoard, ArrayList<Player> players)
	{
		
		if (newRow < 0 || newRow > BOARD_HEIGHT - 1 || newCol < 0 || newCol > BOARD_WIDTH - 1)
		{
			System.out.println("Illegal move");
			return false;
		}

		else if (gameBoard.getSlots()[newRow][newCol] == null || gameBoard.getSlots()[newRow][newCol] instanceof RoomSlot)
		{
			System.out.println("Illegal move");
			return false;
		}
		
		else
		{
			for (Player p: players)
			{
				if (p.getSuspectPawn().getPosition() == gameBoard.getSlots()[newRow][newCol])
				{
					System.out.println("Illegal move");
					return false;
				}
			}
		}

		return true;
	
	}
	
	
	private Slot getSecretSlot(int roomNumber, Board gameBoard, ArrayList<Player> players)
	{
		
		switch(roomNumber)
		{
		
			case 1:		return getRoomSlot(7, gameBoard, players);
			
			case 3:		return getRoomSlot(5, gameBoard, players);
					
			case 5: 	return getRoomSlot(3, gameBoard, players);
					
			case 7:		return getRoomSlot(1, gameBoard, players);
					
			default:	return null;
			
		}
	}
	

	private Slot getRoomSlot(int roomNumber, Board gameBoard, ArrayList<Player> players)
	{
		boolean slotOccupied;
		
		ArrayList<RoomSlot> roomSlots = gameBoard.getRoomSlots();
		
		for (RoomSlot rs: roomSlots)
		{
			slotOccupied = false;
			
			if (rs.getRoomNumber() == roomNumber)
			{
				for (Player p: players)
				{
					if (p.getSuspectPawn().getPosition() == gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				if (!slotOccupied)
				{
					return gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()];
				}
			}
		}
		
		return null;
	}
	
	
	
	private Slot getDoorSlot(int roomNumber, Board gameBoard) 
	{
		ArrayList<DoorSlot> doorSlots = gameBoard.getDoorSlots();
		
		for (DoorSlot ds: doorSlots)
		{
			if (ds.getRoomNumber() == roomNumber)
			{
				return gameBoard.getSlots()[ds.getYPosition()][ds.getXPosition()];
			}
		}
		
		return null;
	}	
	
}
