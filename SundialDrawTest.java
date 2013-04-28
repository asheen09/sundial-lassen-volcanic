package sundial;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing that calcCoordinates() is returning correct coordinates for
 * Latitude  = 50
 * Longitude = 90
 * Date      = 4 27 2013
 */
public class SundialDrawTest {
	
	Double[]    angles    = {-1.5840792672084858,-1.2470214577157424,-0.9362707918624013,-0.6635414234689688,
	                         -0.42512270542379915,-0.21047465346168365,-0.007795049976836958,0.19444077525093845,
	                          0.4077367540784387,0.6438920828094231,0.913680655540756,1.2216519152098173,1.557513386381307};
	SundialDraw sd        = new SundialDraw(angles);
	Double[][]  coordinates;
	Double[][]  expectedCoor = {{-292.0,-3.87884672683936}, {-292.0,97.99055535834597},{-292.0,214.93216853069262},
			                    {-292.0,373.4993814961053},{-172.49051099976404,381.0},{-81.39634906377132,381.0},
			                    {-2.9699741960720205,381.0},{75.02988423319226,381.0},{164.57030643283665,381.0},
			                    {285.98282000077364,381.0},{292.0,225.27611683871325},{292.0,106.3052879584775},{292.0,3.8788467268394253}};
	double      coreAngle = 0.6539173987242541; 

	@Test
	public void testCalcCoordinates() {
		// displayLines() is called to get Frame dimensions for calcCoordinates()
		sd.displayLines();
		coordinates = sd.calcCoordinates(coreAngle);
		assertArrayEquals(expectedCoor, coordinates);
	}
}
