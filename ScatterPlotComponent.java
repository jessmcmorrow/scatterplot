import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.lang.IndexOutOfBoundsException;

public class ScatterPlotComponent extends JComponent implements MouseListener {

	private static final int BORDER_GAP = 30;
	private static final int Y_HATCH_CNT = 10;
	private static final int X_HATCH_CNT = 10;
	private static final int GRAPH_POINT_WIDTH = 12;
	
	// Two arrayLists. One to store bonds and one to store ellipses.
	private ArrayList<Bond> bondList = new ArrayList<Bond>();
	private ArrayList<Ellipse2D> bondEllipses = new ArrayList<Ellipse2D>();

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		g2.setColor(Color.BLACK);
		g2.setBackground(Color.WHITE);
		int width = this.getWidth();
		int height = this.getHeight();
		addMouseListener(this);
		
		double[] listOfYields = new double[bondList.size()];
		for (int i = 0; i < bondList.size() - 1; i++) {
			listOfYields[i] = bondList.get(i).getYield();
		}
		QuickSorter sorter = new QuickSorter();
		sorter.sortDouble(listOfYields);

		int[] listOfMaturations = new int[bondList.size()];
		for (int i = 0; i < bondList.size() - 1; i++) {
			listOfMaturations[i] = bondList.get(i).getMaturation();
		}
		sorter.sort(listOfMaturations);
		bondEllipses.clear();
		for (Bond bond : bondList) {
			double minYield = listOfYields[0]; 
			double maxYield = listOfYields[listOfYields.length - 1];
			int minMaturation = listOfMaturations[0];
			int maxMaturation = listOfMaturations[listOfMaturations.length - 1];
			double xAxisLength = width - (BORDER_GAP * 2);
			double yAxisLength = height - (BORDER_GAP * 2);
			double x = (bond.getYield() * (xAxisLength / maxYield)) + BORDER_GAP;
			double y = ((double) bond.getMaturation() * ((double) yAxisLength / (double) maxMaturation)) + BORDER_GAP;
			y = height - y;
			double r = 8;
			Ellipse2D.Double e = new Ellipse2D.Double(x, y, r, r);
			g2.setColor(Color.BLUE);
			g2.draw(e);
			g2.fill(e);
			bondEllipses.add(e);
			repaint();
			
			// draws x and y axis
			g2.drawLine(BORDER_GAP, this.getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
			g2.drawLine(BORDER_GAP, this.getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

			// draws hatch marks for x axis
			// for every gap of (maximum-minimum)/k, where k is the
			// number of tick marks.
			FontMetrics fm = g2.getFontMetrics();
			//double yieldInterval = (maxYield - minYield) / X_HATCH_CNT;
			//double labelValues = minYield;
			
			 for (int i = 0; i <= X_HATCH_CNT; i++) {
			        if (bondList.size() > 1) {
			            int x0 = i * (getWidth() - BORDER_GAP * 2) / X_HATCH_CNT + BORDER_GAP;
			            int x1 = x0;
			            int y0 = getHeight() - BORDER_GAP;
			            int y1 = y0 - 10;
			            if (bondList.size() > 0) {
			                String xLabel = ((int) ((minYield + (maxYield - minYield) * ((i * 1.00) / X_HATCH_CNT) * 100)) / 100 + "");
			                FontMetrics metrics = g2.getFontMetrics();
			                int labelWidth = metrics.stringWidth(xLabel);
			                g2.drawString(xLabel, x0 - 10, y0 + metrics.getHeight());
			            } 
			            g2.drawLine(x0, y0, x1, y1);
			           
					}

				}

			// draws hatch marks for y axis
			int maturationInterval = (maxMaturation - minMaturation) / Y_HATCH_CNT;
			int labelValue = minMaturation;
			for (int i = 0; i <= Y_HATCH_CNT; i++) {
				int x0 = BORDER_GAP;
				int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
				int y0 = getHeight() - (((i) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
				int y1 = y0;
				g2.drawLine(x0, y0, x1, y1);
				String value = Integer.toString(labelValue);
				g2.drawString(value, x0 - fm.stringWidth(value), y0 + (fm.getAscent() / 2));
				labelValue = labelValue + maturationInterval;
			}
			

		}
	}
	
	public void addBond(Bond bond) {
		bondList.add(bond);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try 
		{	
			for (int i = 0; i < bondEllipses.size(); i++) {
		
			if (bondEllipses.get(i).contains(e.getPoint())) {
				FrameTest.displayBond(bondList.get(i).toString());
			}
			}
			}
			catch (IndexOutOfBoundsException e1) {
				e1.printStackTrace();
			}
		
	}
	

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
