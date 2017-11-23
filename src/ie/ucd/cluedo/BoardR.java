package ie.ucd.cluedo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ie.ucd.cluedo.DoorButton;
import ie.ucd.cluedo.SecretDoor;

public class BoardR implements ActionListener {
	
	BoardButton[][] buttons = new BoardButton[24][24]; 
	private ImageIcon image;
	private int x = 0;
	private int j = 0;
	public int x_position;
	public int y_position;
	

	
	public BoardR() {
		
		gui2();
	}

	
	public void gui2() {
		

		JFrame Board = new JFrame("Welcome to our game of Cluedo");
		Board.setLayout(null);

		try {
			image = new ImageIcon(ImageIO.read(new File("C:\\Users\\Admin\\Documents\\5th Year\\Software Engineering\\CluedoBoard3.png")));
		} catch (IOException e) {
			System.out.println("Error");
		}

		
		JLabel boardLabel = new JLabel(image);
		boardLabel.setBounds(0, 0, 504,504);
		
		int row;
		int col;

		for(col = 6; col<8; col+= 1){
			for(row = 0; row<=8; row+= 1){
				buttons[col][row] = new BoardButton(col, row);
				Board.add(buttons[col][row]);
				}
			}
		
		buttons[9][0] = new BoardButton(9, 0);
		Board.add(buttons[9][0]);
		
		//Doors and Secret Passageway's
		Board.add(new DoorButton(4, 5));
		Board.add(new DoorButton(8, 4));
		Board.add(new DoorButton(9, 6));
		Board.add(new DoorButton(14, 6));
		Board.add(new DoorButton(15, 4));
		Board.add(new DoorButton(18, 3));
		Board.add(new DoorButton(18, 8));
		Board.add(new DoorButton(20,13));
		Board.add(new DoorButton(17, 15));
		Board.add(new DoorButton(17, 20));
		Board.add(new DoorButton(11, 18));
		Board.add(new DoorButton(12, 18));
		Board.add(new DoorButton(6, 18));
		Board.add(new DoorButton(7, 11));
		Board.add(new DoorButton(6, 14));
		
		Board.add(new SecretDoor(0, 0));
		Board.add(new SecretDoor(23, 23));
		Board.add(new SecretDoor(0, 23));
		Board.add(new SecretDoor(23, 0));
		
		
		//Button layout
		buttons[8][0] = new BoardButton(8,0);
		Board.add(buttons[8][0]);
		
		buttons[15][0] = new BoardButton(15,0);
		Board.add(buttons[15][0]);
		
		buttons[14][0] = new BoardButton(14,0);
		Board.add(buttons[14][0]);
		
		col = 8;
		for(row =9; row<=23; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		row = 8;
		for(col =8; col<=17; col+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		col = 9;
		for(row =9; row<=15; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		buttons[18][4] = new BoardButton(col,row);
		Board.add(buttons[18][4]);
		
		col = 18;
		for(row =5; row<=6; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		col = 16;
		for(row = 0; row<=6; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		row = 15;
		for(col =0; col<=7; col+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		row = 16;
		for(col =0; col<=16; col+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		row = 17;
		for(col =0; col<=16; col+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		col = 17;
		for(row = 0; row<=6; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		col = 15;
		for(row = 9; row<=15; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		col = 16;
		for(row = 9; row<=15; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		col = 17;
		for(row = 9; row<=13; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			}
		
		for(col = 0; col<=7; col+= 1){
			row = 6;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);	
			}
		
		for(col = 0; col<=18; col+= 1){
			row = 7;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);	
			}
		
		for(col = 18; col<=23; col+= 1)
		{
			row = 6;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			
		}
		for(col = 18; col<=23; col+= 1)
		{
			row = 5;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			
		}
		for(col = 18; col<=23; col+= 1)
		{
			row = 12;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			
		}
		
		col = 15;
		for(row = 18; row<=23; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
		}
		col = 16;
		for(row = 18; row<=23; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
		}
		
		buttons[17][17] = new BoardButton(17,17);
		Board.add(buttons[17][17]);
		
		for(col = 17; col<=23; col+= 1)
		{
			row = 18;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			
		}
		
		for(col = 17; col<=23; col+= 1)
		{
			row = 19;
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
			
		}
		
		col = 7;
		for(row = 18; row<=23; row+= 1){
			buttons[col][row] = new BoardButton(col,row);
			Board.add(buttons[col][row]);
		}
		
		Board.add(boardLabel);
		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board.setVisible(true);
		Board.setSize(700, 533);
		Board.setResizable(false);
		Board.setLocationRelativeTo(null);
		
		//Adding action Listeners to our board
		for(Integer x=0;x<23;x++)
	    {
	        for(Integer j=0;j<23;j++)
	        {
	        	if (buttons[x][j] != null){
	        		buttons[x][j].addActionListener(this);
	        	}
	        	else{
	        		
	        	}
	        }
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e){
	                	BoardButton buttonB = (BoardButton) e.getSource();
	                	System.out.println("Button pressed is: " + buttonB.getXpos() + "," + buttonB.getYpos());
	                    buttons[buttonB.getXpos()][buttonB.getYpos()].setBackground(Color.RED);
	                    canPlayerMove(buttonB.getXpos(), buttonB.getYpos());
	                }
	            
	public void canPlayerMove(int x, int y){
		System.out.println("Check if the player can move");
		//getPlayer();
		//
	}	    
	    }
