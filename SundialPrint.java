import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;


public class SundialPrint implements Printable{
	private Component c;
	
	public SundialPrint(Component c){
		this.c = c;
	}
	
	public int print(Graphics g, PageFormat format, int page)
			throws PrinterException {
		// only one page
		if(page > 0){
			return Printable.NO_SUCH_PAGE;
		}
		
		Dimension d = c.getSize();
		double imageHeight = d.getHeight();
		double imageWidth = d.getWidth();
		
		double pageHeight = format.getImageableHeight();
		double pageWidth = format.getImageableWidth();
		
		double pageX = format.getImageableX();
		double pageY = format.getImageableY();
		
		double xRatio = pageWidth/imageWidth;
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(pageX, pageY);
		g2D.scale(xRatio, xRatio);
		c.print(g2D);
		
		return Printable.PAGE_EXISTS;
	}

}
