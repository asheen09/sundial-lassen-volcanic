package Sundial;

/**
 * double[] angOfHours;
 * 	Stores the angles made between the hour lines and the gnomon
 * 	position 0 = 12 am; position 12 = 12 pm
 */

public class SundialCompute{

	private float latitude;
	private float longitude;
	private double[] angOfHours;
	
	public SundialCompute(float lati, float longi){
		latitude = lati;
		longitude = longi;
		angOfHours = new double[24];
	}
	
	/**
	 * Calculates the angle of an hour line with the gnomon
	 *
	 * tan(d) = tan(t)*sin(phi)
	 * phi is the latitude of the place the sundial is located
	 * d is the angle which the hour line makes with the gnomon
    * t is the time measured from noon in degrees of arc.
	 */
	public void anglesFor(float phi){
		double tanD = 0;
		
		for(int i = 0; i < 24; i++){
		} 	 	
	}
}