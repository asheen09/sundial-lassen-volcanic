package sundial;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * Testing correctness of calculations for: 
 * 1) hourAngles()
 * 2) intoRadians()
 * 3) EOT()
 * Given:
 * Latitude = 50
 * Longitude = 90
 * Month = 4
 * Day = 27
 * Year = 2013
 */
public class SundialComputeTest {

	SundialCompute sc;
	Double[] angOfHours;
	Double[] correctDegreeAng = {-90.76105642522242,-71.44906648936369,-53.64436485508714,-38.018123096874824,
			                     -24.357736795967043,-12.059309337833035,-0.4466234647663078,11.140635787130565,
		                          23.36159516105809,36.892298806868055,52.350045385231674,69.99549877559643,89.23894357477757};
	Double[] correctRadianAng = {-1.5840792672084858,-1.2470214577157424,-0.9362707918624013,-0.6635414234689688,
			                     -0.42512270542379915,-0.21047465346168365,-0.007795049976836958,0.19444077525093845,
			                      0.4077367540784387,0.6438920828094231,0.913680655540756,1.2216519152098173,1.557513386381307};
	double E = 0.5830172124;
	
	@Before
	public void setUp() throws Exception {
		sc = new SundialCompute(50, 90, 4, 27, 2013);
	}

	@Test
	public void testHourAngles() {
		angOfHours = sc.hourAngles();
		assertArrayEquals(correctDegreeAng, angOfHours);
	}

	@Test
	public void testIntoRadians() {
		angOfHours = sc.intoRadians(sc.hourAngles());
		assertArrayEquals(correctRadianAng, angOfHours);
	}

	@Test
	public void testEOT() {
		assertEquals(E, sc.EOT(), .0000000001);
	}

}
