/***************************************************************/
/* RoomButton Class
/* 
/* Represents the GUI button for a room slot
/***************************************************************/

package ie.ucd.cluedo;

import java.awt.Color;


@SuppressWarnings("serial")
public class RoomButton extends Button
{

	// Attributes
	Color defaultColor;
	
	
	// Constructor
	public RoomButton(int row, int col) 
	{

		super(row, col);
		
		defaultColor = Color.BLACK;
		this.setBackground(this.defaultColor);

	}
	
	
	/* Public Methods */
	
	
	// changeColor() method
	public void changeColor(Color newColor)
	{
		this.setBackground(newColor);
	}
	
	
	// resetDefault() method
	public void resetDefault()
	{
		this.setBackground(this.defaultColor);
	}

}
