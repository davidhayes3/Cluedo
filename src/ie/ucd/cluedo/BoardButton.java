package ie.ucd.cluedo;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoardButton extends JButton{

	int x;
	int y;
	int col;
	int row;
	public BoardButton(int col, int row) {
		this.x = 21*col;
		this.y = 21*row;
		this.col = col;
		this.row = row;
		this.setBounds(x,y, 21, 21);
		this.setBackground(Color.YELLOW);
	}
	
	public int getXpos(){
		return x/21;
		
	}
	public int getYpos(){
		return y/21;
		
	}
	}
