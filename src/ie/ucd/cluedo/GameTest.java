package ie.ucd.cluedo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {

	Game testGame;
	ArrayList<Player> testArray;
	ArrayList<Player> testPlayers;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testGame = new Game();
		testPlayers = new ArrayList<Player>();
		
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testMakePlayers() {

		testGame.makePlayers(3, testPlayers);
		
		assertEquals(1, testPlayers.get(0).getPlayerNumber());
		assertEquals(2, testPlayers.get(1).getPlayerNumber());
	}
	
	@Test
	public void testMakeBoard() {
		Board testBoard = testGame.makeBoard();
		assertTrue(testBoard instanceof Board);
	}

	@Test
	public void testCreateDeck() {
		ArrayList<Card> testDeck = testGame.createDeck();
		assertEquals(18, testDeck.size());
	}

	@Test
	public void testAllocateCards() {
		testGame.makePlayers(3, testPlayers);
		ArrayList<Card> testDeck = testGame.createDeck();
		testGame.allocateCards(testPlayers, testDeck);
		
		Card testCard;
		testCard = testPlayers.get(1).getCards().get(1);
		assertTrue(testCard instanceof Card);
	}

}