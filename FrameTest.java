import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class FrameTest extends JFrame implements ActionListener {
	// My GUI variables are declared and initialized here, with suitable variable
	// names. Necessary imports are above.
	JButton fileButton = new JButton("Choose file");
	JButton quitButton = new JButton("Quit");
	JComboBox<String> x = new JComboBox<String>();
	JComboBox <String> y = new JComboBox<String>();
	JTextField title = new JTextField(16);
	static JTextField mouseListener = new JTextField(16);
	private ScatterPlotComponent scatter;	

	// A public method to create GUI using BorderLayout. southPanel uses GridLayout, setting rows to 1 and columns to 3 to provide equal space
	// for JComboBoxes and JLabel.
	public FrameTest() {
		
		 JPanel mainPanel = new JPanel(new BorderLayout());
		 JPanel northPanel = new JPanel();
		 mainPanel.add(northPanel, BorderLayout.NORTH);
		 mainPanel.setBackground(Color.white);
		 JPanel southPanel = new JPanel(new GridLayout(1, 3, 6, 6));
		 mainPanel.add(southPanel, BorderLayout.PAGE_END);
		 JPanel centerPanel = new JPanel();
		 mainPanel.add(centerPanel, BorderLayout.CENTER);
		 
		// JButtons with ActionListeners for functionality.
		// Combo boxes to load in YIELD etc (headers from CSV file).
		 northPanel.add(fileButton);
		 fileButton.addActionListener(this);
		northPanel.add(title);
		northPanel.add(quitButton, BorderLayout.NORTH);
		quitButton.addActionListener(this);
		southPanel.add(x);
		southPanel.add(y);
		southPanel.add(mouseListener);
		scatter = new ScatterPlotComponent();
		 scatter.setBorder(BorderFactory.createLineBorder(Color.black));
		 scatter.setBackground(Color.WHITE);
		 mainPanel.add(scatter);
		
		// Set size and visibility of JFrame, and exit program on close.
		 // Add mainPanel to Frame, and set title of window.
		this.add(mainPanel);
		this.setSize(500, 600);
		this.setTitle("Scatterplot");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// Create a JFileChooser and FileReader/BufferedReader to read in CSV files.
		if (e.getSource() == fileButton) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				String filename = jfc.getSelectedFile().getName();
				// Set 'title of file' in TextField to CSV selectedFile name.
				title.setText(filename);

				// Catching exceptions.
				try {
					// treat first line of file as special case, because it contains our headers
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					String line = br.readLine();
					// Split file using ','
					String[] headers = line.split(",");
					// For loop that iterates through the headers adding item to JComboBox in each position of array.
					for (int i = 0; i < headers.length; i++) {
						x.addItem(headers[i]);
						y.addItem(headers[i]);
					}
					// While loop that states while line is not empty, take the fields
					// And apply the Bond.createBond method, which parses them from Strings to int/doubles, returning a new Bond object
					while ((line = br.readLine()) != null && !line.isEmpty()) {
						String[] fields = line.split(",");
						Bond bond = Bond.createBond(fields);
						// Print bond to console to check method is working
						System.out.println(bond);
						//mouseListener.setText(bond.toString());
						// Add bond to Scatterplot Component
						scatter.addBond(bond);
						// Repaint method
						scatter.repaint();
						         
			    }

					
					br.close();

				} catch (IOException ee) {
					ee.printStackTrace();
					System.out.println(selectedFile + " (Cannot be found)");
				}
			}
			// Adding ActionEvent to quit button.
		} else {
			this.dispose();
		}
	}
		public static void displayBond(String bond) {
			mouseListener.setText(bond);
			System.out.println(bond);
	

		

	
		}
}


	
	

	


