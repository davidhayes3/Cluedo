/***************************************************************/
/* SecretButton Class
/* 
/* Represents the GUI button for a secret passage slot
/***************************************************************/

package ie.ucd.cluedo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class SecretButton extends Button
{	
	
	// Attributes
	ImageIcon image;

	
	// Constructor
	public SecretButton(int row, int col) 
	{

		super(row, col);
			
		// Set image
		try 
		{
			image = new ImageIcon(ImageIO.read(new File("RoomStairs.jpg")));
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
		this.setBackground(newColor);
	}
	
	
	// resetDefault() method
	public void resetDefault()
	{
		this.setIcon(this.image);
	}
}


