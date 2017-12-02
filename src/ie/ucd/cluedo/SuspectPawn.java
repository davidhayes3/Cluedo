package ie.ucd.cluedo;

import java.awt.Color;

public class SuspectPawn extends Pawn  
{
	Slot pawnPosition;
	public int playerX;
	public int playerY;
	public BoardButton suspectButton;
	public Color pawnColour;
	// SuspectPawn constructor
	public SuspectPawn(int pawnIndex, Slot pawnPosition, Color pawnColour)
	{
		super(pawnIndex, pawnPosition);
		this.pawnPosition = pawnPosition;
		this.pawnColour = pawnColour;
	}
	
	// Returns slot where the suspect pawn is currently located
	public Slot getPosition()
	{
		return this.pawnPosition;
	}
	
	public void movePawn(BoardButton newPosition)
	{
		this.suspectButton = newPosition;
		this.playerX = newPosition.getXpos();
		this.playerY = newPosition.getYpos();
	}
	
	public int getX(){
		return this.playerX;
	}
	
	public int getY(){
		return this.playerY;
	}
	public BoardButton getSuspectButton(){
		return suspectButton;
		
	}
	// Changes the slot occupied by the suspect pawn
	public void movePosition(Slot newPosition)
	{
		this.pawnPosition = newPosition;
	}
	
	public Color getColor(){
		return pawnColour;
		
	}
}
