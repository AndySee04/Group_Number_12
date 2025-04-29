package Test3;

import java.util.List;

public class BestFitAlgorithm extends BinPackingAlgorithm {
    
    public BestFitAlgorithm() {
        super("Best Fit");
    }
    
    @Override
    protected void packInternal(List<Order> orders, double binCapacity, BinPackingResult result) {
        List<Bin> bins = result.getBins();
        
        // Process each order
        for (Order order : orders) {
            double volume = order.getVolume();
            double minRemainingSpace = Double.MAX_VALUE;
            Bin bestBin = null;
            
            // Try to find the best bin for this order
            for (Bin bin : bins) {
                double remainingSpace = bin.getRemainingSpace();
                if (remainingSpace >= volume && remainingSpace - volume < minRemainingSpace) {
                    bestBin = bin;
                    minRemainingSpace = remainingSpace - volume;
                }
            }
            
            // If no bin found, create a new one
            if (bestBin == null) {
                bestBin = new Bin(binCapacity);
                result.addBin(bestBin);
            }
            
            // Add the order to the bin
            bestBin.addOrder(order);
        }
    }
}