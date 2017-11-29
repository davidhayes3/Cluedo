package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.MAX_DIES_SCORE;
import static ie.ucd.cluedo.GameValues.MIN_DIES_SCORE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;

public class diceButton extends JButton implements ActionListener{
	public int diceRoll;
	public diceButton() {
		this.setBounds(550,50, 100, 50);
		this.setText("Roll Dice");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		diceRoll = ThreadLocalRandom.current().nextInt(MIN_DIES_SCORE, MAX_DIES_SCORE + 1);
		System.out.println("Dice Rolled"+diceRoll);
	}
	
	public int getDiceRoll(){
		return diceRoll;
		
	}
}
