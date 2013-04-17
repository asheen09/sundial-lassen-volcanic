package Sundial;


/**
 * double[] angOfHours;
 * 	Stores the angles made between the hour lines and the gnomon
 * 	position 0  = 6  am
 *    position 6  = 12 pm 
 *    position 12 = 6  pm 
 */

public class SundialCompute{

	private double   latitude;
	private double   longitude;
	private double[] angOfHours;
	private int      stdMeri;
	
	public SundialCompute(double lati, double longi){
		latitude = lati;
		longitude = longi;
		angOfHours = new double[13];
		stdMeri = (int)(Math.round(longitude/15) * 15);
	}
	
	/**
	 * Calculates the angle formed by each hour line with the gnomon
    *    Negative values are angles left of the gnomon
	 *    Positive values are angles right of the gnomon
	 *
	 * tan(d) = tan(t)*sin(phi)
	 * phi is the latitude of the place the sundial is located
	 * d is the angle which the hour line makes with the gnomon
	 * t is the time measured from noon in degrees of arc. 1 hour = 15 degrees
	 *
	 * Also adjusts the angle based on the longitude and standard meridian
	 * Returns the calculated angles in a double[]
	 * 
	 */
	public double[] hourAngles(){
		double tanD = 0;
		double hourOfTime = 15.00;
		double adjustmentAng = 0;
		
		/*
		 * Adjust hour angle by the difference of the longitude
		 * and respective standard meridian
		 */
		if(longitude == stdMeri){
			adjustmentAng = 0;
		}
		/**
		 * adjustmentAng will be positive if longitude is west of
       * standard meridian and negative otherwise
		 */
		else {
			adjustmentAng = stdMeri - longitude;
		}

		/**
		 * Further modify adjustmentAng with EOT here
		 */

		/*
		 * Compute angles between 6 am - 6pm
		 */
		for(int i = -6; i < 7; i++){
			if(i*hourOfTime+adjustmentAng == 90){
				angOfHours[i+6] = 90;
			}
			else{
				tanD = Math.tan(((i*hourOfTime+adjustmentAng)/180)*Math.PI)*Math.sin((Math.abs(latitude)/180)*Math.PI);
				angOfHours[i+6] = Math.toDegrees(Math.atan(tanD));
			}
		}
		/**
		 * Fix angles retrieved from atan
		 * atan returns angles between -90 to 90 only
		 *
		 * [0] = 6 am     [12] = 6 pm
		 */
		if(angOfHours[0] > 0){
			angOfHours[0] = -180 + angOfHours[0]; 
		}
		if(angOfHours[12] < 0){
			angOfHours[12] = 180 + angOfHours[12];
		}
		
		return angOfHours;
	}	
	
	public void printAngles(){
		for(int i = 0; i < 7; i++){
			System.out.println(i+6 + ": " + angOfHours[i]);
		}
		for(int i = 7; i < 13; i++){
			System.out.println(i-6 + ": " + angOfHours[i]);
		}
	}
	
	public static void main(String[] args){
		SundialCompute sc = new SundialCompute(21, -158);
		sc.hourAngles();
		sc.printAngles();
	}
}