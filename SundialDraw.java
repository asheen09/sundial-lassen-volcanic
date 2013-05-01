package sundial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Takanori
 * 
 * This class draws out the hour lines for the sundial.  
 */
public class SundialDraw extends JPanel{
	private Double[] angles;
	private JFrame frame;
	
	/*
	 * Angles passed must be in radians.  Assumes indices 0-12 correspond to hours 6am - 6pm.
	 * Angles are from gnomon.
	 */
	public SundialDraw(Double[] angles){
		this.angles = angles;
	}
	
	/*
	 * Displays hour lines in a new window.
	 */
	public void displayLines(){
		frame = new JFrame();
		frame.add(this); 
		frame.setSize(600, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/*
	 * Calls up printing prompt for hour lines.
	 */
	public void printSundial(){
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat preformat = pjob.defaultPage();
		PageFormat postformat = pjob.pageDialog(preformat);
		//If user does not hit cancel then print.
		if (preformat != postformat) {
		    //Set print component
		    pjob.setPrintable(new SundialPrint(frame), postformat); //this is what's needed to integrate it
		    if (pjob.printDialog()) {
		        try {
					pjob.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
	}
	
	/*
	 * Overrides paintComponent.  Draws hour lines using coordinate system.  Coordinate system translated
	 * to be like commonly used coordinate system rather than default.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// calculate coordinates of endpoints of lines with respect to frame size (will scale to paper)
		Double coreAngle = Math.atan((this.getWidth()/2.0) / (this.getHeight()/2.0)); //angle to corner of page
		Double[][] endpoints = calcCoordinates(coreAngle);
		Line2D[] lines = new Line2D[13];
		int[] hours = new int[]{6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6};
		
		// translate origin to center, and make y increase upward and x increase rightward
		Graphics2D img = (Graphics2D) g;
		img.translate(this.getWidth()/2.0, this.getHeight()/2.0); //component should be drawing window
		
		img.setFont(img.getFont().deriveFont((float) 20.0));
		for(int i=0; i<lines.length; i++){
			if((endpoints[i][0] != null) && (endpoints[i][1] != null)){
				if(angles[i] < 0){ //x is fine
					if(angles[i] > -Math.PI/2){ //shift y down
						img.drawString(hours[i] + "", (int)(endpoints[i][0] + 0), (int)((-endpoints[i][1]) + 15));
					}else{ //shift y up
						img.drawString(hours[i] + "", (int)(endpoints[i][0] + 0), (int)((-endpoints[i][1]) - 15));
					}
				}else{ //shift x left so number is on image
					if(angles[i] > Math.PI/2){ //shift y up
						img.drawString(hours[i] + "", (int)(endpoints[i][0] - 15), (int)((-endpoints[i][1]) - 15));
					}else{ //shift y down
						img.drawString(hours[i] + "", (int)(endpoints[i][0] - 15), (int)((-endpoints[i][1]) + 15));
					}
				}
			}
		}
		
		img.scale(1.0, -1.0);
		
		for(int i=0; i<lines.length; i++){
			if((endpoints[i][0] != null) && (endpoints[i][1] != null)){
				lines[i] = new Line2D.Double(0, 0, endpoints[i][0], endpoints[i][1]);
			}
		}
		
		for(int i=0; i<lines.length; i++){
			if(lines[i] != null){
				img.draw(lines[i]);
			}
		}
	}
	
	/*
	 * Calculates coordinates to draw lines with.  Core angle is angle to corner of image.
	 */
	public Double[][] calcCoordinates(double coreAngle){
		Double[][] coordinates = new Double[13][2];
		double x;
		double y;
		
		for(int i=0; i<angles.length; i++){
			if(((angles[i] < coreAngle) && (angles[i] > -coreAngle)) || (angles[i] < -(Math.PI - coreAngle)) ||
					(angles[i] > (Math.PI - coreAngle))){
				// y coordinate will be maximized
				if((angles[i] < Math.PI/2) && (angles[i] > -Math.PI/2))
					y = this.getHeight() / 2.0;
				else
					y = this.getHeight()/ -2.0;
				x = Math.tan(angles[i]) * y;
				coordinates[i][0] = x;
				coordinates[i][1] = y;
			}else{
				// x coordinate will be maximized
				if(angles[i] > 0){
					x = this.getWidth() / (2.0);
					y = Math.tan((Math.PI / 2) - angles[i]) * x;
				}else{
					x = this.getWidth() / (-2.0);
					y = Math.tan((Math.PI / 2) + angles[i]) * -x;
				}	
				
				coordinates[i][0] = x;
				coordinates[i][1] = y;
			}

		}
		return coordinates;
	}	
}
