package ie.ucd.cluedo;

import java.util.ArrayList;
import static ie.ucd.cluedo.GameValues.*;
public class AccusationManager {

private ArrayList<Player> players;
	
	public AccusationManager(){
	
	}
	
	public boolean checkEnvelope(Accusation accusation)
	{
		
		boolean winnerAlright = false;
		int suspectAccusation = accusation.getSuspect();
		int weaponAccusation = accusation.getWeapon();
		int roomAccusation = accusation.getRoom();
		
		ArrayList<Card> playerCards;
	
		if(murderSuspectIndex != suspectAccusation)
		{
			System.out.println("The murderer  is "+ murderSuspectIndex);
			System.out.println("Sorry! You're accusation was wrong");
			return winnerAlright;
		}
		else if(murderWeaponIndex != weaponAccusation)
		{
			System.out.println("The murder weapon is "+ murderWeaponIndex);
			System.out.println("Sorry! You're accusation was wrong");
			return winnerAlright;
		}
		else if(murderRoomIndex != roomAccusation)
		{
			System.out.println("The murder took place in "+ murderRoomIndex);
			System.out.println("Sorry! You're accusation was wrong");
			return winnerAlright;
		}
		else
		{
			System.out.println("You're accusation was correct! Congratulations");
			winnerAlright = true;
			return winnerAlright;
		}
		
		
	}
			
}