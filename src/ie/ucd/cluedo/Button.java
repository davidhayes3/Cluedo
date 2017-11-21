package ie.ucd.cluedo;

import java.awt.Color;
import javax.swing.JButton;

import static ie.ucd.cluedo.GameValues.*;

@SuppressWarnings("serial")
public class Button extends JButton
{

	// Button attributes
	int x;
	int y;

	// Button constructor
	public Button(int x, int y) 
	{
		this.x = x;
		this.y = y;
		this.setBounds(BUTTON_PIXEL_WIDTH*this.x, BUTTON_PIXEL_HEIGHT*this.y, BUTTON_PIXEL_WIDTH, BUTTON_PIXEL_HEIGHT);
		this.setBackground(Color.YELLOW);
	}

}

