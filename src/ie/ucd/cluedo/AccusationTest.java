package ie.ucd.cluedo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccusationTest {

	Accusation testAccusation;
	int testSuspect;
	int testRoom;
	int testWeapon;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testSuspect = 2;
		testRoom = 12;
		testWeapon = 7;
		testAccusation = new Accusation(testSuspect, testWeapon, testRoom);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSuspect() {
		int output = testAccusation.getSuspect();
		assertEquals("The getSuspectMethod works", 2, output);
	}

	@Test
	public void testGetWeapon() {
		int output = testAccusation.getWeapon();
		assertEquals("The getWeaponMethod works", 7, output);
	}

	@Test
	public void testGetRoom() {
		int output = testAccusation.getRoom();
		assertEquals("The getRoomMethod works", 12, output);
	}

}