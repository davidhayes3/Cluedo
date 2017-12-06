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
	    
	    int playerTurn = 0;
	    boolean gameOver = false;

		System.out.println("\n\nGame begins");

		while (!gameOver)
		{			
			int diceScore = 0;
			
			System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" + colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");
			
			Scanner scanner = new Scanner(System.in);
			System.out.printf("\nWhat do you want to do?\nRoll Dice [r]\nMake Hypothesis [h],"
					+ "\nMake Accusation [a]\nFinish Move [f]\nOption: " );
			String playerChoice = scanner.nextLine();
			
			switch (playerChoice)
			{
				case "r":	diceScore = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
							playerMove(diceScore, playerTurn);
							break;
			
				case "h":	//hypothesis();
							break;
			
				case "a":	//accusation();
							gameOver = true;
							break;
				
				case "f":	playerTurn = (playerTurn + 1) % this.numPlayers;
							break;
				
				default:	System.out.println("Please enter a valid option");
							break;
			}
		}
	}

	private void playerMove(int diceScore, int playerTurn)
	{
		System.out.printf("\nYour dice score is " + diceScore + "\n");
		
		int movesRemaining = diceScore;
		int newCol, newRow;

		while (movesRemaining > 0)
		{
			System.out.printf("You have " + movesRemaining + " moves remaining.");		
				
			Scanner scanner = new Scanner(System.in);
			System.out.printf("\nWhat do you want to do?\nMove Up [u]\nMove Down [d],\nMove Left [l]\nMove Right [r]\nFinish moving [f]\nOption: " );
			String playerChoice = scanner.nextLine();				
				
			switch (playerChoice)
			{
				case "u":	newRow = this.players.get(playerTurn).getSuspectPawn().getPosition().getYPosition() - 1;
							newCol = this.players.get(playerTurn).getSuspectPawn().getPosition().getXPosition();
				
							if (newRow < 0 || newRow > 23 || newCol < 0 || newCol > 23)
							{
								System.out.println("That position is outside the board");
								continue;
							}
							
							else if (this.gameBoard.getSlots()[newRow][newCol] == null)
							{
								System.out.println("Cannot access a room through a wall");
								continue;
							}
						
							else
							{
								players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
								movesRemaining--;
							}
								
							break;
				
				case "d":	newRow = this.players.get(playerTurn).getSuspectPawn().getPosition().getYPosition() + 1;
							newCol = this.players.get(playerTurn).getSuspectPawn().getPosition().getXPosition();
				
							if (newRow < 0 || newRow > 23 || newCol < 0 || newCol > 23)
							{
								System.out.println("That position is outside the board");
								continue;
							}
					
							else if (this.gameBoard.getSlots()[newRow][newCol] == null)
							{
								System.out.println("Cannot access a room through a wall");
								continue;
							}
			
							else
							{
								players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
								movesRemaining--;
							}
								
							break;
				
				case "l":	newRow = this.players.get(playerTurn).getSuspectPawn().getPosition().getYPosition();
							newCol = this.players.get(playerTurn).getSuspectPawn().getPosition().getXPosition() - 1;
		
							if (newRow < 0 || newRow > 23 || newCol < 0 || newCol > 23)
							{
								System.out.println("That position is outside the board");
								continue;
							}
		
							else if (this.gameBoard.getSlots()[newRow][newCol] == null)
							{
								System.out.println("Cannot access a room through a wall");
								continue;
							}

							else
							{
								players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
								movesRemaining--;
							}
					
							break;								
					
				case "r":	newRow = this.players.get(playerTurn).getSuspectPawn().getPosition().getYPosition();
							newCol = this.players.get(playerTurn).getSuspectPawn().getPosition().getXPosition() + 1;

							if (newRow < 0 || newRow > 23 || newCol < 0 || newCol > 23)
							{
								System.out.println("That position is outside the board");
								continue;
							}

							else if (this.gameBoard.getSlots()[newRow][newCol] == null)
							{
								System.out.println("Cannot access a room through a wall");
								continue;
							}

							else
							{
								players.get(playerTurn).getSuspectPawn().movePosition(this.gameBoard.getSlots()[newRow][newCol]);
								movesRemaining--;
							}
		
							break;
				
				case "f":	movesRemaining = 0;
							break;
					
				default:	System.out.println("Please enter a valid option");
							break;
								
			}
		}
		
		System.out.printf("\nYou have used all your moves");

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