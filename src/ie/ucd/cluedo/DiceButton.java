package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.MAX_DIES_SCORE;
import static ie.ucd.cluedo.GameValues.MIN_DIES_SCORE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;

public class DiceButton extends JButton{
	
	public DiceButton() {
		this.setBounds(550,50, 100, 50);
		this.setText("Roll Dice");
		//this.addActionListener(this);
	}

}
