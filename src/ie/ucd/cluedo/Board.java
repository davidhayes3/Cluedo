package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.BOARD_HEIGHT;
import static ie.ucd.cluedo.GameValues.BOARD_WIDTH;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ie.ucd.cluedo.DoorButton;
import ie.ucd.cluedo.SecretDoor;

import static ie.ucd.cluedo.GameValues.*;

public class Board implements ActionListener 
{
	
	//Board Attributes
	
	ArrayList<Player> players;
	ArrayList<Slot> slots = new ArrayList<Slot>(BOARD_WIDTH * BOARD_HEIGHT);
	
	BoardButton[][] buttons = new BoardButton[24][24]; 
	DoorButton[][] doorButtons = new DoorButton[24][24]; 
	
	DiceButton dice = new DiceButton();
	JButton buttonB = new JButton();
	FinishMoveButton finishButton = new FinishMoveButton();
	
	private ImageIcon image;
	
	NoteBookDialog note;
	
	int buttonPress = 0;
	int numButtonPressed = 0;
	int x = 0;
	int y = 0;
	int old_x;
	int old_y;

	
	// Board Constructor 
	public Board(ArrayList<Player> players) 
	{	
		this.players = players;
		
		// Fill slots and buttons
		for (int i = 0; i < BOARD_WIDTH; i++)
		{
			for (int j = 0; j < BOARD_HEIGHT; j++)
			{
				slots.add(new Slot(i, j));
			}
		}
		
		gui();
		
		makeSuspectPawns();
		
	}

	// Method which creates GUI
	public void gui() 
	{

		JFrame Board = new JFrame("Welcome to Cluedo");
		Board.setLayout(null);

		try 
		{
			image = new ImageIcon(ImageIO.read(new File("C:\\Users\\Admin\\Documents\\5th Year\\Software Engineering\\CluedoBoard3.png")));
		} 
		catch (IOException e) 
		{
			System.out.println("Error");
		}

		JLabel boardLabel = new JLabel(image);
		boardLabel.setBounds(0, 0, 504,504);
		
		// Doors and Secret Passageways
		doorButtons[8][4] = new DoorButton(8,4);
		doorButtons[8][4].addActionListener(this);
		Board.add(doorButtons[8][4]);
		Board.add(new DoorButton(4, 5));
		
		//Board.add(new DoorButton(8, 4));
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
		
		// Button layout
		
		int row, col;

		for(col = 6; col<=7; col++)
		{
			for(row = 0; row<=8; row++)
			{
				buttons[col][row] = new BoardButton(col, row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);
			}
		}
		
		buttons[9][0] = new BoardButton(9, 0);
		buttons[9][0].addActionListener(this);
		Board.add(buttons[9][0]);
		
		buttons[8][0] = new BoardButton(8,0);
		buttons[8][0].addActionListener(this);
		Board.add(buttons[8][0]);
		
		buttons[15][0] = new BoardButton(15,0);
		buttons[15][0].addActionListener(this);
		Board.add(buttons[15][0]);
		
		buttons[14][0] = new BoardButton(14,0);
		buttons[14][0].addActionListener(this);
		Board.add(buttons[14][0]);
		
		col = 8;
		for(row =9; row<=23; row++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			
			}
		
		row = 8;
		for(col =8; col<=17; col++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}
		
		col = 9;
		for(row =9; row<=15; row++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}
		
		
		col = 18;
		for(row =4; row<=6; row++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}
		
		for (col = 16; col <= 17; col++)
		for(row = 0; row<=6; row++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}
		
		for(row = 15; row <= 17; row++)
		{
			for(col =0; col<=7; col++){
				buttons[col][row] = new BoardButton(col,row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);
			}
		}
		
		row = 16;
		for(col =9; col<=16; col++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}
		
		row = 17;
		for(col =9; col<=16; col++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}		
		
		for (col = 15; col <=16; col++)
		{
			for(row = 9; row<=15; row++){
				buttons[col][row] = new BoardButton(col,row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);
			}
		}
		
		col = 17;
		for(row = 9; row<=13; row++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
			}
		
		for (row = 6; row <= 7; row++)
		{
			for(col = 0; col <= 5; col++){
				buttons[col][row] = new BoardButton(col,row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);	
			}
		}
		
		row = 7;
		for(col = 8; col <= 18; col++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);	
			}
	
		for (row = 5; row <= 6; row++)
		{
			for(col = 19; col <= 23; col++)
			{
				buttons[col][row] = new BoardButton(col,row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);
			}
		}
		
