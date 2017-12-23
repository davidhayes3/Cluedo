package ie.ucd.cluedo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class SecretButton extends Button
{	
	
	ImageIcon image;

	public SecretButton(int col, int row) 
	{

		super(col, row);
			
		try 
		{
			image = new ImageIcon(ImageIO.read(new File("RoomStairs.jpg")));
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


