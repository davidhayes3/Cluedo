package ie.ucd.cluedo;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PlayerHand extends JFrame {

	JLabel label = new JLabel();
	ArrayList<Card> playerHand;
	
	public PlayerHand(Player player)
	{

		playerHand = player.getCards();
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(label);
		this.setTitle("Your Cards");
		label.setVerticalAlignment(SwingConstants.TOP);
		
		for(int i = 0; i< playerHand.size(); i++)
		{	
			String string = playerHand.get(i).getName().toString();
			label.setText(label.getText() + "<html>" + string +"<br/><html>");
		}
		
	}
	
	public void showHand()
	{
		this.setVisible(true);
	}
}