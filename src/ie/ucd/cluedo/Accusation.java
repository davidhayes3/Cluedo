/***************************************************************/ 
/* Accusation Class
/* 
/***************************************************************/

package ie.ucd.cluedo;

public class Accusation 
{	

	// Attributes
	private int suspectHypothesis;
	private int weaponHypothesis;
	private int roomHypothesis;
	
	
	// Constructor
	public Accusation(int suspectHypothesis, int weaponHypothesis, int roomHypothesis)
	{	
		this.suspectHypothesis = suspectHypothesis;
		this.weaponHypothesis = weaponHypothesis;
		this.roomHypothesis = roomHypothesis;	
	}
	
	
	/* Public Methods */
	
	
	// getSuspect() method
	public int getSuspect()
	{
		return this.suspectHypothesis;
	}
	
	
	// getWeapon() method
	public int getWeapon()
	{
		return this.weaponHypothesis;
	}

	
	// getRoom() method
	public int getRoom()
	{
		return this.roomHypothesis;
	}
	
}
