package ie.ucd.cluedo;

import java.util.ArrayList;
import static ie.ucd.cluedo.GameValues.*;

public class AccusationManager 
{
	
	ArrayList<Player> players;
	ArrayList<Card> playerCards;
	
	public AccusationManager(ArrayList<Player> players)
	{
		this.players = players;	
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