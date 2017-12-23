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
		testArray = new ArrayList<Player>();
		testPlayers = new ArrayList<Player>();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testGetNumPlayers() {

	}*/

	@Test
	public void testMakePlayers() {

		testPlayers = testGame.makePlayers(3, testArray);
		
		assertEquals(1, testPlayers.get(0).getPlayerNumber());
		assertEquals(2, testPlayers.get(1).getPlayerNumber());
	}
	
	/*
	@Test
	public void testGetCharacters() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testMakeBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateDeck() {
		ArrayList<Card> testDeck = testGame.createDeck();
		assertEquals(18, testDeck.size());
	}

	@Test
	public void testAllocateCards() {
		testPlayers = testGame.makePlayers(3, testArray);
		ArrayList<Card> testDeck = testGame.createDeck();
		testGame.allocateCards(testPlayers, 3);
		
		Card testCard;
		testCard = testPlayers.get(1).getCards().get(1);
		assertTrue(testCard instanceof Card);
	}

	@Test
	public void testGameTurns() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintMurderDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintDeckInPlayDetails() {
		fail("Not yet implemented");
	}

}