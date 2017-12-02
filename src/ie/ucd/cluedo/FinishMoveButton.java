package ie.ucd.cluedo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinishMoveButton extends JButton implements ActionListener{
	
	public int playerTurn = 0;
	public int numPlayers;
	public FinishMoveButton(int numPlayers){
		this.numPlayers = numPlayers;
		this.addActionListener(this);
		this.setText("Finish Move");
		this.setBounds(550,250, 100, 50);
	}
	
	public int getPlayerTurn(){
		return playerTurn;	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		playerTurn++;
		
		if(playerTurn==numPlayers){
			playerTurn = 0;
		}
		System.out.println((playerTurn+1));
	}

}
