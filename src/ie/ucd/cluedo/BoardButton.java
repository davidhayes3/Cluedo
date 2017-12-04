package ie.ucd.cluedo;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoardButton extends JButton{

	public int x;
	public int y;
	public static int col;
	public static int row;
	
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
	public void reset(){
	this.setBackground(Color.yellow);
	}
	}
