package Test2;

import java.util.LinkedList;
import java.util.List;

public class BinPackingProblem {

	public static void main(String[] args) {
		// Define box capacity
		final int BINCAPACITY = 1;
		
		// Load orders
		List<Order> orders = new LinkedList<Order>(CSVReader.loadOrders("src/Test2/orders.csv"));
		
		// Display loaded orders (test if loaded successfully)
		for(Order o: orders) {
			System.out.println(o.getOrderId() + " " + o.getVolume());
		}
		
		// Put order volumes into double array
		double[] volume = new double[orders.size()];
		for (int i = 0; i < orders.size(); i++) {
			volume[i] = orders.get(i).getVolume();
		}
		
		// Run Best Fit algorithm
		System.out.println("Number of bins required in Best Fit: "
				+ BestFitAlgorithm.bestFit(volume, volume.length, BINCAPACITY));
		
		// Run Next Fit algorithm
		System.out.println("Number of bins required in Next Fit: "
				+ NextFitAlgorithm.nextFit(volume, volume.length, BINCAPACITY));
	}

}
