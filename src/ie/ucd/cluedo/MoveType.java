package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.BOARD_HEIGHT;
import static ie.ucd.cluedo.GameValues.BOARD_WIDTH;

import java.util.ArrayList;
import java.util.Scanner;

public class MoveType 
{
	// Attributes
	ArrayList<Player> players;
	int playerTurn;
	int numPlayers;
	Board gameBoard;
	
	
	// Constructor
	public MoveType(ArrayList<Player> players, int playerTurn, int numPlayers, Board gameBoard) 
	{
		this.players = players;
		this.playerTurn = playerTurn;
		this.numPlayers = numPlayers;
		this.gameBoard = gameBoard;
	}
	
	
	@SuppressWarnings("resource")
	public int normalRoomMove(int movesRemaining)
	{
		
		String playerChoice;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("\nLeave Room [l]\nStay in room [s]\nOption: " );				
		playerChoice = scanner.nextLine();
		
		switch (playerChoice)
		{
		
			case "l":	Slot doorSlot = getDoorSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber());
			
						players.get(playerTurn).getSuspectPawn().movePosition(doorSlot);
	
						break;
						
			
			case "s":	movesRemaining = 0;
						break;
			
			default:	break;
		
		}
		
		return movesRemaining;
	}
	
	@SuppressWarnings({ "resource" })
	public int secretRoomMove(int movesRemaining, String playerChoice)
	{
		

		switch (playerChoice)
		{
		
			case "l":	Slot doorSlot = getDoorSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber());
				
						players.get(playerTurn).getSuspectPawn().movePosition(doorSlot);
				
						break;
						
			case "s":	movesRemaining = 0;
						break;
						
			case "p":	Slot secretSlot = getSecretSlot(players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber());
				
						players.get(playerTurn).getSuspectPawn().movePosition(secretSlot);
			
						movesRemaining--;
			
			default:	break;
		
		}
		
		return movesRemaining;
	}
	
	
	
	@SuppressWarnings("resource")
	public int boardMove(int movesRemaining, String playerChoice)
	{
		
		int newCol, newRow;
		boolean canMove;
				
		switch (playerChoice)
		{
			
			case "u":	newRow = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getYPosition() - 1;
						newCol = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getXPosition();
						
						canMove = canMove(newCol, newRow);
					
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(this.playerTurn).getSuspectPawn().getPosition().getRoomNumber());
							
							players.get(this.playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
			
			case "d":	newRow = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getYPosition() + 1;
						newCol = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getXPosition();
			
						canMove = canMove(newCol, newRow);
					
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(this.playerTurn).getSuspectPawn().getPosition().getRoomNumber());
							
							players.get(this.playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
			
			case "l":	newRow = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getYPosition();
						newCol = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getXPosition() - 1;
	
						canMove = canMove(newCol, newRow);
						
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(this.playerTurn).getSuspectPawn().getPosition().getRoomNumber());
							
							players.get(this.playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
				
			case "r":	newRow = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getYPosition();
						newCol = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getXPosition() + 1;

						canMove = canMove(newCol, newRow);
	
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
						}
						
						if (players.get(playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
						{
							Slot roomSlot = getRoomSlot(players.get(this.playerTurn).getSuspectPawn().getPosition().getRoomNumber());
							
							players.get(this.playerTurn).getSuspectPawn().movePosition(roomSlot);
						}
						
						return movesRemaining;
			
			case "f":	movesRemaining = 0;
						return movesRemaining;
				
			default:	System.out.println("Please enter a valid option");
						return movesRemaining;
							
		}
	}
	


	boolean canMove(int newCol, int newRow)
	{
		
		if (newRow < 0 || newRow > BOARD_HEIGHT - 1 || newCol < 0 || newCol > BOARD_WIDTH - 1)
		{
			System.out.println("That position is outside the board");
			return false;
		}

		else if (this.gameBoard.getSlots()[newRow][newCol] == null || this.gameBoard.getSlots()[newRow][newCol] instanceof RoomSlot)
		{
			System.out.println("Cannot access a room through a wall");
			return false;
		}
		
		else
		{
			for (Player p: this.players)
			{
				if (p.getSuspectPawn().getPosition() == this.gameBoard.getSlots()[newRow][newCol])
				{
					System.out.println("Cannot move to a space occupied by another player");
					return false;
				}
			}
		}

		return true;
	
	}
	
	
	
	
	private Slot getRoomSlot(int roomNumber)
	{
		boolean slotOccupied;
		
		ArrayList<RoomSlot> roomSlots = this.gameBoard.getRoomSlots();
		
		for (RoomSlot rs: roomSlots)
		{
			slotOccupied = false;
			
			if (rs.getRoomNumber() == roomNumber)
			{
				for (Player p: this.players)
				{
					if (p.getSuspectPawn().getPosition() == this.gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				if (!slotOccupied)
				{
					return this.gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()];
				}
			}
		}
		
		return null;
	}
	
	
	private Slot getSecretSlot(int roomNumber)
	{
		switch(roomNumber)
		{
		
			case 1:		return getRoomSlot(7);
			
			case 3:		return getRoomSlot(5);
					
			case 5: 	return getRoomSlot(3);
					
			case 7:		return getRoomSlot(1);
					
			default:	return null;
			
		}
	}
	
	
	private Slot getDoorSlot(int roomNumber) 
	{
		ArrayList<DoorSlot> doorSlots = this.gameBoard.getDoorSlots();
		
		for (DoorSlot ds: doorSlots)
		{
			if (ds.getRoomNumber() == roomNumber)
			{
				return this.gameBoard.getSlots()[ds.getYPosition()][ds.getXPosition()];
			}
		}
		
		return null;
	}
	
	
	
}
