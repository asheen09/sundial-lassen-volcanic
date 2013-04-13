package Sundial;

/** Java Imports */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JOptionPane;

	public class SundialInterface extends Applet implements ActionListener{ // begins SundialInterface
	// Variable Declaration
		public static final String SEARCH = new String("Search");
		private Integer iX = new Integer(0);
      private Integer iY = new Integer(0);
		private Integer iXAppletCenter = 0;
		private Integer iYAppletCenter = 0;
	// Button Data Fields
		private Button searchButton = new Button(SEARCH);
	// Label Data Field
		private Label latitudeHere = new Label("Latitude: ");
		private Label longitudeHere = new Label("Longitude: ");
		private Label dateHere = new Label("Date: ");
	// Text Field - User Input
		private TextField tfLatitude = new TextField(20);
		private TextField tfLongitude = new TextField(20);
		private TextField tfDate = new TextField(20);
	
	public void init(){ // begins init()
	// Initialize ActionListener
		searchButton.addActionListener(this);
	// Panels
		Panel mainPanel = new Panel();
	// Panel Dimensions
		mainPanel.setSize(200, 300);
	// Arrangment (4 rows by 3 columns);
		GridLayout layout = new GridLayout(4, 2);
		mainPanel.setLayout(layout);
	// Top Panel
		this.add(mainPanel);
	// 8 Panels
		Panel panel1 = new Panel();
		Panel panel2 = new Panel();
		Panel panel3 = new Panel();
		Panel panel4 = new Panel();
		Panel panel5 = new Panel();
		Panel panel6 = new Panel();
		Panel panel7 = new Panel();
	// Panel Components
		panel1.add(latitudeHere);
		panel2.add(longitudeHere);
		panel3.add(dateHere);
		panel4.add(tfLatitude);
		panel5.add(tfLongitude);
		panel6.add(tfDate);
		panel7.add(searchButton);
	// Panel Additives
		mainPanel.add(panel1);
		mainPanel.add(panel4);
		mainPanel.add(panel2);
		mainPanel.add(panel5);
		mainPanel.add(panel3);
		mainPanel.add(panel6);
		mainPanel.add(panel7);
	// (Get) Applet Size
		int iWidth = this.getWidth();
		int iHeight = this.getHeight();
		final int TWO = 2;
	// Center X & Y
		iXAppletCenter = iWidth/TWO;
		iYAppletCenter = iHeight/TWO;
		iX = iXAppletCenter;
		iY = iYAppletCenter;
	} // closes init()
	
	public void actionPerformed(ActionEvent event){ // begins actionPerformed
		String buttonName = event.getActionCommand();
		
		String latitude = tfLatitude.getText();
		String longitude = tfLongitude.getText();
		String date = tfDate.getText();
			
	} // closes actionPerformed
	
	public void paint(Graphics window){ // begins paint
/**	// Instantiate Window
		SundialInterface sundialApplet = new SundialInterface();
	// Set Window
		window.setColor(Color.black);
	//
*/	
	} // closes paint

		} // closes SundialInterface