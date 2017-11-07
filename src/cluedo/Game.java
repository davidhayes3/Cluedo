package cluedo;

import java.util.ArrayList;
import java.util.Scanner;
import static cluedo.GameValues.*;

public class Game 
{
	// Game attributes
	ArrayList<Slot> startPositions = new ArrayList<Slot>(NUM_PAWNS);
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Card> cardDeck = new ArrayList<Card>(NUM_CARDS_IN_PLAY);
	ArrayList<WeaponPawn> weaponPawns = new ArrayList<WeaponPawn>(NUM_WEAPONS);
	
	
	// Game Constructor
	public Game()
	{
		
		// Get number of players
		int numPlayers = getNumPlayers();
		
		// Print details of murder for this game
		System.out.println("\nMurder Details:");
		System.out.printf("Suspect: %s\nWeapon: %s\nRoom: %s\n", murderSuspect, murderWeapon, murderRoom);
		System.out.println("\n");
		
		// *** Setup Board ***
		// Creates slots of the board 
		makeSlots(startPositions);
		
		// Print co-ordinates of slots
		System.out.println("Possible Start Positions:");
		for (int i = 0; i < NUM_PAWNS; i++)
		{
			System.out.printf("(%d, %d)\n", startPositions.get(i).getXPosition(), startPositions.get(i).getYPosition());
		}
		
		System.out.println("\n\n");
		
		// Instantiate all players, assign a suspect pawn to each player and place these suspect pawns at a slot
		makePlayers(numPlayers, players, startPositions);
		
		// Print the die roll scores of all players
		
		System.out.printf("Players:\n\n");
		
		for (int i = 0; i < numPlayers; i++)
		{
			System.out.printf("Player %d\n", players.get(i).getPlayerNumber());
			System.out.printf("Pawn: %s\n", players.get(i).getSuspectPawn().getName());
			System.out.printf("Dice Score: %d\n", players.get(i).rollDies());
			System.out.printf("Pawn Location: (%d, %d)\n", players.get(i).getPosition().getXPosition(), players.get(i).getPosition().getYPosition());
			System.out.println("\n");
		}
		
		// Create deck of cards excluding the murder cards
		createDeck(cardDeck);
		
		// Print the type and name of cards to be dealt to players
		System.out.println("Deck in Play:");
		
		for (int i = 0; i < NUM_CARDS_IN_PLAY; i++)
		{
			System.out.printf("Type: %s, Name: %s\n", cardDeck.get(i).getType(), cardDeck.get(i).getName());
		}
		
		System.out.println("\n\n");
		
		// Allocate cards to players
		allocateCards(numPlayers, players, cardDeck);
		
		// Print cards allocated to each player
		System.out.printf("Cards Allocation:\n\n");
		
		for (int i = 0; i < numPlayers; i++)
		{
			System.out.printf("Player %d Cards:\n", players.get(i).getPlayerNumber());
			
			for (int n = 0; n < players.get(i).getCards().size(); n++)
			{
				System.out.println(players.get(i).getCards().get(n).getName());
			}
			
			System.out.println("\n");
		}
		
		// Create Pawns
 		makeWeaponPawns(weaponPawns, startPositions);
 		
 		// Print weapon pawns
 		System.out.printf("Weapon Pawns:\n");
 		
 		for (int i = 0; i < NUM_WEAPONS; i++)
 		{
 			System.out.printf("Type: %s, Name: %s\n", weaponPawns.get(i).getType(), weaponPawns.get(i).getName());
 		}
 		
 		// Test player/notebook methods
 		System.out.printf("\n\nNotebook:\n\n");
 		
 		System.out.printf("First Inspection: %s\n", players.get(0).inspectNotebook());
 		players.get(0).updateNotebook("I formulated the hypothesis that Z made the murder in the kitchen with the gun.");
 		System.out.printf("Second Inspection: %s", players.get(0).inspectNotebook());

	}
	
	
	

	
	
	
	
	// Get number of players
	public int getNumPlayers()
	{
		int numPlayers;
		
		while (true)
		{
			// Ask user for input until no. of players between 2 and 6 is selected
			System.out.println("Welcome to Cluedo. Please enter the number of players (2-6):");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			numPlayers = sc.nextInt();
				
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
		
	// Define locations where pawns start
	public void makeSlots(ArrayList<Slot> startPositions)
	{
		for (int i = 0; i < NUM_PAWNS; i++)
		{
			startPositions.add(new Slot(i, i));
		}
	}
	
	// Create Players
	public void makePlayers(int numPlayers, ArrayList<Player> playerList, ArrayList<Slot> startPositions)
	{		
		for (int i = 0; i < numPlayers; i++)
		{
			playerList.add(new Player(i + 1, new SuspectPawn(i, startPositions.get(i)), new Notebook()));
		}
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
	public void allocateCards(int numPlayers, ArrayList<Player> players, ArrayList<Card> cardDeck) 
	{
		int playerNumber = 0;
		
		for (int i = 0; i < NUM_CARDS_IN_PLAY; i++)
		{
			players.get(playerNumber++).giveCard(cardDeck.get(i));
			
			if (playerNumber == numPlayers)
			{
				playerNumber = 0;
			}
		}
	}
	
	// Instantiates the weapon pawns for the game and stores in an ArrayList
	public void makeWeaponPawns(ArrayList<WeaponPawn> weaponPawnList, ArrayList<Slot> startPositions)
	{		
		// Create Weapon Pawns
		for (int i = NUM_SUSPECTS; i < NUM_PAWNS; i++)
		{
			weaponPawnList.add(new WeaponPawn(i, startPositions.get(i))); 
		}
	}
	
}