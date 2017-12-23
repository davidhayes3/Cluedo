package ie.ucd.cluedo;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SuspectPawnTest {
	
	SuspectPawn testSuspectPawn;
	BoardSlot testPosition;
	BoardButton testBoardButton;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testBoardButton = new BoardButton(0, 0);
		Color testColor = Color.RED;
		testPosition = new BoardSlot(20, 20, testBoardButton);
		testSuspectPawn = new SuspectPawn(1, testColor);
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testGetPosition() {
		
	}*/

	@Test
	public void testMovePosition() {
		testSuspectPawn.movePosition(testPosition);
		Slot output = testSuspectPawn.getPosition();
		assertEquals("The method movePosition", testPosition, output);
	}

	@Test
	public void testMoveRight() {
		testSuspectPawn.movePosition(testPosition);
		Slot output = testSuspectPawn.getPosition();
		assertEquals("The method movePosition", testPosition, output);
	}
	/*@Test
	public void testGetColor() {
		fail("Not yet implemented");
	}*/

}