package sundial;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing that the following data is verified correctly
 * Latitude is between -90 and 90
 * Longitude is between -180 and 180
 * Month is between 1 and 12
 * Day is possible for the given month
 */

public class SundialInterfaceTest {

	SundialInterface si;
	double goodLatitude;
	double badLatitude;
	double goodLongitude;
	double badLongitude;
	int    goodMonth;
	int    badMonth;
	int    goodDay;
	int    badDay;
	int    year;


	@Before
	public void setUp() throws Exception {
		si            = new SundialInterface();
		goodLatitude  = 50;
		badLatitude   = -100;
		goodLongitude = 90;
		badLongitude  = 200;
		goodMonth     = 4;
		badMonth      = 20;
		goodDay       = 27;
		badDay        = 40;
		year          = 2013;
	}

	@Test
	public void testVerifyData() {
		// User supplied data is all correct
		assertEquals(true,si.verifyData(goodLatitude, goodLongitude, goodMonth, goodDay, year));
		// User supplied data is incorrect for latitude
		assertEquals(false,si.verifyData(badLatitude, goodLongitude, goodMonth, goodDay, year));
		// User supplied data is incorrect for longitude
		assertEquals(false,si.verifyData(goodLatitude, badLongitude, goodMonth, goodDay, year));
		// User supplied data is incorrect for month
		assertEquals(false,si.verifyData(goodLatitude, goodLongitude, badMonth, goodDay, year));
		// User supplied data is incorrect for day
		assertEquals(false,si.verifyData(goodLatitude, goodLongitude, goodMonth, badDay, year));
		// User supplied data is all incorrect (except year);
		assertEquals(false,si.verifyData(badLatitude, badLongitude, badMonth, badDay, year));
	}

}
