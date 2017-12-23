/***************************************************************/
/* Button Class
/* 
/* Abstract Class representing a GUI button
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.awt.Color;

import javax.swing.JButton;


@SuppressWarnings("serial")
public abstract class Button extends JButton
{
	
	// Constructor
	public Button(int row, int col) 
	{
		this.setBounds( BUTTON_PIXEL_WIDTH * col, BUTTON_PIXEL_HEIGHT * row, BUTTON_PIXEL_WIDTH, BUTTON_PIXEL_HEIGHT );			
	}
	
	
	/* Public Methods */
	
	
	// changeColor() method
	public void changeColor(Color newColor)
	{
		this.setBackground(newColor);
	}
	
	// Abstract method to reset default settings of button
	public abstract void resetDefault();

}