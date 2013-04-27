package sundial;

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
		public static final String CALC = new String("Calculate");
		public static final String PRINT = new String("Print");
		private Integer iX = new Integer(0);
      private Integer iY = new Integer(0);
		private Integer iXAppletCenter = 0;
		private Integer iYAppletCenter = 0;
		private int[]    days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		private boolean allCorrect = false;

	// Button Data Fields
		private Button calcButton = new Button(CALC);
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
		tfLatitude.setText("Range: -90.0 to 90.0");
		tfLongitude.setText("Range: -180.0 to 180.0");
		tfDate.setText("MMDDYYYY");
	
	// Initialize ActionListener
		calcButton.addActionListener(this);
		printButton.addActionListener(this);
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
		panel7.add(calcButton);
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
	
	public void invalidInputMessageBox(String message){ // begins invalidInputMessageBox
		JOptionPane.showMessageDialog(null, message);
	} // closes invalidInputMessageBox
	
	public void actionPerformed(ActionEvent event){ // begins actionPerformed
		
		String buttonName = event.getActionCommand();
		
		String latitude = tfLatitude.getText();
		String longitude = tfLongitude.getText();
		String date = tfDate.getText();
		
		double dLatitude = 0;
		double dLongitude = 0;
		int iDate = 0;
		
		if(calcButton.equals(event.getSource())){ // begins if - 
			allCorrect = true;
		
			// latitude, longitude, date input length validation
			if(latitude.length() < 1 || longitude.length() < 1 || date.length() < 1){
				allCorrect = false;
				invalidInputMessageBox("Do not leave any field blank");
			} // closes if
			else{ // begins else
				try{ // begins try
					dLatitude = Double.parseDouble(latitude); // latitude conversion
					dLongitude = Double.parseDouble(longitude); // longitude conversion
					if(date.substring(0,1).equals("0")){ // begins if
						date = date.substring(1, date.length());
					} // closes if
					iDate = Integer.parseInt(date); // date conversion
				} // closes try
				catch(InputMismatchException ime){ // begins catch
					allCorrect = false;
					invalidInputMessageBox("The inserted information does not meet the requirements");
				} // closes catch
				catch(NumberFormatException nfe){ // begins catch
					allCorrect = false;
					invalidInputMessageBox("Please provide proper numbers for all fields\n" +
				                          "Latitude range: -90.0 to 90.0\n" +
												  "Longtidue range: -180.0 to 180.0");
				} // closes catch
			}
			
			// sanitization for latitude and longitude data
			if(dLongitude < -180 || dLongitude > 180){
				allCorrect = false;
				invalidInputMessageBox("Please ensure longitude is in range: -180.0 to 180.0");
			}
			if(dLatitude < -90 || dLatitude > 90){
				allCorrect = false;
				invalidInputMessageBox("Please ensure latitude is in range: -90.0 to 90.0");
			}
					
			/** Taken from SundialCompute.java EOT()*/
			int tempDate = iDate; // modify "date" variable to "iDate"
			int dayNum = 0;
			boolean isLeapY = false;
				
			//Extract Year
			tempDate = (tempDate/10000)*10000;
			int year = (iDate - tempDate);
			// Extract Day
			tempDate = (tempDate/1000000)*1000000 + year;
			int day =(iDate - tempDate)/10000;
			// Extract Month
			int month =  iDate/1000000;
			
			// sanitization for month and day of the month
			if(month < 1 || month > 12){
				allCorrect = false;
				invalidInputMessageBox("Please enter a month between 1 and 12\n" +
				                       "Format: MMDDYYYY");
			}
			else if(day < 1 || day > days[month-1]){
				allCorrect = false;
				invalidInputMessageBox("Please enter a date within the given month's range\n" + 
				                       "Format: MMDDYYYY");
			}
			else{
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
		}
		if(printButton.equals(event.getSource())){ // begins else if
			if(allCorrect){
				JOptionPane.showMessageDialog(null, "(''')(O,,,O)(''')");
			}
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