		row = 12;
		for(col = 18; col <= 23; col++)
		{
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);	
		}
		
		for (col = 15; col <=16; col++)
		{
			for(row = 18; row <= 23; row++){
				buttons[col][row] = new BoardButton(col,row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);
			}
		}
		
		buttons[17][17] = new BoardButton(17,17);
		buttons[17][17].addActionListener(this);
		Board.add(buttons[17][17]);
		
		for (row = 18; row <= 19; row++)
		{
			for(col = 17; col <= 23; col++)
			{
				buttons[col][row] = new BoardButton(col,row);
				buttons[col][row].addActionListener(this);
				Board.add(buttons[col][row]);		
			}
		}
		
		col = 7;
		for(row = 18; row <= 23; row++){
			buttons[col][row] = new BoardButton(col,row);
			buttons[col][row].addActionListener(this);
			Board.add(buttons[col][row]);
		}
		
		this.note =  new NoteBookDialog(Board, "This is your notebook"); 
		NoteBookButton noteBook = new NoteBookButton(note);
		
		// Add action listeners to all buttons
		finishButton.addActionListener(this);
		dice.addActionListener(this);
		noteBook.addActionListener(this);
		
		Board.add(dice);
		Board.add(finishButton);
		Board.add(noteBook);
		
		
		
		

		
		Board.add(boardLabel);
		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board.setVisible(true);
		Board.setSize(700, 533);
		Board.setResizable(false);
		Board.setLocationRelativeTo(null);



		//Adding action Listeners to our board
		/*for(Integer x=0;x<23;x++)
	    {
	        for(Integer j=0;j<23;j++)
	        {
	        	if (buttons[x][j] != null)
	        	{
	        		buttons[x][j].addActionListener(this);
	        	}
	        }
	    }*/
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.buttonPress++;
		
		if (e.getSource() instanceof DiceButton)
		{
			//System.out.println("Dice Roll Button Pressed");	
			
			this.numButtonPressed = DIES_BUTTON_PRESS;
		}
						
		else if(e.getSource() instanceof BoardButton)
		{
	    	buttonB = (JButton) e.getSource();
	       
	       	this.x = buttonB.getX()/21;
        	this.y = buttonB.getY()/21;

	       	this.numButtonPressed = BOARD_BUTTON_PRESS;
		}
		
		else if (e.getSource() instanceof FinishMoveButton)
		{
			//System.out.println("Finish Button Pressed");
			this.numButtonPressed = END_TURN_BUTTON_PRESS;
		}
		
		else if(e.getSource() instanceof NoteBookButton)
		{
	       	this.numButtonPressed = NOTEBOOK_BUTTON_PRESS;
		}
                  	
	}
                    
	public int movePawn(int playerTurn, int diceScore)
	{
		if (canPlayerMove(playerTurn, diceScore))
		{			
	       	int old_x = players.get(playerTurn).getSuspectPawn().getX();
	        int old_y = players.get(playerTurn).getSuspectPawn().getY();
	        
			players.get(playerTurn).getSuspectPawn().movePawns(this.x, this.y);
			//System.out.println("Player moved to: " + players.get(playerTurn).getSuspectPawn().getX() + "," + players.get(playerTurn).getSuspectPawn().getY());
			
			this.buttons[old_x][old_y].setBackground(Color.yellow);
	        this.buttons[this.x][this.y].setBackground(players.get(playerTurn).getSuspectPawn().getColor());

	        diceScore = 0;
		}
		
		return diceScore;	
	}
	
	public void makeSuspectPawns()
	{
		
		//System.out.println(players.get(0).getPosition().getXPosition());
		this.players.get(0).giveSuspectPawn(new SuspectPawn(1, slots.get(0), Color.red));
		this.players.get(0).getSuspectPawn().movePawn(buttons[9][0]);
		this.players.get(0).getSuspectPawn().getSuspectButton().setBackground(Color.red);
		this.players.get(1).giveSuspectPawn(new SuspectPawn(2, slots.get(100), Color.blue));
		this.players.get(1).getSuspectPawn().movePawn(buttons[14][0]);
		this.players.get(1).getSuspectPawn().getSuspectButton().setBackground(Color.blue);
		
		if (this.players.size() <= 2)
		{		
			return;
		}
		
		this.players.get(2).giveSuspectPawn(new SuspectPawn(3, slots.get(200), Color.ORANGE));
		this.players.get(2).getSuspectPawn().movePawn(buttons[23][5]);
		this.players.get(2).getSuspectPawn().getSuspectButton().setBackground(Color.ORANGE);
		if (this.players.size() <= 3)
		{
			return;
		}
		
		this.players.get(3).giveSuspectPawn(new SuspectPawn(4, slots.get(300), Color.MAGENTA));
		this.players.get(3).getSuspectPawn().movePawn(buttons[23][12]);
		this.players.get(3).getSuspectPawn().getSuspectButton().setBackground(Color.MAGENTA);
		
		if (this.players.size() <= 4)
		{
			return;
		}
		
		this.players.get(4).giveSuspectPawn(new SuspectPawn(4, slots.get(300), Color.WHITE));
		this.players.get(4).getSuspectPawn().movePawn(buttons[15][23]);
		this.players.get(4).getSuspectPawn().getSuspectButton().setBackground(Color.WHITE);
		
		if (this.players.size() <= 5)
		{
			return;
		}
		
		this.players.get(5).giveSuspectPawn(new SuspectPawn(4, slots.get(300), Color.PINK));
		this.players.get(5).getSuspectPawn().movePawn(buttons[0][16]);
		this.players.get(5).getSuspectPawn().getSuspectButton().setBackground(Color.PINK);
	}
	
	public boolean canPlayerMove(int playerTurn, int diceScore)
	{

		int newX = players.get(playerTurn).getSuspectPawn().getX();
		int newY = players.get(playerTurn).getSuspectPawn().getY();
		
		int a = java.lang.Math.abs(newX - this.x);
		int b = java.lang.Math.abs(newY - this.y);
		
		if(a + b <= diceScore)
		{
			return true;
		}
		
		System.out.println("The distance of this position is greater than your dice roll");
		return false;
	}
	
	public void showNoteBook(int playerTurn){
		note.changeNoteBook(players.get(playerTurn).inspectNotebook());
		note.openDialog();
	}

	public int detectButtonPress()
	{
		return this.buttonPress;
	}
	
	public int getButtonPressed()
	{
		return this.numButtonPressed;
	}
}
		