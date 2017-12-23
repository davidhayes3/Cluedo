package ie.ucd.cluedo;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	
	Player player1;
	Card testCard1;
	Card testCard2;
	ArrayList<Card> playerCards;
	SuspectPawn testPawn;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Notebook notebook = new Notebook();
		player1 = new Player(1,notebook);
		testCard1 = new Card(1);
		testCard2 = new Card(2);
		testPawn = new SuspectPawn(1, Color.black);
		playerCards = new ArrayList<Card>();
		playerCards.add(testCard1);
		playerCards.add(testCard2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPlayerNumber() {
		int output = player1.getPlayerNumber();
		assertEquals("getPlayerNumber method workiign correctly", 1, output);
	}
	
	@Test
	public void testGiveCard() {
		player1.giveCard(testCard1);
		Card output = player1.getCards().get(0);  
		assertEquals("giveCard method working correctly", testCard1, output);
	}
	
	@Test
	public void testGetCards() {
		player1.giveCard(testCard1);
		player1.giveCard(testCard2);
		ArrayList<Card> output = player1.getCards();
		assertEquals("Get Cards method working correctly", playerCards, output);
	}
	
	@Test
	public void testGivePawn() {
		player1.giveSuspectPawn(testPawn);
		SuspectPawn output = player1.getSuspectPawn();
		assertEquals("Get Cards method working correctly", testPawn, output);
	}

}
