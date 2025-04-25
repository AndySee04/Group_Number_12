package Test2;

import java.util.LinkedList;
import java.util.List;

public class BinPackingProblem {

	public static void main(String[] args) {
		// Read data from CSV file and store as list
		List<Order> orders = new LinkedList<Order>(CSVReader.loadOrders("src/Test2/orders.csv"));
		
		// Test if data is loaded successfully
		for(Order o: orders) {
			System.out.println(o.getOrderId() + " " + o.getVolume());
		}
		
	}

}
