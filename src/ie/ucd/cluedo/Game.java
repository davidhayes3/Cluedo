package ie.ucd.cluedo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static ie.ucd.cluedo.GameValues.*;

public class Game 
{
	
	// Game attributes
	ArrayList<Player> players;
	Board gameBoard;
	ArrayList<Card> cardDeck;
	ArrayList<Card> fullCardDeck;
	int numPlayers;
	boolean gameOver;
	int playerTurn;
	HypothesisManager hypothesisManager;
	AccusationManager accusationManager;
	
	// Game Constructor
	public Game()
	{
		this.players = new ArrayList<Player>(MAX_NUM_PLAYERS);
		this.cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
		this.fullCardDeck = new ArrayList<Card>(NUM_CARDS_IN_DECK);
		
	    this.playerTurn = 0;
	    this.gameOver = false;
		this.hypothesisManager = new HypothesisManager(this.players);
		this.accusationManager = new AccusationManager();
	}
	
	// Method Implementations
	
	// Get number of players
	@SuppressWarnings("resource")
	public void getNumPlayers()
	{	
		while (true)
		{
			// Ask user for input until no. of players between 2 and 6 is selected
			System.out.println("Welcome to Cluedo. Please enter the number of players (2-6):");
			Scanner scanner = new Scanner(System.in);
			this.numPlayers = scanner.nextInt();
				
			if (this.numPlayers >= MIN_NUM_PLAYERS && this.numPlayers <= MAX_NUM_PLAYERS)
			{
				break;
			}
			else
			{
				System.out.println("Please enter a number between 2 and 6");
			}
		}
	}

	// Create Players
	public void makePlayers()
	{		
		for (int i = 0; i < this.numPlayers; i++)
		{
			this.players.add(new Player(i + 1, new Notebook()));
		}
	}
	
	
	// Create suspect pawns
	
	public void getCharacters()
	{		
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.WHITE, Color.PINK};
		
		Map<Color, String> colorMap = new HashMap<Color, String>();
		colorMap.put(Color.RED, "Red");  
	    colorMap.put(Color.BLUE, "Blue");
	    colorMap.put(Color.GREEN, "Green");
	    colorMap.put(Color.MAGENTA, "Magenta");
	    colorMap.put(Color.WHITE, "White");
	    colorMap.put(Color.PINK, "Pink");
		
