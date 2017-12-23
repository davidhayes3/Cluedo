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
	
	
	public int getSuspect()
	{
		return suspectHypothesis;
	}
	
	
	public int getWeapon()
	{
		return weaponHypothesis;
	}

	
	public int getRoom()
	{
		return roomHypothesis;
	}
	
}
