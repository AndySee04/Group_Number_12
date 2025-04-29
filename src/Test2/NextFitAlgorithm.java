package Test2;

import java.util.List;

public class NextFitAlgorithm {
	// Returns number of bins required
	// using next fit 
	// online algorithm
	static int findnumberOfBin(List<Order> orders, int capacity) {
		// Initialize result (Count of bins) and remaining
		// capacity in current bin.
		int res = 0;
		double bin_rem = capacity;

		// Place items one by one
		for (Order order : orders) {
			double volume = order.getVolume();
			// If this item can't fit in current bin
			if (volume > bin_rem) {
				res++; // Use a new bin
				bin_rem = capacity - volume;
			} else
				bin_rem -= volume;
		}
		return res;
	}
}

// References: https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
