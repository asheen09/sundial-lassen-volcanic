import java.awt.*;
import java.awt.geom.Line2D;
import java.lang.Math;
import javax.swing.JPanel;

// Error parts have placeholder information for now
public class SundialDraw extends JPanel{
	private Double[] angles;
	private double xOrigin;
	private double yOrigin;
	private double pageWidth;
	private double pageHeight;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// translate origin to bottom center, and make y increase upward and x increase rightward
		Graphics2D img = (Graphics2D) g;
		img.translate(component.getWidth()/2.0, component.getHeight()/2.0);
		img.scale(1.0, -1.0);
		
		// calculate coordinates of endpoints of lines with respect to paper size(or just in general)
		Double[][] endpoints = calcCoordinates();
		Line2D[] lines = new Line2D[12];
		
		for(int i=0; i<lines.length; i++){
			if((endpoints[i][0] != null) && (endpoints[i][1] != null)){
				lines[i] = new Line2D.Double(xOrigin, yOrigin, endpoints[i][0], endpoints[i][1]);
			}
		}
		
		for(int i=0; i<lines.length; i++){
			if(lines[i] != null){
				img.draw(lines[i]);
			}
		}
	}
	
	public Double[][] calcCoordinates(){
		Double coreAngle = something;
		Double[][] coordinates = new Double[12][2];
		double x;
		double y;
		
		for(int i=0; i<angles.length; i++){
			if(angles[i] < coreAngle){
				// y coordinate will be maximized
				x = Math.tan(angles[i]) * pageHeight;
				coordinates[i][0] = x;
				coordinates[i][1] = pageHeight;
			}else{
				// x coordinate will be maximized
				y = Math.tan(90degrees - angles[i]) * (pageWidth / 2);
				coordinates[i][0] = pageWidth / 2;
				coordinates[i][1] = y;
			}
		}
		
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(pageWidth, pageHeight);
	}
	
}
