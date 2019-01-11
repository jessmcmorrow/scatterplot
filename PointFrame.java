import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PointFrame extends JFrame implements ActionListener{
	private JButton addButton;
	private PlotComponent plotComponent;
	private Random r = new Random();
	public PointFrame() {
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setup();
		
		this.setVisible(true);
	}
	
	private void setup() {
		JPanel panel = new JPanel(new BorderLayout());
		this.add(panel);
		plotComponent = new PlotComponent();
		panel.add(plotComponent,BorderLayout.CENTER);
		addButton = new JButton("Add a point");
		addButton.addActionListener(this);
		panel.add(addButton,BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		new PointFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			for(int i=0;i<100;i++) {
				double radius = r.nextDouble()*50 + 10;
				Point p = new Point(r.nextDouble(),r.nextDouble(),radius);
				plotComponent.addPoint(p);
				plotComponent.repaint();
			}
		}
		
	}

}
