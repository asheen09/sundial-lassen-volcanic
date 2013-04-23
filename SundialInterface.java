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
import java.util.*;

	public class SundialInterface extends Applet implements ActionListener{ // begins SundialInterface
	// Variable Declaration
		public static final String SEARCH = new String("Search");
		public static final String PRINT = new String("Print");
		private Integer iX = new Integer(0);
      private Integer iY = new Integer(0);
		private Integer iXAppletCenter = 0;
		private Integer iYAppletCenter = 0;
		private int[]    days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	// Button Data Fields
		private Button searchButton = new Button(SEARCH);
		private Button printButton = new Button(PRINT);
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
		Panel panel8 = new Panel();
	// Panel Components
		panel1.add(latitudeHere);
		panel2.add(longitudeHere);
		panel3.add(dateHere);
		panel4.add(tfLatitude);
		panel5.add(tfLongitude);
		panel6.add(tfDate);
		panel7.add(searchButton);
		panel8.add(printButton);
	// Panel Additives
		mainPanel.add(panel1);
		mainPanel.add(panel4);
		mainPanel.add(panel2);
		mainPanel.add(panel5);
		mainPanel.add(panel3);
		mainPanel.add(panel6);
		mainPanel.add(panel7);
		mainPanel.add(panel8);
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
	
	public void invalidInputMessageBox(){ // begins invalidInputMessageBox
		JOptionPane.showMessageDialog(null, "User did not enter valid input.");
	} // closes invalidInputMessageBox
	
	public void actionPerformed(ActionEvent event){ // begins actionPerformed
		String buttonName = event.getActionCommand();
		
		String latitude = tfLatitude.getText();
		String longitude = tfLongitude.getText();
		String date = tfDate.getText();
		
		double dLatitude = 0;
		double dLongitude = 0;
		int iDate = 0;
		int iLength = 0;
		
		if(searchButton.equals(event.getSource())){ // begins if - 
			iLength = latitude.length(); // latitude input validation
			if(iLength == 0){ // begins if
				invalidInputMessageBox();
			} // closes if
			iLength = longitude.length(); // longitude input validation
			if(iLength == 0){ // begins else if
				invalidInputMessageBox();
			} // closes if
			iLength = date.length(); // date input validation
			if(iLength == 0){ // begins else if
				invalidInputMessageBox();
			} // closes if
		} // closes if
		else{ // begins else
			try{ // begins try
				dLatitude = Double.parseDouble(latitude); // latitude conversion
			} // closes try
			catch(InputMismatchException ime){ // begins catch
				invalidInputMessageBox();
			} // closes catch
			catch(NumberFormatException nfe){ // begins catch
				invalidInputMessageBox();
			} // closes catch
			try{ // begins try
				dLongitude = Double.parseDouble(longitude); // longitude conversion
			} // closes try
			catch(InputMismatchException ime){ // begins catch
				invalidInputMessageBox();
			} // closes catch
			catch(NumberFormatException nfe){ // begins catch
				invalidInputMessageBox();
			} // closes catch
			if(date.substring(0,1).equals("0")){ // begins if
				date = date.substring(1, date.length());
			} // closes if
			try{ // begins try
				iDate = Integer.parseInt(date); // date conversion
			} // closes try
			catch(InputMismatchException ime){ // begins catch
				invalidInputMessageBox();
			} // closes catch
				
			/** Taken from SundialCompute.java EOT()*/
			int tempDate = iDate; // modify "date" variable to "iDate"
			int dayNum = 0;
			boolean isLeapY = false;
				
			//Extract Year
			tempDate = (tempDate/1000)*10000;
			int year = (iDate - tempDate);
			// Extract Day
			tempDate = (tempDate/1000000)*1000000 + year;
			int day =(iDate - tempDate)/10000;
			// Extract Month
			int month =  iDate/1000000;
			// Find Number of Days Past
			for(int i = 0; i < month - 1; i++){ // begins for
				dayNum = dayNum + days[i];
			} // closes for
			dayNum = dayNum + day;
			// Determine Leap Year
			if(year%4 == 0){ // begins if
				if(year%100 != 0){ // begins if
					isLeapY = true;
				} // closes if
				else if(year%400 == 0){ // begins else if
					isLeapY = true;
				} // closes else if
			} // closes if
			// Add extra day if past February on a leap year
			if(isLeapY && month > 2){ // begins if
				dayNum = dayNum + 1;
			} // closes if
		} // closes else
				
		if(printButton.equals(event.getSource())){ // begins else if
			JOptionPane.showMessageDialog(null, "(''')(O,,,O)(''')");
		} // closes else if
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