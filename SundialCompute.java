package Sundial;


/**
 * double[] angOfHours;
 * 	Stores the angles made between the hour lines and the gnomon
 * 	position 0 = 12 pm; position 6 = 6 am
 */

public class SundialCompute{

	private float latitude;
	private float longitude;
	private double[] angOfHours;
	
	public SundialCompute(float lati, float longi){
		latitude = lati;
		longitude = longi;
		angOfHours = new double[7];
	}
	
	/**
	 * Calculates the angle of an hour line with the gnomon
	 *
	 * tan(d) = tan(t)*sin(phi)
	 * phi is the latitude of the place the sundial is located
	 * d is the angle which the hour line makes with the gnomon
	 * t is the time measured from noon in degrees of arc. 1 hour = 15 degrees
	 */
	public void hourAngles(){
		double tanD = 0;
		angOfHours[6] = 90;
		
		for(int i = 0; i < 6; i++){
			tanD = Math.tan((i*15.00/180)*Math.PI)*Math.sin((latitude/180)*Math.PI);
			angOfHours[i] = Math.toDegrees(Math.atan(tanD));
		} 	 	
	}
	
	public void printAngles(){
		for(int i = 0; i < 7; i++){
			System.out.println((12-i) + ": " + angOfHours[i]);
		}
	}
	
	public static void main(String[] args){
		SundialCompute sc = new SundialCompute(52, 100);
		sc.hourAngles();
		sc.printAngles();
	}
}