package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.BOARD_HEIGHT;
import static ie.ucd.cluedo.GameValues.BOARD_WIDTH;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class DoorButton extends Button
{	
	
	ImageIcon image;

	public DoorButton(int col, int row) 
	{

		super(col, row);
			
		try 
		{
			this.image = new ImageIcon(ImageIO.read(new File("RoomDoor.jpg")));
		} 
		catch (IOException e) 
		{
			System.out.println("Error");
		}
			
		this.setIcon(this.image);
	
	}
	
	public void changeColor(Color newColor)
	{
		this.setIcon(null);
		this.setBackground(newColor);
	}
	
	public void resetDefault()
	{
		this.setIcon(this.image);
	}
}

