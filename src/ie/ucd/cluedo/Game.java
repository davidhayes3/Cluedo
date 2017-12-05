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
	ArrayList<Player> players = new ArrayList<Player>(6);
	Board gameBoard;
	ArrayList<Card> cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
	int numPlayers;
	boolean gameOver = false;
	int playerTurn;
	
	// Game Constructor
	public Game()
	{
		this.playerTurn = 0;
	}
	
	// Method Implementations
	
	// Make board
	public void makeBoard()
	{
		 this.gameBoard = new Board(this.players);
	}
	
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
		
		int buttonPress = 0;
		int trackButtonPress = 0;
		int numButtonPressed = 0;
		int diceScore = 0;
		boolean gameOver = false;
		
		System.out.println("\n\nGame begins");
		System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" + colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");

		while (!gameOver)
		{
			Thread.yield();			
			buttonPress = gameBoard.detectButtonPress();
			numButtonPressed = gameBoard.getButtonPressed();
			
			
			if (buttonPress - trackButtonPress == 1)
			{	
				
				trackButtonPress++;
				
				if (numButtonPressed == DIES_BUTTON_PRESS)
				{
					diceScore = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
					System.out.println("Dice score: " + diceScore);
					continue;
				}
				
				else if (numButtonPressed == BOARD_BUTTON_PRESS)
				{
					diceScore = gameBoard.movePawn(playerTurn, diceScore);
					continue;
				}
				
				else if (numButtonPressed == END_TURN_BUTTON_PRESS)
				{
					System.out.println(players.get(playerTurn).getSuspectPawn().getName() + " ended their move.");
					playerTurn = (playerTurn + 1) % numPlayers;
					System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" + colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");
				}
				
				else if (numButtonPressed == NOTEBOOK_BUTTON_PRESS)
				{
					gameBoard.showNoteBook(playerTurn);
					
				}
				else if (numButtonPressed == DOOR_BUTTON_PRESS)
				{
					diceScore = gameBoard.movePawn(playerTurn, diceScore);
					
				}
			}
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