package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton
{
	
	public Button(int col, int row) 
	{
		
		this.setBounds( BUTTON_PIXEL_WIDTH * row, BUTTON_PIXEL_HEIGHT * col, BUTTON_PIXEL_WIDTH, BUTTON_PIXEL_HEIGHT );
				
	}
	
	public void changeColor(Color newColor)
	{
		this.setBackground(newColor);
	}
	
	public void resetDefaultColor()
	{
		this.setBackground(Color.yellow);
	}

}