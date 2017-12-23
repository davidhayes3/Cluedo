package ie.ucd.cluedo;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TurnTest {

	Turn testTurn;
	Player testPlayer;
	int numPlayers;
	Board testBoard;
	boolean gameOver;
	int playerTurn;
	ArrayList<Player> players;
	ArrayList<Player> testPlayers;

	Game testGame;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//testPlayer = new Player(1, new Notebook());
		players = new ArrayList<Player>();
		testPlayer = new Player(1, new Notebook());
		testPlayer.giveSuspectPawn(new SuspectPawn(1, Color.BLACK));
		players.add(testPlayer);
		testGame = new Game();
		
		numPlayers = 2;
		gameOver = false;
		playerTurn = 0;
		testBoard = new Board();
		testTurn = new Turn(players, playerTurn, numPlayers, testBoard, gameOver);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBeforeRollMove() {
		boolean hasRolled = false;
		boolean output = testTurn.beforeRollMove(hasRolled, "n");
		assertEquals("Test that the player is able to pick an option before they have rolled", false, output);
	}

	@Test
	public void testBeforeRollMoveInRoom() {
		boolean hasRolled = false;
		boolean output = testTurn.beforeRollMoveInRoom(hasRolled, "f");
		assertEquals("Test that the player is able to pick an option before they have rolled and are in a room", false, output);
	}

	@Test
	public void testAfterRollMove() {
		boolean hasRolled = false;
		boolean output = testTurn.afterRollMove(hasRolled, "n");
		assertEquals("Test that the player is able to pick an option after they have rolled", false, output);
	}

	@Test
	public void testAfterRollMoveInRoom() {
		boolean hasRolled = false;
		boolean output = testTurn.afterRollMoveInRoom(hasRolled, "n");
		assertEquals("Test that the player is able to pick an option after they have rolled in the room", false, output);	
		}

	/*@Test
	public void testGetPlayerTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameOver() {
		fail("Not yet implemented");
	}
*/
}
