package Test;

import java.io.*;
import java.util.*;

public class BinPackingWithWeight {
	// Shopee box size limits (capacity in cm³ and max weight in grams)
	static final int SMALL_BOX_CAPACITY = 5000;
	static final int MEDIUM_BOX_CAPACITY = 10000;
	static final int LARGE_BOX_CAPACITY = 15000;
	static final int MAX_WEIGHT = 50000; // 50kg max weight limit

	public static void main(String[] args) {
		String fileName = "src/Test/shopee_bin_packing_120_orders2.csv";
		List<Order> orders = readOrdersFromCSV(fileName);

		System.out.println("Applying First Fit Decreasing (FFD)...");
		int ffdBins = firstFitDecreasing(new ArrayList<>(orders));
		System.out.println("Total bins used (FFD): " + ffdBins);

		System.out.println("\nApplying Best Fit (BF)...");
		int bfBins = bestFit(new ArrayList<>(orders));
		System.out.println("Total bins used (BF): " + bfBins);
	}

	// Read orders from CSV
	private static List<Order> readOrdersFromCSV(String fileName) {
		List<Order> orders = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			br.readLine(); // Skip header
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				String[] itemSizes = parts[1].split(" ");
				int volume = Arrays.stream(itemSizes).mapToInt(Integer::parseInt).sum();
				int weight = Integer.parseInt(parts[5].trim());
				orders.add(new Order(volume, weight));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orders;
	}

	// First Fit Decreasing (FFD) Algorithm
	private static int firstFitDecreasing(List<Order> orders) {
		orders.sort((o1, o2) -> Integer.compare(o2.volume, o1.volume)); // Sort by volume (desc)

		List<Box> bins = new ArrayList<>();
		for (Order order : orders) {
			boolean placed = false;
			for (Box bin : bins) {
				if (bin.canFit(order)) {
					bin.addOrder(order);
					placed = true;
					break;
				}
			}
			if (!placed) {
				bins.add(new Box(order));
			}
		}
		return bins.size();
	}

	// Best Fit (BF) Algorithm
	private static int bestFit(List<Order> orders) {
		List<Box> bins = new ArrayList<>();
		for (Order order : orders) {
			int bestBinIndex = -1;
			int minRemainingSpace = LARGE_BOX_CAPACITY + 1;

			for (int i = 0; i < bins.size(); i++) {
				int remainingSpace = bins.get(i).remainingVolume() - order.volume;
				if (bins.get(i).canFit(order) && remainingSpace < minRemainingSpace) {
					bestBinIndex = i;
					minRemainingSpace = remainingSpace;
				}
			}

			if (bestBinIndex == -1) {
				bins.add(new Box(order));
			} else {
				bins.get(bestBinIndex).addOrder(order);
			}
		}
		return bins.size();
	}
}

// Order class representing a single warehouse order
class Order {
	int volume;
	int weight;

	public Order(int volume, int weight) {
		this.volume = volume;
		this.weight = weight;
	}
}
