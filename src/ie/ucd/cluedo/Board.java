/***************************************************************/
/* Board Class
/* 
/* Creates the slots and suspectPawns involved in the game
/* and board GUI
/***************************************************************/

package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ie.ucd.cluedo.DoorButton;
import ie.ucd.cluedo.SecretButton;

public class Board
{
	
	// Attributes
	ArrayList<SuspectPawn> suspectPawns;
	Slot[][] boardSlots;		
	ArrayList<RoomSlot> roomSlots;
	ArrayList<DoorSlot> doorSlots;
		
	
	// Constructor 
	public Board() 
	{
		this.suspectPawns = new ArrayList<SuspectPawn>();
		this.boardSlots = new Slot[BOARD_WIDTH][BOARD_HEIGHT];
		this.roomSlots = new ArrayList<RoomSlot>();
		this.doorSlots = new ArrayList<DoorSlot>();
		
		makeSlots();
		
		makeGui();
		
		makeSuspectPawns();	
	}

	
	/* Public Methods */
	
	
	// makesSlots() Method
	// Purpose: Maps out game board in term of different types of slots needed for each
	public void makeSlots() 
	{

		// Maps out board in terms of slots. Each number corresponds to a particular type of slot.
		int[][] slotPositions = new int[][]
		{
			{ 3, 0, 4, 4, 4, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 4, 4, 4, 0, 3 },
			{ 0, 0, 4, 4, 4, 0, 1, 1, 0, 0, 0, 4, 4, 0, 0, 0, 1, 1, 0, 4, 4, 4, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 4, 4, 4, 4, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 4, 4, 4, 4, 4, 4 },
			{ 4, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 4, 4, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 4, 4, 4, 4, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 4, 4, 4, 0, 0, 1, 1, 0, 0, 4, 4, 0, 0, 1, 1, 0, 0, 4, 4, 0, 0, 0 },
			{ 3, 0, 4, 4, 4, 0, 0, 1, 1, 0, 4, 4, 4, 4, 0, 1, 1, 0, 4, 4, 4, 4, 0, 3 }
		};
	
		// Maps out the board in terms of room numbers. Room number of 0 means a corridor slot
		int[][] roomPositions = new int[][]
		{
			{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 3, 3, 3 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 3, 3, 3 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 3, 3, 3 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 3, 3, 3 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
			{ 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9 },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9 },
			{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 7, 7, 7, 7, 7, 7, 7 },
			{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 7, 7, 7, 7, 7, 7, 7 },
			{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 7, 7, 7, 7, 7, 7, 7 },
			{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 7, 7, 7, 7, 7, 7, 7 }
		};
		
		
		for (int row = 0; row < BOARD_HEIGHT; row++)
		{
			
			for (int col = 0; col < BOARD_WIDTH; col++)
			{
				
				// Create different type of slots depending on the value of slotPositions. Each type of slot has a button.
				switch (slotPositions[row][col])
				{
					
					case 0:		// Only 6 slots are placed in each room, there is no slots in the remainder of each room;
								break;
						   
					case 1: 	this.boardSlots[row][col] = new BoardSlot(row, col, new BoardButton(row, col));
								break;
						
					case 2:		this.boardSlots[row][col] = new DoorSlot(row, col, roomPositions[row][col], new DoorButton(row, col));
								this.doorSlots.add((DoorSlot) this.boardSlots[row][col]);
								break;
								
					case 3: 	this.boardSlots[row][col] = new SecretSlot(row, col, roomPositions[row][col], new SecretButton(row, col));
								break;
								
					case 4:		// 6 room slots are placed in each room to enable them to contain the max possible number of suspect pawns
								this.boardSlots[row][col] = new RoomSlot(row, col, roomPositions[row][col], new RoomButton(row, col));
								this.roomSlots.add((RoomSlot) this.boardSlots[row][col]);
								break;
								
					default: 	System.out.println("Error reading slot matrix");
								break;
				
				}		
			}
		}	
	}
	
	
	// makeGui() Method
	// Purpose: Creates JFame to visually represent board
	public void makeGui() 
	{
			
		// Frame for board	
		JFrame Board = new JFrame("Cluedo");
		
		// Add buttons to board for each position where there is a slot
		for (int row = 0; row < BOARD_HEIGHT; row++)
		{
			
			for (int col = 0; col < BOARD_WIDTH; col++)
			{
				
				if (boardSlots[row][col] != null)
				{	
					Board.add(this.boardSlots[row][col].getButton());
				}
					
			}
		
		}		
	
		// Get background image for JFrame		
		ImageIcon image = null;
			
		try 
		{
			image = new ImageIcon(ImageIO.read(new File("CluedoBoard3.png")));
		} 
		catch (IOException e) 
		{
			System.out.println("Error reading image");
		}

		// Finalise JFrame
		JLabel boardLabel = new JLabel(image);
		boardLabel.setBounds(0, 0, BOARD_WIDTH * BUTTON_PIXEL_WIDTH, BOARD_HEIGHT * BUTTON_PIXEL_HEIGHT);
		
		Board.add(boardLabel);
		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set size for board with additions to account for JFrame outer
		Board.setSize(BOARD_WIDTH * BUTTON_PIXEL_WIDTH + 6, BOARD_HEIGHT * BUTTON_PIXEL_HEIGHT + 29);
		
		Board.setResizable(false);
		Board.setVisible(true);
	}

	
	// makeSuspectPawns() Method
	// Purpose: Creates suspect pawns for each game character and places them at set locations on board
	public void makeSuspectPawns()
	{
		
		// Initial locations for each suspect pawn is the same for every game
		int[] x0 = {9, 14, 23, 23, 15, 0};
		int[] y0 = {0, 0, 5, 12, 23, 16};
		
		// Color associated to each pawn
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.WHITE, Color.PINK};
				
		for (int i = 0; i < NUM_SUSPECTS; i++)
		{
			// Create suspect pawns and position on board
			this.suspectPawns.add(new SuspectPawn(i, colors[i]));
			this.suspectPawns.get(i).movePosition(this.boardSlots[y0[i]][x0[i]]);
		}
	}
	
	
	// getSuspectPawns() Method
	public ArrayList<SuspectPawn> getSuspectPawns()
	{
		return this.suspectPawns;
	}
	
	
	// addSuspectPawns() Method
	public void addSuspectPawn(SuspectPawn suspectPawn)
	{
		this.suspectPawns.add(suspectPawn);
	}
	
	
	// getSlots() Method
	public Slot[][] getSlots()
	{
		return this.boardSlots;		
	}


	// getRoomSlots() Method
	public ArrayList<RoomSlot> getRoomSlots()
	{
		return this.roomSlots;		
	}
	
	
	// getDoorSlots() Method
	public ArrayList<DoorSlot> getDoorSlots()
	{
		return this.doorSlots;		
	}
	
}