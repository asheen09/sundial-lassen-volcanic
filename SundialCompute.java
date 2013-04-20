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
	private int      date;
	
	public SundialCompute(double lati, double longi, int date){
		latitude = lati;
		longitude = longi;
		angOfHours = new double[13];
		stdMeri = (int)(Math.round(longitude/15) * 15);
		this.date = date;

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
		 *
		 * adjustmentAng will be positive if longitude is west of
       * standard meridian and negative otherwise
		 */
		if(longitude == stdMeri){
			adjustmentAng = 0;
		}
		else {
			adjustmentAng = stdMeri - longitude;
		}

		//compute EOT		 

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
	
	/**
	 * Accepts a double[] of angles in degrees
	 * Returns the double[] of angles in radians 
	 */
	public double[] intoRadians(double[] angles){
		for(int i = 0; i < angles.length; i++){
			angles[i] = Math.toRadians(angles[i]);
		}
		return angles;
	}
	
	/**
	  * Further refine adjustmentAng based on EOT
	  * This is done to correct for the Earth's non-circular orbit 
	  * Sundials are "slow" in Jan, Feb, Mar, Apr, July, Sep
	  *              "fast" in May, June, Oct, Nov, Dec 
	  *
	  * E = 9.87 * sin (2B) - 7.53 * cos (B) - 1.5 * sin (B)
	  * B = 360 * (N - 81) / 365
	  * N = day number, January 1 = day 1
	  *
	  * The day number can change based on leap year
	  */
	public void EOT(){
		int tempDate = date;
		
		// Extracts the year
		tempDate = (tempDate/10000)*10000;
		int year = (date - tempDate);
		
		// Extracts the day
		tempDate = (tempDate/1000000)*1000000 + year;
		int day = (date - tempDate)/10000;
		
		//Extracts the month
		int month = date/1000000;
		
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
		SundialCompute sc = new SundialCompute(21, -158, 4172013);
		sc.intoRadians(sc.hourAngles());
		sc.printAngles();
	}
}