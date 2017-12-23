/***************************************************************/
/* Board Button Class
/* 
/* Represents a GUI button for a slot in the corridor
/***************************************************************/

package ie.ucd.cluedo;

import java.awt.Color;


@SuppressWarnings("serial")
public class BoardButton extends Button
{

	// Attributes
	Color defaultColor;
	
	
	// Constructor
	public BoardButton(int col, int row) 
	{
		super(col, row);
		
		defaultColor = Color.YELLOW;
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