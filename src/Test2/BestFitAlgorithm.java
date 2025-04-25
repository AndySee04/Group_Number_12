package Test2;

import java.util.List;

public class BestFitAlgorithm {
	// Returns number of bins 
	// required using best fit
	// online algorithm
	static int bestFit(List<Order> orders, int capacity) {
		// Initialize result (Count of bins)
		int res = 0;

		// Create an array to store
		// remaining space in bins
		// there can be at most n bins
		double[] bin_rem = new double[orders.size()];

		// Loop through orders array
		for (Order order : orders) {
			double volume = order.getVolume();
			double min = capacity + 1;
			int bi = -1;

			// Place items one by one
			for (int j = 0; j < res; j++) {
				if (bin_rem[j] >= volume && bin_rem[j] - volume < min) {
					bi = j;
					min = bin_rem[j] - volume;
				}
			}

			// If no bin could accommodate order's volume,
			// create a new bin
			if (min == capacity + 1) {
				bin_rem[res] = capacity - volume;
				res++;
			} else // Assign the item to best bin
				bin_rem[bi] -= volume;
		}

		return res;
	}
}

//References: https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
