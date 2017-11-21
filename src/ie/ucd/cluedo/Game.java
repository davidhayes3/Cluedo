package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;
import static ie.ucd.cluedo.GameValues.*;

public class Game 
{
	// Game attributes
	ArrayList<Player> players = new ArrayList<Player>(6);
	Board gameBoard;
	ArrayList<Card> cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
	int numPlayers;
	boolean gameOver = false;
	int playerTurn = 0;
	
	// Game Constructor
	@SuppressWarnings("resource")
	public Game()
	{
		
		// Main part of game
		System.out.println("NEW GAME\n\n");
			
		// Get number of players
		numPlayers = getNumPlayers();
			
		// Print details of murder
		printMurderDetails();
			
		// Instantiate all players, assign a suspect pawn to each player and place these suspect pawns at a slot
		makePlayers(numPlayers, players);
						
		// Setup Board
		gameBoard = new Board(players);

		// Print details of players
		//printPlayerDetails();
			
		// Create deck of cards excluding the murder cards
		createDeck(cardDeck);
		printDeckInPlayDetails();
			
		// Allocate cards to players
		allocateCards(players, cardDeck);
		printCardAllocation();
			
		// Game play
		while(!gameOver)
		{
			Turn();
		}
		
		System.out.println("\n\nGAME OVER\n\n");	
			
	}
		
	
	
	// Method Implementations
	
	// Get number of players
	@SuppressWarnings("resource")
	public int getNumPlayers()
	{
		int numPlayers;
		
		while (true)
		{
			// Ask user for input until no. of players between 2 and 6 is selected
			System.out.println("Welcome to Cluedo. Please enter the number of players (2-6):");
			Scanner scanner = new Scanner(System.in);
			numPlayers = scanner.nextInt();
				
			if (numPlayers >= MIN_NUM_PLAYERS && numPlayers <= MAX_NUM_PLAYERS)
			{
				break;
			}
			else
			{
				System.out.println("Please enter a number between 2 and 6");
			}
		}
		
		return numPlayers;
	}

	// Create Players
	public void makePlayers(int numPlayers, ArrayList<Player> playerList)
	{		
		for (int i = 0; i < numPlayers; i++)
		{
			playerList.add(new Player(i + 1, new Notebook()));
		}
	}
	
	
	// Turn for a player
	@SuppressWarnings("resource")
	public void Turn()
	{
		System.out.printf("\nTURN: PLAYER %d\n", playerTurn + 1);
		
		Player currentPlayer = this.players.get(playerTurn);
		
		int diceScore = currentPlayer.rollDies();
		System.out.printf("\nYou rolled a score of %d!\n\n", playerTurn + 1, diceScore);
		
		while (true)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.printf( "What do you want to do?\nMove Postion [m],\nEnter Room [r],\nMake Hypothesis [h],\nMake Accusation [a]\nOption: " );
			String playerChoice = scanner.nextLine();
			
			if (playerChoice.equals("m"))
			{
				//pawnMove(currentPlayer);
				break;
			}
			else if (playerChoice.equals("r"))
			{
				//enterRoom()
				gameOver = true;
				break;
			}
			else if (playerChoice.equals("h"))
			{
				//hypothesis();
				gameOver = true;
				break;
			}
			else if (playerChoice.equals("a"))
			{
				//accusation();
				gameOver = true;
				break;
			}
			else
			{
				System.out.println("Please enter a valid option");
			}
		}
		
		players.set(playerTurn, currentPlayer);
		playerTurn = (playerTurn + 1) % players.size();
		System.out.println("\n");
		
	}
	
	// Creates deck of cards with all cards except murder cards
	public void createDeck(ArrayList<Card> cardDeck)
	{
		for (int i = 0; i < NUM_CARDS_IN_DECK; i++)
		{
			if (i == murderSuspectIndex || i == murderWeaponIndex || i == murderRoomIndex)
			{
				continue;
			}
			else
			{
				cardDeck.add(new Card(i));
			}
		}
	}
	
	// Allocates the deck of cards created among all players, giving one at a time to each player starting with player 1, player 2...
	public void allocateCards(ArrayList<Player> players, ArrayList<Card> cardDeck) 
	{
		int playerNumber = 0;
		
		for (int i = 0; i < NUM_CARDS_IN_PLAY; i++)
		{
			players.get(playerNumber++).giveCard(cardDeck.get(i));
			
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
	
	
	// Game Main
	
	public static void main(String[] args) 
	{	
		new Game();	
	}
	
}