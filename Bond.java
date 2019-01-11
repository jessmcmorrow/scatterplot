import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;

public class Bond {
		private double yield;
		private int maturation;
		private int chf;
		
		public Bond(double x, int y, int z) {
			
	 		this.yield = x;
	 		this.maturation = y;
	 		this.chf = z;

	 	}
	 
	 	public double getYield() {
	 		return yield;
	 	}
	 	
	 	public int getMaturation() {
	 		return maturation;
	 	}
	 	
	 	public int getCHF() {
	 		return chf;
	 	}
	 	public static Bond createBond(String[] fields) {
	 		double yield = Double.parseDouble(fields[0]);
	 		int maturation = Integer.parseInt(fields[1]);
	 		int chf = Integer.parseInt(fields[2]);
	 		return new Bond(yield, maturation, chf);
	 		
	 	}
	 	


		@Override
		public String toString() {
			return "Bond [yield=" + yield + ", maturation=" + maturation + ", chf=" + chf + "]";
		}
		
	 	
	}

