package Sundial;


/**
 * double[] angOfHours;
 * 	Stores the angles made between the hour lines and the gnomon
 * 	position 0  = 6  am
 *    position 6  = 12 pm 
 *    position 12 = 6  pm 
 *
 * double[] standardMeri;
 * 	Stores the list of standard meridians (west) 
 * 	0 = 52.5 Newfoundland; 1 = 60 Atlantic;       2 = 75 Eastern
 * 	3 = 90 Central;        4 = 105 Mountain;      5 = 120 Pacific
 * 	6 = 135 Yukon;         7 = 150 Alaska-Hawaii; 8 = 165 Bering
 */

public class SundialCompute{

	private float latitude;
	private float longitude;
	private double[] angOfHours;
	private double[] stdMeriList;
	
	public SundialCompute(float lati, float longi){
		latitude = lati;
		longitude = longi;
		angOfHours = new double[13];
		stdMeriList = new double[9];
		
		stdMeriList[0] = 52.5;
		for(int i = 1; i < 9; i++){
			stdMeriList[i] = 45 + 15*i; 
		}
	}
	
	/**
	 * Calculates the angle of an hour line with the gnomon
	 *
	 * tan(d) = tan(t)*sin(phi)
	 * phi is the latitude of the place the sundial is located
	 * d is the angle which the hour line makes with the gnomon
	 * t is the time measured from noon in degrees of arc. 1 hour = 15 degrees
	 *
	 * Also adjusts the angle based on the longitude and standard meridian
	 * 
	 */
	public void hourAngles(){
		double tanD = 0;
		double hourOfTime = 15.00;
		double stdMeridian = 0;
		double adjustmentAng = 0;
		
		
		/** 
		 * Newfoundland's standard meridian is 52.5
		 * Less than 60 and not a multiple of 15
		 * Fails on input of 180 or -180 < will fix later
		 */ 
		if(Math.abs(longitude) < 60){
			stdMeridian = stdMeriList[0];
		}
		else{
			stdMeridian = stdMeriList[(int)(Math.abs(longitude) / 15) - 3];
		} 
		
		/*
		 * Adjust hour angle by the difference of the longitude
		 * and respective standard meridian
		 */
		if(longitude < 0){
			adjustmentAng = longitude + stdMeridian;
		}
		else{
			adjustmentAng = longitude - stdMeridian;
		}

		/*
		 * Compute angles between 6 am - 11 am
		 */
		for(int i = 1; i < 7; i++){
			if(i*hourOfTime+adjustmentAng == 90){
				angOfHours[6-i] = 90;
			}
			else{
				tanD = Math.tan(((i*hourOfTime+adjustmentAng)/180)*Math.PI)*Math.sin((latitude/180)*Math.PI);
				angOfHours[6-i] = Math.toDegrees(Math.atan(tanD));
			}
		}
		
		/*
		 * may have to calculate 12 pm differently
	    * if adjustmentAngle < 0, shift 12 pm to the left of middle
		 * else, shift 12 pm to the right of middle
		 */
		
		/*
		 * Compute angles between 1 pm - 6 pm
		 */
		for(int i = 0; i < 7; i++){
			if(i*hourOfTime-adjustmentAng == 90){
				angOfHours[i+6] = 90;
			}
			else{
				tanD = Math.tan(((i*hourOfTime-adjustmentAng)/180)*Math.PI)*Math.sin((latitude/180)*Math.PI);
				angOfHours[i+6] = Math.toDegrees(Math.atan(tanD));
			}
		} 	 	
	}
	
	public void printAngles(){
		for(int i = 0; i < 7; i++){
			System.out.println(i+6 + ": " + angOfHours[i]);
		}
		for(int i = 7; i < 13; i++){
			System.out.println(i-6 + ": " + angOfHours[i]);
		}
	}

	public double[] getAngles(){
		return angOfHours;
	}
	
	public static void main(String[] args){
		SundialCompute sc = new SundialCompute(50, -96);
		sc.hourAngles();
		sc.printAngles();
	}
}