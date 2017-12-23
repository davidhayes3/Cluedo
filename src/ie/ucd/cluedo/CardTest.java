package ie.ucd.cluedo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static ie.ucd.cluedo.GameValues.*;

public class CardTest {

	Card testCard;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testCard = new Card(4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetType() {
		CardType output = testCard.getType();
		assertEquals("The method getType works", CardType.SUSPECT, output);
	}

	@Test
	public void testGetName() {
		Name output = testCard.getName();
		assertEquals("The method getName works", Name.COLONEL_MUSTARD, output);
	}

	@Test
	public void testGetCardIndex() {
		int output = testCard.getCardIndex();
		assertEquals("The method getcardIndexis working", 4, output);
	}

}