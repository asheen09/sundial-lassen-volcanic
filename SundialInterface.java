package sundial;

/** Java Imports */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.*;

	public class SundialInterface implements ActionListener{ // begins SundialInterface
	
	// Variable Declaration
		public static final String CREATE = new String("Create");
		public static final String PRINT = new String("Print");
		private int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		private boolean allCorrect = false;
		private SundialCompute sunComp;
		private SundialDraw sunDraw;
		private SundialGnomon sunGnom;

	// Button Data Fields
		private Button createButton = new Button(CREATE);
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
		createButton.addActionListener(this);
		printButton.addActionListener(this);
		
		JFrame mainFrame = new JFrame("Sundial");
		Panel mainPanel = new Panel();
	// Frame initialization
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400, 200);
	
	// Arrangement (4 rows by 2 columns);
		GridLayout layout = new GridLayout(4, 2);
		mainPanel.setLayout(layout);
	// Top Panel
		mainFrame.add(mainPanel);
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
		panel7.add(createButton);
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
		
	} // closes init()
	
	public void invalidInputMessageBox(String message){ // begins invalidInputMessageBox
		JOptionPane.showMessageDialog(null, message);
	} // closes invalidInputMessageBox
	
	
	/**
	 * Verifies the following data for correctness:
	 * 1) Latitude
	 * 2) Longitude
	 * 3) Month
	 * 4) Day
	 * 5) Year
	 * 
	 * Returns a boolean, isCorrect, for JUnit test purposes
	 */
	public boolean verifyData(double latitude, double longitude, int month, int day, int year){
		
		boolean isCorrect = true;
		
		// Verify latitude and longitude data are within bounds
		if(longitude < -180 || longitude > 180){
			allCorrect = false;
			isCorrect  = false;
		    invalidInputMessageBox("Please ensure longitude is in range: -180.0 to 180.0");
	    }
		if(latitude < -90 || latitude > 90){
			allCorrect = false;
			isCorrect  = false;
			invalidInputMessageBox("Please ensure latitude is in range: -90.0 to 90.0");
		}
		// Verify month and day are within bounds
		if(month < 1 || month > 12){
			allCorrect = false;
			isCorrect  = false;
			invalidInputMessageBox("Please enter a month between 1 and 12\n" +
						           "Date: MMDDYYYY");
		}
		else if(day < 1 || day > days[month-1]){
			allCorrect = false;
			isCorrect  = false;
			invalidInputMessageBox("Please enter a date within the given month's range\n" + 
						           "Date: MMDDYYYY");
		}
		return isCorrect;
	}
	
	public void actionPerformed(ActionEvent event){ // begins actionPerformed
		
		String latitude = tfLatitude.getText();
		String longitude = tfLongitude.getText();
		String date = tfDate.getText();
		
		double dLatitude = 0;
		double dLongitude = 0;
		int iDate = 0;
		int month = 0;
		int day = 0;
		int year = 0;
		
		if(createButton.equals(event.getSource())){ // begins if - 
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
					if(date.length() < 7){
						allCorrect = false;
						invalidInputMessageBox("There is not enough data to determine the date\n" +
						                       "Date: MMDDYYYY");
					}
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
									       "Longtidue range: -180.0 to 180.0\n" + 
				                           "Date: MMDDYYYY");
				} // closes catch
			}
						
			/** Taken from SundialCompute.java EOT()*/
			// Only call verifyData() if dLatitude, dLongitude, and iDate
			// if they actual numbers
			if(allCorrect){
				int tempDate = iDate; // modify "date" variable to "iDate"
				
				//Extract Year
				tempDate = (tempDate/10000)*10000;
				year     = (iDate - tempDate);
				// Extract Day
				tempDate = (tempDate/1000000)*1000000 + year;
				day      = (iDate - tempDate)/10000;
				// Extract Month
				month    =  iDate/1000000;
			
				verifyData(dLatitude, dLongitude, month, day, year);
			}
				
			if(allCorrect){
				Double[] angles;
				
				sunComp = new SundialCompute(dLatitude, dLongitude, month, day, year);
				angles  = sunComp.hourAngles();

				sunDraw = new SundialDraw(sunComp.intoRadians(angles));
				sunGnom = new SundialGnomon(Math.toRadians(dLatitude));
				sunGnom.displayGnomon();
				sunDraw.displayLines();
			}
		}
		if(printButton.equals(event.getSource())){ // begins else if
			if(allCorrect){	
				sunDraw.printSundial();
				sunGnom.printGnomon();
			}
		} // closes else if
	} // closes actionPerformed

	public static void main(String args[]){
		SundialInterface si = new SundialInterface();
		si.init();
	}
} // closes SundialInterface