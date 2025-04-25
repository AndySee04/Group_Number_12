package Test2;

public class BestFitAlgorithm {
	static int bestFit(double weight[], int n, int c)
	{
	    
	    // Initialize result (Count of bins)
	    int res = 0;

	    // Create an array to store 
	    // remaining space in bins
	    // there can be at most n bins
	    double []bin_rem = new double[n];

	    // Place items one by one
	    for (int i = 0; i < n; i++) 
	    {
	        
	        // Find the best bin that 
	        // can accommodate
	        // weight[i]
	        int j;

	        // Initialize minimum space 
	        // left and index
	        // of best bin
	        double min = c + 1;
	        int bi = 0;

	        for (j = 0; j < res; j++) 
	        {
	            if (bin_rem[j] >= weight[i] && 
	                bin_rem[j] - weight[i] < min)
	            {
	                bi = j;
	                min = bin_rem[j] - weight[i];
	            }
	        }

	        // If no bin could accommodate weight[i],
	        // create a new bin
	        if (min == c + 1)
	        {
	            bin_rem[res] = c - weight[i];
	            res++;
	        }
	        else // Assign the item to best bin
	            bin_rem[bi] -= weight[i];
	    }
	    return res;
	}
}

//https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/

