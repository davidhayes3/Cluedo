
package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

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
	
	//Board Attributes
	
	ArrayList<WeaponPawn> weaponPawns;
	ArrayList<SuspectPawn> playerPawns;
	Slot[][] boardSlots;		
	ArrayList<RoomSlot> roomSlots;
	ArrayList<DoorSlot> doorSlots;
		
	// Board Constructor 
	public Board(ArrayList<Player> players) 
	{	

		this.weaponPawns = new ArrayList<WeaponPawn>();
		this.playerPawns = new ArrayList<SuspectPawn>();
		this.roomSlots = new ArrayList<RoomSlot>();
		this.doorSlots = new ArrayList<DoorSlot>();
		
		gui();
		
		positionSuspectPawns(players);
		
	}

	// Method which creates GUI
	public void gui() 
	{
			
		// Initialize frame for board
			
		JFrame Board = new JFrame("Cluedo");
		
		// Create Board Slots
			
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

		boardSlots = new Slot[BOARD_WIDTH][BOARD_HEIGHT];
		
		for (int row = 0; row < BOARD_HEIGHT; row++)
		{
			
			for (int col = 0; col < BOARD_WIDTH; col++)
			{
				
				switch (slotPositions[row][col])
				{
					
					case 0:		break;
						   
					case 1: 	boardSlots[row][col] = new BoardSlot(row, col, new BoardButton(row, col));
								Board.add(boardSlots[row][col].getButton());
								break;
						
					case 2:		boardSlots[row][col] = new DoorSlot(row, col, roomPositions[row][col], new DoorButton(row, col));
								this.doorSlots.add((DoorSlot) boardSlots[row][col]);
								Board.add(boardSlots[row][col].getButton());
								break;
								
					case 3: 	boardSlots[row][col] = new SecretSlot(row, col, roomPositions[row][col], new SecretButton(row, col));
								Board.add(boardSlots[row][col].getButton());
								break;
								
					case 4:		boardSlots[row][col] = new RoomSlot(row, col, roomPositions[row][col], new RoomButton(row, col));
								this.roomSlots.add((RoomSlot) boardSlots[row][col]);
								Board.add(boardSlots[row][col].getButton());
								break;
								
					default: 	System.out.println("Error reading slot matrix");
								break;
				
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
			System.out.println("Error");
		}

		JLabel boardLabel = new JLabel(image);
		boardLabel.setBounds(0, 0, BOARD_WIDTH * BUTTON_PIXEL_WIDTH, BOARD_HEIGHT * BUTTON_PIXEL_HEIGHT);
		
		Board.add(boardLabel);
		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board.setSize(BOARD_WIDTH * BUTTON_PIXEL_WIDTH + 6, BOARD_HEIGHT * BUTTON_PIXEL_HEIGHT + 29);
		Board.setResizable(false);
		Board.setVisible(true);
	}

	
	// Place pawns at starting positions
	public void positionSuspectPawns(ArrayList<Player> players)
	{
		int[] x0 = {9, 14, 23, 23, 15, 0};
		int[] y0 = {0, 0, 5, 12, 23, 16};
				
		for (int i = 0; i < players.size(); i++)
		{
			playerPawns.add(players.get(i).getSuspectPawn());
			players.get(i).getSuspectPawn().movePosition(this.boardSlots[y0[i]][x0[i]]);
		}
	}
	
	public Slot[][] getSlots()
	{
		return this.boardSlots;		
	}
	
	public ArrayList<RoomSlot> getRoomSlots()
	{
		return this.roomSlots;		
	}
	
	public ArrayList<DoorSlot> getDoorSlots()
	{
		return this.doorSlots;		
	}
}