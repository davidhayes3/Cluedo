package ie.ucd.cluedo;

import java.util.ArrayList;
import java.util.Scanner;

import static ie.ucd.cluedo.GameValues.*;


public class HypothesisManager 
{
	
	ArrayList<Player> players;
	Board gameBoard;
	
	public HypothesisManager(ArrayList<Player> players, Board gameBoard)
	{
		
		this.players = players;	
		this.gameBoard = gameBoard;
	
	}
	
	
	
	public void simulateHypothesis(int playerTurn, int numPlayers)
	{

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What suspect is in your hypothesis: " );
		for (int i = 0; i < 6; i++)
		{
			System.out.println("[" + i + "]" + gameList.get(i));
		}
		
		int suspectHypothesis = scanner.nextInt();
		
		System.out.println("What weapon is in your hypothesis: " );
		for (int i = 6; i < 12; i++)
		{
			System.out.println("[" + i + "]" + gameList.get(i));
		}
		
		int weaponHypothesis = scanner.nextInt();
		
		// Room
		int roomHypothesis = this.players.get(playerTurn).getSuspectPawn().getPosition().getRoomNumber() + NUM_SUSPECTS + NUM_WEAPONS - 1;
		
		Hypothesis playerHypothesis = new Hypothesis(suspectHypothesis, weaponHypothesis, roomHypothesis);
		
		checkPlayersCards(playerHypothesis, playerTurn, numPlayers);
		
		moveSuspectPawn(suspectHypothesis, roomHypothesis);

	}
	
	
	private void moveSuspectPawn(int suspectHypothesis, int roomHypothesis)
	{
		
		Player pl = null;
				
		for (Player p: this.players)
		{
			if (p.getSuspectPawn().getPawnIndex() - 1 == suspectHypothesis)
			{
				pl = p;
				
				if (pl.getSuspectPawn().getPosition().getRoomNumber() == roomHypothesis - 11)
				{
					return;
				}
			}
		}
		
		if (this.gameBoard.getSuspectPawns().get(suspectHypothesis).getPosition().getRoomNumber() == roomHypothesis - 11)
		{
			return;
		}
			
		else
		{
			Slot roomSlot = getRoomSlot(roomHypothesis, suspectHypothesis);
	
			for (int i = 0; i < this.players.size(); i++)
			{
				if (this.players.get(i).getSuspectPawn().getPawnIndex() - 1 == suspectHypothesis)
				{	
					players.get(i).getSuspectPawn().movePosition(roomSlot);
					return;
				}
			}
		
			this.gameBoard.getSuspectPawns().get(suspectHypothesis).movePosition(roomSlot);
		}
	}

	private Slot getRoomSlot(int roomHypothesis, int suspectHypothesis)
	{
		boolean slotOccupied;
		
		ArrayList<RoomSlot> roomSlots = this.gameBoard.getRoomSlots();
		
		for (RoomSlot rs: roomSlots)
		{
			slotOccupied = false;
			
			if (rs.getRoomNumber() == roomHypothesis - 11)
			{
				for (Player p: this.players)
				{
					if (p.getSuspectPawn().getPosition() == this.gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				for (int i = 0; i < NUM_SUSPECTS; i++)
				{
					if (this.gameBoard.getSuspectPawns().get(i).getPosition() == this.gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()])
					{
						slotOccupied = true;
						break;
					}
				}
				
				if (!slotOccupied)
				{
					return this.gameBoard.getSlots()[rs.getYPosition()][rs.getXPosition()];
				}
			}
		}
		
		return null;
	}
	
	private void checkPlayersCards(Hypothesis hypothesis, int playerTurn, int numPlayers)
	{
		
		ArrayList<Card> playerCards;
		
		int suspectHypothesis = hypothesis.getSuspect();
		int weaponHypothesis = hypothesis.getWeapon();
		int roomHypothesis = hypothesis.getRoom();
		
		int i = playerTurn - 1;
		
		while (i != playerTurn)
		{
				
				playerCards = players.get(i).getCards();
				
				for(int j = 0; j < playerCards.size(); j++)
				{
					if (playerCards.get(j).getCardIndex() == suspectHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}

					else if (playerCards.get(j).getCardIndex() == weaponHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}
				
					else if (playerCards.get(j).getCardIndex() == roomHypothesis)
					{
						System.out.println(players.get(i).getSuspectPawn().getName() + " refuted the hypothesis");
						updateNotebook(players.get(playerTurn), players.get(i), playerCards.get(j), suspectHypothesis, weaponHypothesis, roomHypothesis);
						return;
					}

				}
				
				i--;
				i = (i < 0) ? (i + numPlayers) : i;
		}
		
		System.out.println("The hypothesis was not refuted");
		
	}
	
	public void updateNotebook(Player accuser, Player refuter, Card cardShown, int suspectHypothesis, int weaponHypothesis, int roomHypothesis)
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
