package sundial;

import java.applet.Applet;
import java.awt.*;

public class Sundial extends Applet{
	Panel container = new Panel();
	SundialInterface sunInt = new SundialInterface();
	
	public void init(){
		container.setLayout(new GridLayout(1,0));
		container.add(sunInt);
		add(container);
		
		sunInt.init();
	
	}
}
