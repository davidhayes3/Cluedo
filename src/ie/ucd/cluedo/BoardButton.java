package ie.ucd.cluedo;

import java.awt.Color;

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