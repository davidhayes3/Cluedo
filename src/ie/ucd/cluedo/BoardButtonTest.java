package ie.ucd.cluedo;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardButtonTest {
	
	BoardButton testBoardButton;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int testCol = 20;
		int testRow = 20;
		testBoardButton = new BoardButton(testCol,testRow); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChangeColor() {
		testBoardButton.changeColor(Color.RED);
		Color output = testBoardButton.getBackground();
		assertEquals(Color.RED, output);
	}
	
	@Test
	public void testResetDefault() {
		testBoardButton.changeColor(Color.RED);
		testBoardButton.resetDefault();
		Color output = testBoardButton.getBackground();
		assertEquals(Color.YELLOW, output);
	}
	

}