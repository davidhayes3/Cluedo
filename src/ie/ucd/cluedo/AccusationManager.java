package ie.ucd.cluedo;

import java.util.ArrayList;
import static ie.ucd.cluedo.GameValues.*;

public class AccusationManager 
{
	
	public AccusationManager()
	{

	}
	
	public boolean checkEnvelope(Accusation accusation)
	{
		
		int suspectAccusation = accusation.getSuspect();
		int weaponAccusation = accusation.getWeapon();
		int roomAccusation = accusation.getRoom();
	
		if (suspectAccusation != murderSuspectIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			return false;
		}
		
		else if (weaponAccusation != murderWeaponIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			return false;
		}
		
		else if (roomAccusation != murderRoomIndex)
		{
			System.out.println("Sorry! You're accusation was wrong");
			return false;
		}
		
		else
		{
			System.out.println("You're accusation was correct! Congratulations");
			return true;
		}
		
		
	}
			
}