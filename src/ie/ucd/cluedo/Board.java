
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
		
	BoardButton[][] boardButtons = new BoardButton[BOARD_WIDTH][BOARD_HEIGHT]; 
	DoorButton[][] doorButtons = new DoorButton[BOARD_WIDTH][BOARD_HEIGHT]; 
	SecretButton[][] secretButtons = new SecretButton[BOARD_WIDTH][BOARD_HEIGHT];
		
		
	// Board Constructor 
	public Board(ArrayList<Player> players) 
	{	

		this.weaponPawns = new ArrayList<WeaponPawn>();
		this.playerPawns = new ArrayList<SuspectPawn>();
		
		gui();
		
		positionSuspectPawns(players);
		
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
			
		
		boardSlots = new Slot[BOARD_WIDTH][BOARD_HEIGHT];
			
		int[][] slotPositions = new int[][]
		{
			{ 3, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 3 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 2, 0, 0, 0, 0, 2, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 2, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 3, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 3 }
		};
		
		for (int i = 0; i < BOARD_WIDTH; i++)
		{
			for (int j = 0; j < 24; j++)
			{
				
				switch (slotPositions[i][j])
				{
					case 0:		break;
						   
					case 1: 	boardButtons[i][j] = new BoardButton(i, j);
								boardSlots[i][j] = new BoardSlot(i, j, boardButtons[i][j]);
								Board.add(boardButtons[i][j]);
								break;
						
					case 2: 	doorButtons[i][j] = new DoorButton(i, j);
								boardSlots[i][j] = new DoorSlot(i, j, doorButtons[i][j]);
								Board.add(doorButtons[i][j]);
								break;
								
					case 3: 	secretButtons[i][j] = new SecretButton(i, j);
								boardSlots[i][j] = new SecretSlot(i, j, secretButtons[i][j]);
								Board.add(secretButtons[i][j]);
								break;
								
					default: 	System.out.println("Nothing happened with slot");
								break;
				}
					
			}
		}		
	
						
		// Finish rest of board
			
		Board.add(boardLabel);
		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board.setSize(BOARD_WIDTH * BUTTON_PIXEL_WIDTH, 533);
		Board.setResizable(false);
		Board.setLocationRelativeTo(null);
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
}