/***************************************************************/
/* Hypothesis Manager Class
/* 
/* Implements the formulation and resulting actions arising from 
/* hypotheses
/***************************************************************/

package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;

import static ie.ucd.cluedo.GameValues.*;


public class HypothesisManager 
{
	
	// Attributes
	Hypothesis hypothesis;
	
	
	// Constructor
	public HypothesisManager()
	{
	
	}
	
	
	/* Public Methods */
	
	
	// simulateHypothesis() Method
	// Purpose: Implements a full hypothesis and makes the necessary board repositions
	@SuppressWarnings("resource")
	public void simulateHypothesis(ArrayList<Player> players, Board gameBoard, int playerTurn)
	{

		Scanner scanner = new Scanner(System.in);
		
		// Get suspect in hypothesis
		System.out.println("\nWhat suspect is in your hypothesis:");
		for (int i = 0; i < NUM_SUSPECTS; i++)
		{
			System.out.println(i + ")" + gameList.get(i));
		}
		
		int suspectHypothesis = scanner.nextInt();
		
		
		// Get weapon in hypothesis
		System.out.println("\nWhat weapon is in your hypothesis:");
		for (int i = 0; i < NUM_WEAPONS; i++)
		{
			System.out.println(i + ")" + gameList.get(i + NUM_SUSPECTS));
		}
		
		int weaponHypothesis = scanner.nextInt() + NUM_SUSPECTS;
		
		
		// Room in hypothesis is the players current room
		int currentRoom = players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber();
		int roomHypothesis = currentRoom + NUM_SUSPECTS + NUM_WEAPONS - 1;
		
		
		// Create hypothesis object
		Hypothesis playerHypothesis = new Hypothesis(suspectHypothesis, weaponHypothesis, roomHypothesis);
		
		// Check cards of all players to see if any player refutes the hypothesis
		checkPlayersCards(players, playerHypothesis, playerTurn);
		
		// Move the suspect pawn of the hypothesis to a new room
		moveSuspectPawn(players, gameBoard, suspectHypothesis, roomHypothesis);

	}
	
	
	/* Private Methods */
	
	
	// moveSuspectPawn() Method
	// Purpose: Moves the suspect pawn of the of the suspect in the hypothesis to the room in the hypothesis 
	private void moveSuspectPawn(ArrayList <Player> players, Board gameBoard, int suspectHypothesis, int roomHypothesis)
	{
		
		for (int i = 0; i < players.size(); i++)
		{
			if (players.get(i).getSuspectPawn().getPawnIndex() == suspectHypothesis)
			{
				if (players.get(i).getSuspectPawn().getPosition().getRoomNumber() == roomHypothesis - NUM_SUSPECTS - NUM_WEAPONS + 1)
				{
					return;
				}
			}
		}
		
		
		for (int j = 0; j < gameBoard.getSuspectPawns().size(); j++)
		{
			if (gameBoard.getSuspectPawns().get(j).getPawnIndex() == suspectHypothesis)
			{
				if (gameBoard.getSuspectPawns().get(j).getPosition().getRoomNumber() == roomHypothesis - NUM_SUSPECTS - NUM_WEAPONS + 1)
				{
					return;
				}
			}
		}
		
		
		Slot roomSlot = getRoomSlot(players, gameBoard, roomHypothesis);

		for (int i = 0; i < players.size(); i++)
		{
			if (players.get(i).getSuspectPawn().getPawnIndex() == suspectHypothesis)
			{	
				players.get(i).getSuspectPawn().movePosition(roomSlot);
				return;
			}
		}
		
		
		for (int j = 0; j < gameBoard.getSuspectPawns().size(); j++)
		{
			if (gameBoard.getSuspectPawns().get(j).getPawnIndex() == suspectHypothesis)
			{
				gameBoard.getSuspectPawns().get(j).movePosition(roomSlot);
				return;
			}
		}
			
	}

	private Slot getRoomSlot(ArrayList<Player> players, Board gameBoard, int roomHypothesis)
	{
		boolean slotOccupied;
		
		ArrayList<RoomSlot> roomSlots = gameBoard.getRoomSlots();
		
		for (RoomSlot rs: roomSlots)
		{
			slotOccupied = false;
			
			if (rs.getRoomNumber() == roomHypothesis - NUM_SUSPECTS - NUM_WEAPONS + 1)
			{
				
				for (Player p: players)
				{
					if (p.getSuspectPawn().getPosition() == gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				for (int i = 0; i < gameBoard.getSuspectPawns().size(); i++)
				{
					if (gameBoard.getSuspectPawns().get(i).getPosition() == gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				if (!slotOccupied)
				{
					return gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()];
				}
			}
		}
		
		return null;
	}
	
	private void checkPlayersCards(ArrayList<Player> players, Hypothesis hypothesis, int playerTurn)
	{
		
		ArrayList<Card> playerCards;
		
		int suspectHypothesis = hypothesis.getSuspect();
		int weaponHypothesis = hypothesis.getWeapon();
		int roomHypothesis = hypothesis.getRoom();
		
		int i = playerTurn - 1;
		i = (i < 0) ? (i + players.size()) : i;
		
		while (i != playerTurn)
		{
				
				playerCards = players.get(i).getCards();
				
				for(int j = 0; j < playerCards.size(); j++)
				{
					if (playerCards.get(j).getCardIndex() == suspectHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players, players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}

					else if (playerCards.get(j).getCardIndex() == weaponHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players, players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}
				
					else if (playerCards.get(j).getCardIndex() == roomHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players, players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}

				}
				
				i--;
				i = (i < 0) ? (i + players.size()) : i;
		}
		
		System.out.println("The hypothesis was not refuted");
		
	}
	
	public void updateNotebook(ArrayList<Player> players, Player accuser, Player refuter, Card cardShown, int suspectHypothesis, int weaponHypothesis, int roomHypothesis)
	{
		
		// Two entries for accuser
		accuser.getNotebook().makeEntry("I formulated the hypothesis " + gameList.get(suspectHypothesis) + 
								 ", " + gameList.get(weaponHypothesis) + ", " + gameList.get(roomHypothesis) + ".");
		accuser.getNotebook().makeEntry(refuter.getSuspectPawn().getName() + " refuted the hypothesis with the card " + cardShown.getName()+ ".");
		
		// Two entries for refuter
		refuter.getNotebook().makeEntry(accuser.getSuspectPawn().getName() + " formulated the hypothesis " + gameList.get(suspectHypothesis) + 
				 ", " + gameList.get(weaponHypothesis) + ", " + gameList.get(roomHypothesis) + ".");
		
		refuter.getNotebook().makeEntry("I refuted " + accuser.getSuspectPawn().getName() + "'s hypothesis with the card " + cardShown.getName()+ ".");
		
		for (int i = 0; i < players.size(); i++)
		{
			
			if (i + 1 == refuter.getPlayerNumber() || i + 1 == accuser.getPlayerNumber())
			{
				continue;
			}
			
			else
			{
				players.get(i).getNotebook().makeEntry(accuser.getSuspectPawn().getName() + " made the hypothesis " + gameList.get(suspectHypothesis) 
				+ ", " + gameList.get(weaponHypothesis) + ", " + gameList.get(roomHypothesis) + ".");
				
				players.get(i).getNotebook().makeEntry(refuter.getSuspectPawn().getName() + " refuted the hypothesis.");
			}				
		}
	}
}
