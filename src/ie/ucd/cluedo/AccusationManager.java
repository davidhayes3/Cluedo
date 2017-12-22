package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;

import static ie.ucd.cluedo.GameValues.*;

public class AccusationManager 
{
	
	ArrayList<Player> players;
	ArrayList<Card> playerCards;
	
	public AccusationManager(ArrayList<Player> players)
	{
		this.players = players;	
	}
	
	
	
	// Accusation
	public boolean simulateAccusation(int playerTurn, int numPlayers, boolean gameOver)
	{
		boolean winnerAlright;
		int currentRoom = this.players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber();
				
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("\nWhat suspect is in your accusation: " );
		for (int i = 0; i < 6; i++)
		{
			System.out.println("[" + i + "]" + gameList.get(i));
		}
		
		int suspectAccusation = scanner.nextInt();
		
		System.out.printf("\nWhat weapon is in your accusation: " );
		for (int i = 6; i < 12; i++)
		{
			System.out.println("[" + i + "]" + gameList.get(i));
		}
		
		int weaponAccusation = scanner.nextInt();
		
		// Room Accusation
		int roomAccusation = currentRoom + NUM_SUSPECTS + NUM_WEAPONS - 1;
		
		
		Accusation playerAccusation = new Accusation(suspectAccusation, weaponAccusation, roomAccusation);
		
		winnerAlright = checkEnvelope(playerAccusation, playerTurn);
		
		if (winnerAlright)
		{
			gameOver = true;
		}
		
		else
		{
			players.remove(playerTurn);
			
			if (numPlayers == 1)
			{
				System.out.println("There is only one player left\n \n");
				gameOver = true;
			}
		}
		
		return gameOver;

	}
	
	
	
	
	public boolean checkEnvelope(Accusation accusation, int playerTurn)
	{
		
		this.playerCards = players.get(playerTurn).getCards();
		
		int suspectAccusation = accusation.getSuspect();
		int weaponAccusation = accusation.getWeapon();
		int roomAccusation = accusation.getRoom();
	
		if (suspectAccusation != murderSuspectIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			updateNotebooks(players.get(playerTurn), suspectAccusation, weaponAccusation, roomAccusation);
			return false;
		}
		
		else if (weaponAccusation != murderWeaponIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			updateNotebooks(players.get(playerTurn), suspectAccusation, weaponAccusation, roomAccusation);
			return false;
		}
		
		else if (roomAccusation != murderRoomIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			updateNotebooks(players.get(playerTurn), suspectAccusation, weaponAccusation, roomAccusation);
			return false;
		}
		
		else
		{
			System.out.println("You're accusation was correct! Congratulations");
			return true;
		}
				
	}
	
	
	public void updateNotebooks(Player accuser, int suspectAccusation, int weaponAccusation, int roomAccusation)
	{
		
		for (int i = 0; i < players.size(); i++)
		{

			if (i + 1 == accuser.getPlayerNumber())
			{
				continue;
			}
			
			else
			{
				players.get(i).getNotebook().makeEntry(accuser.getSuspectPawn().getName() + " made an accusation with the cards " 
						+ gameList.get(suspectAccusation) + "," + gameList.get(weaponAccusation) + "," + gameList.get(roomAccusation) + ".");
			}
			
		}
	}
			
}