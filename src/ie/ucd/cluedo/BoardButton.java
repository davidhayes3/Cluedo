package ie.ucd.cluedo;


import static ie.ucd.cluedo.GameValues.BOARD_HEIGHT;
import static ie.ucd.cluedo.GameValues.BOARD_WIDTH;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoardButton extends Button
{

	Color defaultColor = Color.YELLOW;
	
	public BoardButton(int col, int row) 
	{

		super(col, row);
		
		this.setBackground(this.defaultColor);

	}
	
	public void changeColor(Color newColor)
	{
		this.setBackground(newColor);
	}
	
	public void resetDefault()
	{
		this.setBackground(this.defaultColor);
	}

}