package sundial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * @author Takanori
 * 
 * This class draws out the gnomon for the sundial.
 */
public class SundialGnomon extends JPanel{
	private double gnomonAngle;
	JFrame frame;
	
	/*
	 * Angle is the latitude.
	 */
	public SundialGnomon(double angle){
		gnomonAngle = angle;
	}
	
	/*
	 * Displays gnomon in a new window.
	 */
	public void displayGnomon(){
		frame = new JFrame();
		frame.add(this); 
		frame.setSize(600, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/*
	 * Calls up printing prompt for gnomon.
	 */
	public void printGnomon(){
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
	 * Draws gnomon with base at bottom.  Again, coordinate system is translated to commonly used coordinate system.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D img = (Graphics2D) g;
		
		img.translate(0, this.getHeight());
		img.drawString("Gnomon angle", 20, -5);
		img.scale(1.0, -1.0);
		double x, y;
		
		// baseLine is the bottom, hLine is the height of the gnomon, angleLine is the style (diagonal portion)
		Line2D baseLine, hLine, angleLine;
		
		if(gnomonAngle < Math.atan(this.getHeight() / (this.getWidth()/2))){ //style will extend to center of image
			y = Math.tan(gnomonAngle) * (this.getWidth() / 2);
			baseLine = new Line2D.Double(0, 0, this.getWidth()/2, 0);
			hLine = new Line2D.Double(this.getWidth()/2, 0, this.getWidth()/2, y);
			angleLine = new Line2D.Double(0, 0, this.getWidth()/2, y);
		}else{ //style needs to be closer to maintain the angle
			x = this.getHeight() / Math.tan(gnomonAngle);
			baseLine = new Line2D.Double(0, 0, x, 0);
			hLine = new Line2D.Double(x, 0, x, this.getHeight());
			angleLine = new Line2D.Double(0, 0, x, this.getHeight());
		}
		
		img.draw(baseLine);
		img.draw(hLine);
		img.draw(angleLine);		
	}
}
