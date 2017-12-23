/***************************************************************/
/* DoorButton Class
/* 
/* Represents a GUI button for the slot for the door of a room
/***************************************************************/

package ie.ucd.cluedo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class DoorButton extends Button
{	
	
	// Attributes
	ImageIcon image;

	
	// Constructor
	public DoorButton(int row, int col) 
	{
		super(row, col);	
		
		// Set image of button
		try 
		{
			this.image = new ImageIcon(ImageIO.read(new File("RoomDoor.jpg")));
		} 
		catch (IOException e) 
		{
			System.out.println("Error reading image");
		}
			
		this.setIcon(this.image);
	}
	
	
	/* Public Methods */
	
	
	// changeColor() method
	public void changeColor(Color newColor)
	{
		this.setIcon(null);
		this.setBackground(newColor);
	}
	
	// resetDefault() method
	public void resetDefault()
	{
		this.setIcon(this.image);
	}
	
}

