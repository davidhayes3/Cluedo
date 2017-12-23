/***************************************************************/
/* PlayerHand Class
/* 
/* Contains the cards allocated to a player
/***************************************************************/

package ie.ucd.cluedo;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PlayerHand extends JFrame
{

	// Attributes
	JLabel label = new JLabel();
	ArrayList<Card> playerHand;
	
	
	// Constructor
	public PlayerHand(ArrayList<Card> cards)
	{

		this.playerHand = cards;
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(label);
		this.setTitle("Your Cards");
		label.setVerticalAlignment(SwingConstants.TOP);
		
		for(int i = 0; i < this.playerHand.size(); i++)
		{	
			String string = this.playerHand.get(i).getName().toString();
			label.setText(label.getText() + "<html>" + string +"<br/><html>");
		}
		
	}
	
	// showHand() method, makes JFrame visible
	public void showHand()
	{
		this.setVisible(true);
	}
}