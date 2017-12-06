package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ie.ucd.cluedo.DoorButton;
import ie.ucd.cluedo.SecretDoor;

public class Board implements ActionListener 
{
	
	//Board Attributes
	
	ArrayList<Player> players;
	ArrayList<WeaponPawn> weaponPawns;
	//ArrayList<Slot> slots = new ArrayList<Slot>(BOARD_WIDTH * BOARD_HEIGHT);
	
	Slot[][] slots = new Slot[BOARD_WIDTH][BOARD_HEIGHT];
	
	BoardButton[][] buttons = new BoardButton[BOARD_WIDTH][BOARD_HEIGHT]; 
	DoorButton[][] doorButtons = new DoorButton[BOARD_WIDTH][BOARD_HEIGHT]; 
	
	/*DiceButton diceButton;
	FinishMoveButton finishButton;
	NotebookButton notebookButton;*/
	
	JButton buttonTracker;
	
	NotebookDialog note;
		
	int buttonPress;
	int numButtonPressed;
	int x;
	int y;
	int last_x;
	int last_y;
	
	
	// Board Constructor 
	public Board(ArrayList<Player> players) 
	{	
		this.players = players;
		
		this.buttonPress = 0;
		this.numButtonPressed = 0;
		
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
		
		// Make frame for board
		
		JFrame Board = new JFrame("Welcome to Cluedo");
		Board.setLayout(null);

		// Get background image for JFrame
		
		ImageIcon image = null;
		
		try 
		{
			image = new ImageIcon(ImageIO.read(new File("C:\\Users\\Admin\\Documents\\5th Year\\Software Engineering\\CluedoBoard3.png")));
		} 
		catch (IOException e) 
		{
			System.out.println("Error");
		}

		JLabel boardLabel = new JLabel(image);
		boardLabel.setBounds(0, 0, BOARD_WIDTH * BUTTON_PIXEL_WIDTH, BOARD_HEIGHT * BUTTON_PIXEL_HEIGHT);
		
		
		
		// Make door buttons and passage ways
		
		doorButtons[8][4] = new DoorButton(8,4);
		doorButtons[4][5] = new DoorButton(4,5);
		doorButtons[9][6] = new DoorButton(9,6);
		doorButtons[14][6] = new DoorButton(14,6);
		doorButtons[15][4] = new DoorButton(15,4);
		doorButtons[18][3] = new DoorButton(18,3);
		doorButtons[18][8] = new DoorButton(18,8);
		doorButtons[20][13] = new DoorButton(20,13);
		doorButtons[17][15] = new DoorButton(17,15);
		doorButtons[17][20] = new DoorButton(17,20);
		doorButtons[11][18] = new DoorButton(11,18);
		doorButtons[12][18] = new DoorButton(12,18);
		doorButtons[6][18] = new DoorButton(6,18);
		doorButtons[7][11] = new DoorButton(7,11);
		doorButtons[6][14] = new DoorButton(6,14);

		
		
		// Make all board buttons
		
		int row, col;

		for(col = 6; col <= 7; col++)
		{
			for(row = 0; row <= 8; row++)
			{
				buttons[col][row] = new BoardButton(col, row);
				Board.add(buttons[col][row]);
			}
		}
		
		col = 8;
		for(row = 9; row <= 23; row++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
		
		row = 8;
		for(col = 8; col <= 17; col++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
		
		col = 9;
		for(row = 9; row <= 15; row++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
			
		col = 18;
		for(row = 4; row <= 6; row++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
		
		for (col = 16; col <= 17; col++)
		{
			for(row = 0; row <= 6; row++)
			{
				buttons[col][row] = new BoardButton(col, row);
			}
		}
		
		for(row = 15; row <= 17; row++)
		{
			for(col = 0; col <= 7; col++)
			{
				buttons[col][row] = new BoardButton(col, row);
			}
		}
		
		row = 16;
		for(col = 9; col <= 16; col++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
		
		row = 17;
		for(col = 9; col <= 16; col++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}		
		
		for (col = 15; col <= 16; col++)
		{
			for(row = 9; row <= 15; row++)
			{
				buttons[col][row] = new BoardButton(col, row);
			}
		}
		
		col = 17;
		for(row = 9; row <= 13; row++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
		
		for (row = 6; row <= 7; row++)
		{
			for(col = 0; col <= 5; col++)
			{
				buttons[col][row] = new BoardButton(col, row);
			}
		}
		
		row = 7;
		for(col = 8; col <= 18; col++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
	
		for (row = 5; row <= 6; row++)
		{
			for(col = 19; col <= 23; col++)
			{
				buttons[col][row] = new BoardButton(col, row);
			}
		}
		
		row = 12;
		for(col = 18; col <= 23; col++)
		{
			buttons[col][row] = new BoardButton(col, row);
		}
		
		for (col = 15; col <= 16; col++)
		{
			for(row = 18; row <= 23; row++)
			{
				buttons[col][row] = new BoardButton(col, row);
			}
		}
		
		for (row = 18; row <= 19; row++)
		{
			for(col = 17; col <= 23; col++)
			{
				buttons[col][row] = new BoardButton(col, row);	
			}
		}
		
		col = 7;
		for(row = 18; row <= 23; row++)
		{
			buttons[col][row] = new BoardButton(col,row);
		}
		
		buttons[9][0] = new BoardButton(9, 0);	
		buttons[8][0] = new BoardButton(8,0);
		buttons[15][0] = new BoardButton(15,0);
		buttons[14][0] = new BoardButton(14,0);
		buttons[17][17] = new BoardButton(17,17);
		
		
		
		// Add all buttons to board and add action listeners to all buttons

		for(int x = 0; x < BOARD_WIDTH; x++)
	    {
	        for(int y = 0; y < BOARD_HEIGHT; y++)
	        {
	        	if (buttons[x][y] != null)
	        	{
	        		buttons[x][y].addActionListener(this);
	        		Board.add(buttons[x][y]);
	        	}
	        	
	        	if (doorButtons[x][y] != null)
	        	{
	        		doorButtons[x][y].addActionListener(this);
	        		Board.add(doorButtons[x][y]);
	        	}
	        }
	    }
		
		// Add secret doors to board
		Board.add(new SecretDoor(0, 0));
		Board.add(new SecretDoor(23, 23));
		Board.add(new SecretDoor(0, 23));
		Board.add(new SecretDoor(23, 0));
		
		
		// Create, add action listeners, and add to board, all other buttons
		
		diceButton = new DiceButton();
		notebookButton = new NotebookButton(new NotebookDialog(Board, "Notebook"));
		finishButton = new FinishMoveButton();
		
		diceButton.addActionListener(this);
		notebookButton.addActionListener(this);
		finishButton.addActionListener(this);
		
		Board.add(diceButton);
		Board.add(finishButton);
		Board.add(notebookButton);
		
		
		// Finish rest of board
		
		Board.add(boardLabel);
		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board.setSize(700, 533);
		Board.setResizable(false);
		Board.setLocationRelativeTo(null);
		Board.setVisible(true);
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
	    	buttonTracker = (JButton) e.getSource();
	       
	       	this.x = buttonTracker.getX() / BUTTON_PIXEL_WIDTH;
        	this.y = buttonTracker.getY() / BUTTON_PIXEL_HEIGHT ;

	       	this.numButtonPressed = BOARD_BUTTON_PRESS;
		}
		
		else if (e.getSource() instanceof FinishMoveButton)
		{
			//System.out.println("Finish Button Pressed");
			this.numButtonPressed = END_TURN_BUTTON_PRESS;
		}
		
		else if(e.getSource() instanceof NotebookButton)
		{
	       	this.numButtonPressed = NOTEBOOK_BUTTON_PRESS;
		}
		
		else if(e.getSource() instanceof DoorButton)
		{
	    	buttonTracker = (JButton) e.getSource();
		       
	       	this.x = buttonTracker.getX() / BUTTON_PIXEL_HEIGHT ;
        	this.y = buttonTracker.getY() / BUTTON_PIXEL_HEIGHT;

	       	this.numButtonPressed = DOOR_BUTTON_PRESS;
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
			this.buttons[old_x][old_y].setText("");
	        this.buttons[this.x][this.y].setBackground(players.get(playerTurn).getSuspectPawn().getColor());        
	        
	        diceScore = 0;
		}
		
		return diceScore;	
	}
	
	public void makeSuspectPawns()
	{		
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.WHITE, Color.PINK};

		
		Map<Color, String> colorMap = new HashMap<Color, String>();
		colorMap.put(Color.RED, "Red");  
	    colorMap.put(Color.BLUE, "Blue");
	    colorMap.put(Color.GREEN, "Green");
	    colorMap.put(Color.MAGENTA, "Magenta");
	    colorMap.put(Color.WHITE, "White");
	    colorMap.put(Color.PINK, "Pink");
		
		for (int i = 0; i < players.size(); i++)
		{
			while (true)
			{
				// Ask user for input until no. of players between 2 and 6 is selected
				System.out.println("\nPlayer " + (i + 1) + ", please select your character:\n1. MISS SCARLET\n2. PROFESSOR PLUM\n"
						+ "3. MRS. PEACOCK\n4. REVEREND MR. GREEN\n5. COLONEL MUSTARD\n6. MRS. WHITE\n");
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				int playerChoice = scanner.nextInt();
				
				if (playerChoice > 0 && playerChoice <= MAX_NUM_PLAYERS)
				{
					this.players.get(i).giveSuspectPawn(new SuspectPawn(playerChoice, slots.get(i), colors[playerChoice-1]));
					this.players.get(i).getSuspectPawn().movePawn(buttons[init_x[playerChoice-1]][init_y[playerChoice-1]]);
					this.players.get(i).getSuspectPawn().getSuspectButton().setBackground(colors[playerChoice-1]);
					System.out.println("Player " + (i+1) + " is " + players.get(i).getSuspectPawn().getName() + " (" + colorMap.get(players.get(i).getSuspectPawn().getColor()) + ")");
					break;
				}
				else
				{
					System.out.println("Please enter a number between 1 and " + players.size());
				}
			}
		}

		
	}
	
	public boolean canPlayerMove(int playerTurn, int diceScore)
	{

		int newX = players.get(playerTurn).getSuspectPawn().getX();
		int newY = players.get(playerTurn).getSuspectPawn().getY();
		
		int a = java.lang.Math.abs(newX - this.x);
		int b = java.lang.Math.abs(newY - this.y);
		
		if(a + b > diceScore)
		{
			System.out.println("Your dice score is not high enough to move here");
			return false;
		}
		
		for (int i = 0; i < players.size(); i++)
		{
			if(this.x == players.get(i).getSuspectPawn().getX() && this.y == players.get(i).getSuspectPawn().getY())
			{
				System.out.println("Another player is already in that position");
				return false;
			}
		}
			
		return true;
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
		