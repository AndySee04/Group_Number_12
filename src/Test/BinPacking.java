package Test;

import java.io.*;
import java.util.*;

public class BinPacking {
    private static final int BOX_CAPACITY = 1000;

    public static void main(String[] args) {
        String fileName = "src/Test/shopee_bin_packing_120_orders.csv";
        List<List<Integer>> orders = readOrdersFromCSV(fileName);

        System.out.println("Applying First Fit Decreasing (FFD)...");
        int ffdBins = firstFitDecreasing(new ArrayList<>(orders));
        System.out.println("Total bins used (FFD): " + ffdBins);

        System.out.println("\nApplying Best Fit (BF)...");
        int bfBins = bestFit(new ArrayList<>(orders));
        System.out.println("Total bins used (BF): " + bfBins);
    }

    // Read the orders from CSV file
    private static List<List<Integer>> readOrdersFromCSV(String fileName) {
        List<List<Integer>> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String[] items = parts[1].split(" ");
                List<Integer> itemSizes = new ArrayList<>();
                for (String item : items) {
                    itemSizes.add(Integer.parseInt(item.trim()));
                }
                orders.add(itemSizes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // First Fit Decreasing (FFD) Algorithm
    private static int firstFitDecreasing(List<List<Integer>> orders) {
        List<Integer> allItems = new ArrayList<>();
        for (List<Integer> order : orders) {
            allItems.addAll(order);
        }
        allItems.sort(Collections.reverseOrder()); // Sort items in descending order

        List<Integer> bins = new ArrayList<>();
        for (int item : allItems) {
            boolean placed = false;
            for (int i = 0; i < bins.size(); i++) {
                if (bins.get(i) + item <= BOX_CAPACITY) {
                    bins.set(i, bins.get(i) + item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                bins.add(item);
            }
        }
        return bins.size();
    }

    // Best Fit (BF) Algorithm
    private static int bestFit(List<List<Integer>> orders) {
        List<Integer> allItems = new ArrayList<>();
        for (List<Integer> order : orders) {
            allItems.addAll(order);
        }

        List<Integer> bins = new ArrayList<>();
        for (int item : allItems) {
            int bestBinIndex = -1;
            int minRemainingSpace = BOX_CAPACITY + 1;

            for (int i = 0; i < bins.size(); i++) {
                int remainingSpace = BOX_CAPACITY - (bins.get(i) + item);
                if (remainingSpace >= 0 && remainingSpace < minRemainingSpace) {
                    bestBinIndex = i;
                    minRemainingSpace = remainingSpace;
                }
            }

            if (bestBinIndex == -1) {
                bins.add(item);
            } else {
                bins.set(bestBinIndex, bins.get(bestBinIndex) + item);
            }
        }
        return bins.size();
    }
    // testing github fetch function
}
