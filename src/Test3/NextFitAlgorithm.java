package Test3;

import java.util.List;

public class NextFitAlgorithm extends BinPackingAlgorithm {
    
    public NextFitAlgorithm() {
        super("Next Fit");
    }
    
    @Override
    protected void packInternal(List<Order> orders, double binCapacity, BinPackingResult result) {
        // Start with one bin
        Bin currentBin = new Bin(binCapacity);
        result.addBin(currentBin);
        
        // Place items one by one
        for (Order order : orders) {
            // If current bin can't fit this order, create a new bin
            if (!currentBin.addOrder(order)) {
                currentBin = new Bin(binCapacity);
                result.addBin(currentBin);
                
                // Add the order to the new bin
                if (!currentBin.addOrder(order)) {
                    // This should never happen as we validated order size earlier
                    throw new RuntimeException("Failed to add order to a new bin: " + order.getOrderId());
                }
            }
        }
    }
}

// References: https://www.geeksforgeeks.org/bin-packing-problem-minimize-number-of-used-bins/