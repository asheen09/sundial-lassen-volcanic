package sundial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class SundialGnomon extends JPanel{
	private double gnomonAngle;
	JFrame frame;
	
	public SundialGnomon(double angle){
		gnomonAngle = angle; //whatever method you can make to pass the latitude as an angle
	}
	
	public void displayGnomon(){
		frame = new JFrame();
		frame.add(this); //this is what's needed to integrate it
		frame.setSize(600, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D img = (Graphics2D) g;
		
		img.translate(0, this.getHeight());
		img.drawString("Gnomon angle", 20, -5);
		img.scale(1.0, -1.0);
		double x, y;
		
		Line2D baseLine, hLine, angleLine;
		
		if(gnomonAngle < Math.atan(this.getHeight() / (this.getWidth()/2))){
			y = Math.tan(gnomonAngle) * (this.getWidth() / 2);
			baseLine = new Line2D.Double(0, 0, this.getWidth()/2, 0);
			hLine = new Line2D.Double(this.getWidth()/2, 0, this.getWidth()/2, y);
			angleLine = new Line2D.Double(0, 0, this.getWidth()/2, y);
		}else{
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
