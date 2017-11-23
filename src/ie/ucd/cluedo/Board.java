package ie.ucd.cluedo;

import static ie.ucd.cluedo.GameValues.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Board 
{
	
	// Board attributes
	ArrayList<Slot> slots = new ArrayList<Slot>(BOARD_WIDTH * BOARD_HEIGHT);
	Button[][] buttons = new Button[BOARD_WIDTH][BOARD_HEIGHT]; 
	ArrayList<WeaponPawn> weaponPawns = new ArrayList<WeaponPawn>(NUM_WEAPONS);
	ArrayList<Room> rooms = new ArrayList<Room>(NUM_ROOMS);
		
	public Board(ArrayList<Player> players) 
	{
		// Fill slots and buttons
		for (int i = 0; i < BOARD_WIDTH; i++)
		{
			for (int j = 0; j < BOARD_HEIGHT; j++)
			{
				slots.add(new Slot(i, j));
			}
		}
			
		//gui();
		makeSuspectPawns(players);
		
		System.out.printf("\nPLAYER DETAILS:\n\n");
		
		/*for (int i = 0; i < players.size(); i++)
		{
			System.out.printf("Player %d\n", players.get(i).getPlayerNumber());
			System.out.printf("Pawn: %s\n", players.get(i).getSuspectPawn().getName());
			System.out.printf("Pawn Location: (%d, %d)\n\n", slots.get(i*100).getXPosition(), slots.get(i*100).getYPosition());
		}*/

	}

	public void gui() 
	{
		
		JFrame Board = new JFrame("Frame");		
		JLabel boardLabel = new JLabel();		

		for (int i = 4; i < 8; i++)
		{
			for (int j = 0; j < BOARD_HEIGHT; j++)
			{
				Board.add(buttons[i][j]);
			}
		}
		
		
		for (int i = 16; i < 20; i++)
		{
			for (int j = 0; j < BOARD_HEIGHT; j++)
			{
				Board.add(buttons[i][j]);
			}
		}
		
		for (int i = 0; i < BOARD_WIDTH; i++)
		{
			for (int j = 6; j < 10; j++)
			{
				Board.add(buttons[i][j]);
			}
			
			if (i == 4 || i == 16)
			{
				i += 5;
			}
		}
		
		
		for (int i = 0; i < BOARD_WIDTH; i++)
		{
			for (int j = 16; j < 20; j++)
			{
				Board.add(buttons[i][j]);
			}
			
			if (i == 4 || i == 9)
			{
				i += 5;
			}
		}

		Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board.add(boardLabel);
		Board.setVisible(true);
		Board.setSize(700, 544);
		Board.setLocationRelativeTo(null);
		
	}

	public void makeSuspectPawns(ArrayList<Player> players)
	{
		
		players.get(0).giveSuspectPawn(new SuspectPawn(1, slots.get(0)));
		players.get(1).giveSuspectPawn(new SuspectPawn(2, slots.get(100)));
		
		if (players.size() <= 2)
		{
			return;
		}
		
		players.get(2).giveSuspectPawn(new SuspectPawn(3, slots.get(200)));
		
		if (players.size() <= 3)
		{
			return;
		}
		
		players.get(3).giveSuspectPawn(new SuspectPawn(4, slots.get(300)));
		
		if (players.size() <= 4)
		{
			return;
		}
		
		players.get(4).giveSuspectPawn(new SuspectPawn(5, slots.get(400)));
		
		if (players.size() <= 5)
		{
			return;
		}
		
		players.get(5).giveSuspectPawn(new SuspectPawn(6, slots.get(500)));
	}
	
}
