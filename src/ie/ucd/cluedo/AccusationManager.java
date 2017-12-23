package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;

import static ie.ucd.cluedo.GameValues.*;

public class AccusationManager 
{
	
	Accusation playerAccusation;
	
	public AccusationManager()
	{

	}
	
	// Accusation
	public boolean simulateAccusation(ArrayList<Player> players, int playerTurn, boolean gameOver)
	{
		boolean winnerAlright;
		int currentRoom = players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber();
				
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
		
		
		this.playerAccusation = new Accusation(suspectAccusation, weaponAccusation, roomAccusation);
		
		winnerAlright = checkEnvelope(players, this.playerAccusation, playerTurn);
		
		if (winnerAlright)
		{
			gameOver = true;
		}
		
		else
		{
			players.remove(playerTurn);
			
			if (players.size() == 1)
			{
				System.out.println("There is only one player left\n \n");
				gameOver = true;
			}
		}
		
		return gameOver;

	}
	
	
	
	
	public boolean checkEnvelope(ArrayList<Player> players, Accusation accusation, int playerTurn)
	{
		
		int suspectAccusation = accusation.getSuspect();
		int weaponAccusation = accusation.getWeapon();
		int roomAccusation = accusation.getRoom();
	
		if (suspectAccusation != murderSuspectIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			updateNotebooks(players, players.get(playerTurn), suspectAccusation, weaponAccusation, roomAccusation);
			return false;
		}
		
		else if (weaponAccusation != murderWeaponIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			updateNotebooks(players, players.get(playerTurn), suspectAccusation, weaponAccusation, roomAccusation);
			return false;
		}
		
		else if (roomAccusation != murderRoomIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			updateNotebooks(players, players.get(playerTurn), suspectAccusation, weaponAccusation, roomAccusation);
			return false;
		}
		
		else
		{
			System.out.println("You're accusation was correct! Congratulations");
			return true;
		}
				
	}
	
	
	public void updateNotebooks(ArrayList<Player> players, Player accuser, int suspectAccusation, int weaponAccusation, int roomAccusation)
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