package ie.ucd.cluedo;

import java.awt.Color;

@SuppressWarnings("serial")
public class RoomButton extends Button
{

	Color defaultColor = Color.BLACK;
	
	public RoomButton(int col, int row) 
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
