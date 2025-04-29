package Test2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinPackingProblem {

	public static void main(String[] args) {
		// Define box capacity
		final int BINCAPACITY = 1;

		// Load orders
		List<Order> orders = new LinkedList<Order>(CSVReader.loadOrders("src/Test2/orders.csv"));

		// Display loaded orders (test if loaded successfully)
		for (Order o : orders) {
			System.out.println(o.getOrderId() + " " + o.getVolume());
		}

		// Default order
		// Run Best Fit algorithm
		BestFitAlgorithm best = new BestFitAlgorithm(orders, BINCAPACITY);
		System.out.println("Number of bins required in Best Fit: " + best.findNumberOfBin());
//		System.out.println("Number of bins required in Best Fit: " + BestFitAlgorithm.bestFit(orders, BINCAPACITY));
		// Run Next Fit algorithm
//		System.out.println("Number of bins required in Next Fit: " + NextFitAlgorithm.nextFit(orders, BINCAPACITY));

	}
}