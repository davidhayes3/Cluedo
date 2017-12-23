package ie.ucd.cluedo;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MoveTypeTest {

	MoveType testMoveType;
	Board testBoard;
	ArrayList<Player> players;
	Player testPlayer;
	SuspectPawn testPawn;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testPawn = new SuspectPawn(1, Color.BLACK);
		players = new ArrayList<Player>();
		testPlayer = new Player(1, new Notebook());
		testPlayer.giveSuspectPawn(testPawn);
		players.add(testPlayer);
		testBoard = new Board();
		testMoveType = new MoveType();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSecretRoomMove() {
		testPlayer.getSuspectPawn().movePosition(this.testBoard.getSlots()[0][3]);
		int output = testMoveType.secretRoomMove(players, 0, testBoard, 8, "p");
		assertEquals("", 7, output);
	}

	@Test
	public void testBoardMove() {
		testPlayer.getSuspectPawn().movePosition(this.testBoard.getSlots()[0][9]);
		int output = testMoveType.boardMove(players, 0, testBoard, 8, "l");
		int outputXPosition = testPlayer.getSuspectPawn().getPosition().getXPosition();
		assertEquals("The moves have been decremented correctly", 7,output);
		assertEquals("The x position update is correct", 8, outputXPosition);
		
	}

	@Test
	public void testCanMove() {
		
		boolean output = testMoveType.canMove(0, 7, testBoard, players);
		assertEquals("", true, output);
	}

}
