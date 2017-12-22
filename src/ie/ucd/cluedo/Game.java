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
	HypothesisManager hypothesisManager;
	AccusationManager accusationManager;
	
	// Game Constructor
	public Game()
	{
		this.players = new ArrayList<Player>(MAX_NUM_PLAYERS);
		this.cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
		
	    this.playerTurn = 0;
	    this.gameOver = false;
		
		
	}
	
	
	
	/***************************************************************/
	/*  Public Method Implementations
	/***************************************************************/
	
	// Get number of players
	@SuppressWarnings("resource")
	public void getNumPlayers()
	{	
		// Ask user for input until no. of players between 2 and 6 is selected
		System.out.println("Welcome to Cluedo. Please enter the number of players (2-6):");
		
		while (true)
		{
			
			Scanner scanner = new Scanner(System.in);
			
			while (!scanner.hasNextInt())
			{
				scanner.next();
				System.out.println("Please enter an integer value between 2 and 6 (inclusive):");
			}
			
			this.numPlayers = scanner.nextInt();
				
			if (this.numPlayers >= MIN_NUM_PLAYERS && this.numPlayers <= MAX_NUM_PLAYERS)
			{
				break;
			}
			else
			{
				System.out.println("Please enter an integer value between 2 and 6 (inclusive)");
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
	@SuppressWarnings("resource")
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
			
			// Ask user for input until no. of players between 2 and 6 is selected
			System.out.println("\nPlayer " + (i + 1) + ", please select your character:\n1. MISS SCARLET\n2. PROFESSOR PLUM\n"
					+ "3. MRS. PEACOCK\n4. REVEREND MR. GREEN\n5. COLONEL MUSTARD\n6. MRS. WHITE\n");
			
			while (true)
			{
				
				Scanner scanner = new Scanner(System.in);
				
				while (!scanner.hasNextInt())
				{
					scanner.next();
					System.out.println("Please enter an integer value between 1 and 6 (inclusive): ");
				}
				
				int playerChoice = scanner.nextInt();
				
				if (playerChoice > 0 && playerChoice <= MAX_NUM_PLAYERS)
				{
					this.players.get(i).giveSuspectPawn(new SuspectPawn(playerChoice, colors[playerChoice-1]));
					System.out.println("Player " + (i+1) + " is " + this.players.get(i).getSuspectPawn().getName() + " (" + colorMap.get(players.get(i).getSuspectPawn().getColor()) + ")");
					break;
				}
				else
				{
					System.out.println("Please enter an integer value between 1 and 6 (inclusive): ");
				}
			}
		}
	}
	
	// Make board
	public void makeBoard()
	{
		 this.gameBoard = new Board(this.players);
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
		for(int j = 0; j < numPlayers; j++)
		{
			this.players.get(j).giveHand(new PlayerHand(players.get(j)));
		}
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
		int currentRoom;

		System.out.println("\n\nGame begins");

		while (!gameOver)
		{			
			
			Turn turn = new Turn(this.players, this.playerTurn, this.numPlayers, this.gameBoard, this.gameOver);
			
			currentRoom = this.players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber();
			
			System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" + colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");
			
			if (hasRolled)
			{
				
				if (currentRoom == 0)
				{
					hasRolled = turn.afterRollMove(hasRolled);
				}
				
				else
				{
					hasRolled = turn.afterRollMoveInRoom(hasRolled);
				}
				
			}
			
			else
			{
				
				if (currentRoom == 0)
				{
					hasRolled = turn.beforeRollMove(hasRolled);
				}
				
				else
				{
					hasRolled = turn.beforeRollMoveInRoom(hasRolled);
				}
				
			}
			
			this.playerTurn = turn.getPlayerTurn();
			this.numPlayers = turn.getNumPlayers();
			this.gameOver = turn.getGameOver();
			
		}
	}
	
	
	// Other Methods
	
	
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
}