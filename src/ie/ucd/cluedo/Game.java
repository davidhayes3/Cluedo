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
	int numPlayers;
	boolean gameOver;
	int playerTurn;
	
	// Game Constructor
	public Game()
	{
		this.players = new ArrayList<Player>(MAX_NUM_PLAYERS);
		
		this.cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
		
		this.cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
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
					System.out.println("Player " + (i+1) + " is " + players.get(i).getSuspectPawn().getName() + " (" + colorMap.get(players.get(i).getSuspectPawn().getColor()) + ")");
					break;
				}
				else
				{
					System.out.println("Please enter a number between 1 and " + players.size());
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
	    
	    this.playerTurn = 0;
	    this.gameOver = false;
		boolean hasRolled = false;

		System.out.println("\n\nGame begins");

		while (!gameOver)
		{			
			
			System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" + colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");
			
			hasRolled = (hasRolled) ? afterRollMove(hasRolled) : beforeRollMove(hasRolled);
			
		}
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
			case "r":	diceScore = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
						playerMove(diceScore, playerTurn);
						return true;
		
			case "h":	
						System.out.printf("\nWhat suspect is in your accusation: " );
						int suspectHypothesis = scanner.nextInt();
						System.out.printf("\nWhat weapon is in your accusation: " );
						int weaponHypothesis = scanner.nextInt();
						System.out.printf("\nWhat Suspect is in your accusation: " );
						int roomHypothesis = scanner.nextInt();
						
						Hypothesis playerHypothesis = new Hypothesis(suspectHypothesis, weaponHypothesis, roomHypothesis);
						playerHypothesis.checkPlayersCards(players, playerTurn);
						return false;
		
			case "a":	// accusation();
						this.gameOver = true;
						return false;
			
			case "f":	this.playerTurn = (this.playerTurn + 1) % this.numPlayers;
						return false;
						
			case "n":	System.out.println("Your Notebook: \n" + players.get(playerTurn).getNotebook().getContents());
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
			case "h":	// hypothesis();
						return true;
		
			case "a":	// accusation();
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

	private void playerMove(int diceScore, int playerTurn)
	{
		System.out.printf("\nYour dice score is " + diceScore + "\n");
		
		int movesRemaining = diceScore;

		while (movesRemaining > 0)
		{
			
			System.out.printf("You have " + movesRemaining + " moves remaining.");		
			
			if (this.players.get(this.playerTurn).getSuspectPawn().getPosition() instanceof DoorSlot)
			{
				movesRemaining = roomMove(movesRemaining);
			}
			
			else
			{
				movesRemaining = normalMove(movesRemaining);
			}	
		}
		
		System.out.printf("\nYour moves are finished for this turn");

	}
	
	@SuppressWarnings({ "resource", "unused" })
	private int roomMove(int movesRemaining)
	{
		String playerChoice;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("\nLeave Room [l]\nAccess Secret Passage [s]\nOption: " );				
		playerChoice = scanner.nextLine();	
		
		return movesRemaining;
	}
	
	@SuppressWarnings("resource")
	private int normalMove(int movesRemaining)
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
						
						return movesRemaining;
			
			case "d":	newRow = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getYPosition() + 1;
						newCol = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getXPosition();
			
						canMove = canMove(newCol, newRow);
					
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
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
						
						return movesRemaining;
				
			case "r":	newRow = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getYPosition();
						newCol = this.players.get(this.playerTurn).getSuspectPawn().getPosition().getXPosition() + 1;

						canMove = canMove(newCol, newRow);
	
						if (canMove)
						{
							players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
							movesRemaining--;
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
		
		boolean slotOccupied = false;
		
		if (newRow < 0 || newRow > BOARD_HEIGHT - 1 || newCol < 0 || newCol > BOARD_WIDTH - 1)
		{
			System.out.println("That position is outside the board");
			return false;
		}
		
		for (Player p: this.players)
		{
			if (p.getSuspectPawn().getPosition() == this.gameBoard.getSlots()[newRow][newCol])
			{
				slotOccupied = true;
			}
		}

		if (this.gameBoard.getSlots()[newRow][newCol] == null)
		{
			System.out.println("Cannot access a room through a wall");
			return false;
		}
		
		else if (slotOccupied)
		{
			System.out.println("Cannot move to a space occupied by another player");
			return false;
		}
		
		else
		{
			return true;
		}
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
	

}