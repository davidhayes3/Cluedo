/***************************************************************/
/* Game Class
/* 
/* Implements all main actions in game of Cluedo
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Game 
{
	
	// Attributes
	int playerTurn;
	boolean gameOver;
	
	
	// Constructor
	public Game()
	{
		
	    this.playerTurn = 0;
	    this.gameOver = false;
			
	}
	
	
	/* Public Methods */
	
	
	// getNumPlayers() Method
	// Purpose: Repeatedly asks users for input until an integer between 2 and 6 is chosen, and then returns this value
	@SuppressWarnings("resource")
	public int getNumPlayers()
	{	

		int numPlayers;
		
		System.out.println("Welcome to Cluedo. Please enter the number of players (2-6):");
		
		// Ask user for input until number of players between 2 and 6 is selected
		while (true)
		{
			
			Scanner scanner = new Scanner(System.in);
			
			// Wait until an integer value is input
			while (!scanner.hasNextInt())
			{
				scanner.next();
				System.out.println("Please enter an integer value between 2 and 6 (inclusive):");
			}
			
			numPlayers = scanner.nextInt();
				
			if (numPlayers >= MIN_NUM_PLAYERS && numPlayers <= MAX_NUM_PLAYERS)
			{
				break;
			}
			
			else
			{
				System.out.println("Please enter an integer value between 2 and 6 (inclusive)");
			}
		}
		
		return numPlayers;
		
	}

	
	// makePlayers() Method
	// Purpose: Creates ArrayList containing all the players of the current game
	public ArrayList<Player> makePlayers(int numPlayers, ArrayList<Player> players)
	{				
		
		for (int i = 0; i < numPlayers; i++)
		{
			// Create player with player number and notebook
			players.add(new Player(i + 1, new Notebook()));
		}
		
		return players;
		
	}
	
	
	// makeBoard() Method
	// Purpose: Instantiates board object and returns this object
	public Board makeBoard()
	{
		 
		Board gameBoard = new Board();
		
		return gameBoard;
		
	}
	
	
	// getCharacters() Method
	// Purpose: Asks each player one by one which character they want, and assigns the suspectPawn of that character to the player
	@SuppressWarnings("resource")
	public void getCharacters(ArrayList<Player> players, Board gameBoard)
	{		
		
		// HashMap used to print color of player's suspectPawn
		Map<Color, String> colorMap = new HashMap<Color, String>();
		colorMap.put(Color.RED, "Red");  
	    colorMap.put(Color.BLUE, "Blue");
	    colorMap.put(Color.GREEN, "Green");
	    colorMap.put(Color.MAGENTA, "Magenta");
	    colorMap.put(Color.WHITE, "White");
	    colorMap.put(Color.PINK, "Pink");
	    
	    ArrayList<SuspectPawn> suspectPawns = gameBoard.getSuspectPawns();
	  
	    // Ask all players, starting with player 1
		for (int i = 0; i < players.size(); i++)
		{	
			
			// Ask player for character they would like to play as
			System.out.println("\nPlayer " + players.get(i).getPlayerNumber() + ", please select your character:\n1) MISS SCARLET\n2) PROFESSOR PLUM\n"
					+ "3) MRS. PEACOCK\n4) REVEREND MR. GREEN\n5) COLONEL MUSTARD\n6) MRS. WHITE\n");

			// Wait until valid integer input
			while (true)
			{
				
				Scanner scanner = new Scanner(System.in);
				
				// Wait until integer input
				while (!scanner.hasNextInt())
				{
					scanner.next();
					System.out.println("Please enter an integer value between 1 and 6 (inclusive): ");
				}
				
				int playerChoice = scanner.nextInt();
				
				if (playerChoice > 0 && playerChoice <= MAX_NUM_PLAYERS)
				{
					// If valid integer input, give corresponding suspectPawn to player
					System.out.println("xxxxxxx player choice is " + playerChoice);
					
					for (int j = 0; j < suspectPawns.size(); j++)
					{
						if (suspectPawns.get(j).getName() == gameList.get(playerChoice - 1))
						{
							players.get(i).giveSuspectPawn(suspectPawns.get(j));
							suspectPawns.remove(j);
						}
					}
					
					System.out.println("Player " + players.get(i).getPlayerNumber() + " is " + players.get(i).getSuspectPawn().getName() 
							+ " (" + colorMap.get(players.get(i).getSuspectPawn().getColor()) + ")");
					
					break;
				}
				
				else
				{
					System.out.println("Please enter an integer value between 1 and 6 (inclusive): ");
				}			
			
			}
			
		}
	
	}
	
	// createDeck() Method
	// Purpose: Creates the card deck to be distributed among players i.e all cards minus murder cards
	public ArrayList<Card> createDeck()
	{
		
		ArrayList<Card> cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
		
		for (int i = 0; i < NUM_CARDS_IN_DECK; i++)
		{
			
			if (i == murderSuspectIndex || i == murderWeaponIndex || i == murderRoomIndex)
			{
				continue;
			}
			
			else
			{
				// If not one of murder card, add to deck
				cardDeck.add(new Card(i));
			}
			
		}
		
		return cardDeck;		
	}
	
	
	// allocateCards() Method
	// Purpose: Allocates the deck of cards created among all players, one at a time to each player and allocates a hand object to each player
	public void allocateCards(ArrayList<Player> players, ArrayList<Card> cardDeck) 
	{
		
		int playerNumber = 0;
		
		// Allocate cards one by one to each player
		for (int i = 0; i < NUM_CARDS_IN_PLAY; i++)
		{
		
			players.get(playerNumber++).giveCard(cardDeck.get(i));
			
			if (playerNumber == players.size())
			{
				playerNumber = 0;
			}
			
		}
		
		// Create PlayerHand for each players
		for(int j = 0; j < players.size(); j++)
		{		
			players.get(j).giveHand(new PlayerHand(players.get(j).getCards()));	
		}
		
	}
	
	
	// gameTurns() Method
	// Purpose: Simulates the turns of the game. The options available to a player vary depending 
	// on where they are on board and if they have already rolled
	@SuppressWarnings("resource")
	public void gameTurns(ArrayList<Player> players, Board gameBoard)
	{
		
		// HashMap to print color of player
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

		// Only break from loop if true accusation is made or only one player left
		while (!gameOver)
		{			
			
			// Instantiate Turn object
			Turn turn = new Turn(this.playerTurn, this.gameOver);
			
			currentRoom = players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber();
			
			// Print details of current turn
			System.out.println("\n" + players.get(playerTurn).getSuspectPawn().getName() + "'s turn (" 
					+ colorMap.get(players.get(playerTurn).getSuspectPawn().getColor()) + ")");
			
			// Vary choices available to player depending on their current circumstances in game
			if (hasRolled)
			{
				
				// If not in room and have already rolled this turn
				if (currentRoom == 0)
				{	
					Scanner scanner = new Scanner(System.in);
					System.out.printf("\nWhat do you want to do?\nView Cards [c]\nView Notebook [n]\nFinish Move [f]\nOption: ");
					String playerChoice = scanner.nextLine();
					hasRolled = turn.afterRollMove(players, gameBoard, hasRolled, playerChoice);
				}
				
				// If in room and have already moved this turn
				else
				{
					Scanner scanner = new Scanner(System.in);
					System.out.printf("\nWhat do you want to do?\nMake Hypothesis [h]\nMake Accusation [a]\nView Cards [c]\nView Notebook [n]\nFinish Move [f]\nOption: ");
					String playerChoice = scanner.nextLine();
					hasRolled = turn.afterRollMoveInRoom(players, gameBoard, hasRolled, playerChoice);	
				}
				
			}
			
			else
			{
				
				// If not in room and have not already moved this turn
				if (currentRoom == 0)
				{			
					Scanner scanner = new Scanner(System.in);
					System.out.printf("\nWhat do you want to do?\nRoll Dice [r]\nView Cards [c]\nView Notebook [n]\nFinish Move [f]\nOption: " );
					String playerChoice = scanner.nextLine();
					hasRolled = turn.beforeRollMove(players, gameBoard, hasRolled, playerChoice);	
				}
				
				// If in room and have not already moved this turn
				else
				{
					Scanner scanner = new Scanner(System.in);
					System.out.printf("\nWhat do you want to do?\nRoll Dice [r]\nMake Hypothesis [h]\nMake Accusation [a]\nView Cards [c]\nView Notebook [n]\nFinish Move [f]\nOption: ");
					String playerChoice = scanner.nextLine();
					hasRolled = turn.beforeRollMoveInRoom(players, gameBoard, hasRolled, playerChoice);				
				}
				
			}
			
			// Update playerTurn and gameOver based on events of this turn
			this.playerTurn = turn.getPlayerTurn();
			this.gameOver = turn.getGameOver();
		}
	
	}
	
}
	