		for (int i = 0; i < players.size(); i++)
		{
			while (true)
			{
				// Ask user for input until no. of players between 2 and 6 is selected
				System.out.println("\nPlayer " + (i + 1) + ", please select your character:\n1. MISS SCARLET\n2. PROFESSOR PLUM\n"
						+ "3. MRS. PEACOCK\n4. REVEREND MR. GREEN\n5. COLONEL MUSTARD\n6. MRS. WHITE\n");
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				int playerChoice = scanner.nextInt();
				
				if (playerChoice > 0 && playerChoice <= MAX_NUM_PLAYERS)
				{
					this.players.get(i).giveSuspectPawn(new SuspectPawn(playerChoice, colors[playerChoice-1]));
					System.out.println("Player " + (i+1) + " is " + this.players.get(i).getSuspectPawn().getName() + " (" + colorMap.get(players.get(i).getSuspectPawn().getColor()) + ")");
					break;
				}
				else
				{
					System.out.println("Please enter a number between 1 and " + this.players.size());
				}
			}
		}
	}
	
	// Make board
	public void makeBoard()
	{
		 this.gameBoard = new Board(this.players);
	}
	
	
	// Turn for a player
	public void gameTurns()
	{
		Map<Color, String> colorMap = new HashMap<Color, String>();
		colorMap.put(Color.RED, "Red");  
	    colorMap.put(Color.BLUE, "Blue");
	    colorMap.put(Color.GREEN, "Green");
	    colorMap.put(Color.MAGENTA, "Magenta");
	    colorMap.put(Color.WHITE, "White");
	    colorMap.put(Color.PINK, "Pink");
	    
		boolean hasRolled = false;

		System.out.println("\n\nGame begins");

		while (!gameOver)
		{			
			
			System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" + colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");
			
			hasRolled = (hasRolled) ? afterRollMove(hasRolled) : beforeRollMove(hasRolled);
			
		}
		
		System.out.println("\n\nGAME OVER");
	}
	
	@SuppressWarnings("resource")
	private boolean beforeRollMove(boolean hasRolled)
	{
		int diceScore;
		
		Scanner scanner = new Scanner(System.in);
		System.out.printf("\nWhat do you want to do?\nRoll Dice [r]\nMake Hypothesis [h],"
				+ "\nMake Accusation [a]\nFinish Move [f]\nView Notebook [n]\nOption: " );
		String playerChoice = scanner.nextLine();
		
		switch (playerChoice)
		{
			case "r":	playerMovement();
						return true;
		
			case "h":	hypothesis();

						return false;
		
			case "a":	accusation();
						return false;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
						
			case "n":	System.out.println("Your Notebook:\n" + players.get(playerTurn).getNotebook().getContents());
						return false;
			
			default:	System.out.println("Please enter a valid option");
						return false;
		}
	}
	
	@SuppressWarnings("resource")
	private boolean afterRollMove(boolean hasRolled)
	{

		Scanner scanner = new Scanner(System.in);
		System.out.printf("\nWhat do you want to do?\nMake Hypothesis [h],"
				+ "\nMake Accusation [a]\nFinish Move [f]\nView Notebook [n]\nOption: " );
		String playerChoice = scanner.nextLine();
		
		switch (playerChoice)
		{
			case "h":	hypothesis();
						return true;
		
			case "a":	accusation();
						this.gameOver = true;
						return true;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
						
			case "n":	
						return true;
			
			default:	System.out.println("Please enter a valid option");
						return true;
		}
	}

	private void playerMovement()
	{
		
		int diceScore = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
		
		System.out.printf("\nYour dice score is " + diceScore + "\n");
		
		int movesRemaining = diceScore;

		while (movesRemaining > 0)
		{
			
			System.out.printf("You have " + movesRemaining + " moves remaining.");
			
			Slot currentSlot = this.players.get(this.playerTurn).getSuspectPawn().getPosition();
			
			if (currentSlot instanceof BoardSlot)
			{
				movesRemaining = boardMove(movesRemaining);
			}
			
			else if (currentSlot instanceof RoomSlot)
			{
				
				if (currentSlot.getRoomNumber() == 1 || currentSlot.getRoomNumber() == 3 || currentSlot.getRoomNumber() == 5 || currentSlot.getRoomNumber() == 7)
				{
					movesRemaining = secretRoomMove(movesRemaining);
				}
				
				else
				{
					movesRemaining = normalRoomMove(movesRemaining);
				}
				
			}
			
			else if (currentSlot instanceof DoorSlot)
			{
				movesRemaining = boardMove(movesRemaining);
			}
			
		}
		
		System.out.printf("\nYour moves are finished for this turn");

	}
	

	
	@SuppressWarnings("resource")
	private int normalRoomMove(int movesRemaining)
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
	private int secretRoomMove(int movesRemaining)
	{
		
		String playerChoice;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("\nLeave Room [l]\nStay in room [s]\nAccess Secret Passage [p]\nOption: " );				
		playerChoice = scanner.nextLine();
		
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
	
	@SuppressWarnings("resource")
	private int boardMove(int movesRemaining)
	{
		
		int newCol, newRow;
		boolean canMove;
		String playerChoice;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("\nWhat do you want to do?\nMove Up [u]\nMove Down [d],\nMove Left [l]\nMove Right [r]\nFinish moving [f]\nOption: " );
		playerChoice = scanner.nextLine();	
				
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
	


	private boolean canMove(int newCol, int newRow)
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
	
	
	// Creates deck of cards with all cards except murder cards
	public void createDeck()
	{
		for (int i = 0; i < NUM_CARDS_IN_DECK; i++)
		{
			if (i == murderSuspectIndex || i == murderWeaponIndex || i == murderRoomIndex)
			{
				continue;
			}
			else
			{
				this.cardDeck.add(new Card(i));
			}
		}
	}
	
	
	
	
	
	// Allocates the deck of cards created among all players, giving one at a time to each player starting with player 1, player 2...
	public void allocateCards() 
	{
		int playerNumber = 0;
		
		for (int i = 0; i < NUM_CARDS_IN_PLAY; i++)
		{
			this.players.get(playerNumber++).giveCard(this.cardDeck.get(i));
			
			if (playerNumber == players.size())
			{
				playerNumber = 0;
			}
		}
	}
	
	// Print details of murder for this game
	public void printMurderDetails()
	{
		System.out.println("\nMURDER DETAILS:\n");
		System.out.printf("Suspect: %s\nWeapon: %s\nRoom: %s\n\n", murderSuspect, murderWeapon, murderRoom);
	}	
	
	public void printPlayerDetails()
	{
		System.out.printf("\nPLAYER DETAILS:\n\n");
		
		for (int i = 0; i < players.size(); i++)
		{
			System.out.printf("Player %d\n", players.get(i).getPlayerNumber());
			System.out.printf("Pawn: %s\n\n", players.get(i).getSuspectPawn().getName());
		}
	}
	
	// Print the type and name of cards to be dealt to players
	public void printDeckInPlayDetails()
	{
		System.out.println("\nDECK IN PLAY:\n");
	
		for (int i = 0; i < NUM_CARDS_IN_PLAY; i++)
		{
			System.out.printf("Type: %s, Name: %s\n", cardDeck.get(i).getType(), cardDeck.get(i).getName());
		}
	}
	
	// Print cards allocated to each player
	public void printCardAllocation()
	{
		System.out.printf("\n\nCARD ALLOCATION:\n\n");
	
		for (int i = 0; i < players.size(); i++)
		{
			System.out.printf("Player %d Cards:\n", players.get(i).getPlayerNumber());
		
			for (int n = 0; n < players.get(i).getCards().size(); n++)
			{
				System.out.println(players.get(i).getCards().get(n).getName());
			}
			
			System.out.println("\n");
		}
	}
	
	public void createFullDeck()
	{
		for (int i = 0; i < NUM_CARDS_IN_DECK; i++)
		{

				this.fullCardDeck.add(new Card(i));
		}
	}

	//Method to handle the player hypothesis
		public void hypothesis(){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("What suspect is in your hypothesis: " );
			for (int i = 0; i < 6; i++)
			{
				System.out.println("[" + i + "]" + fullCardDeck.get(i).getName());
			}
			int suspectHypothesis = scanner.nextInt();
			
			System.out.println("What weapon is in your hypothesis: " );
			
			for (int i = 6; i < 12; i++)
			{
				System.out.println("[" + i + "]" + fullCardDeck.get(i).getName());
			}
			int weaponHypothesis = scanner.nextInt();
			
			System.out.println("What Suspect is in your hypothesis: " );
			for (int i = 12; i < NUM_CARDS_IN_DECK; i++)
			{
				System.out.println("[" + i + "]" + fullCardDeck.get(i).getName());
			}
			int roomHypothesis = scanner.nextInt();

			Hypothesis playerHypothesis = new Hypothesis(suspectHypothesis, weaponHypothesis, roomHypothesis);
			hypothesisManager.checkPlayersCards(playerHypothesis, playerTurn);

		}
		
		public void accusation(){
			boolean winnerAlright;
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.printf("\nWhat suspect is in your accusation: " );
			int suspectAccusation = scanner.nextInt();
			System.out.printf("\nWhat weapon is in your accusation: " );
			int weaponAccusation = scanner.nextInt();
			System.out.printf("\nWhat Suspect is in your accusation: " );
			int roomAccusation = scanner.nextInt();

			Accusation playerAccusation = new Accusation(suspectAccusation, weaponAccusation, roomAccusation);
			winnerAlright = accusationManager.checkEnvelope(playerAccusation);
			if(winnerAlright)
			{
				gameOver = true;
			}
			else
			{
				players.remove(playerTurn);
				numPlayers--;
				this.playerTurn = (this.playerTurn) % this.numPlayers;
				if(numPlayers==1){
					System.out.println("There is only one player left\n \n");
					gameOver = true;
				}
			}

		}
}