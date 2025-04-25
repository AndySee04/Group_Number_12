package Test2;

public class NextFitAlgorithm {
	// Returns number of bins required
    // using next fit online algorithm
	
    static int nextFit(double weight[], int n, int c)
    {

        // Initialize result (Count of bins) and remaining
        // capacity in current bin.
        int res = 0; 
        double bin_rem = c;

        // Place items one by one
        for (int i = 0; i < n; i++) {
            // If this item can't fit in current bin
            if (weight[i] > bin_rem) {
                res++; // Use a new bin
                bin_rem = c - weight[i];
            }
            else
                bin_rem -= weight[i];
        }
        return res;
    }
}

// https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/
