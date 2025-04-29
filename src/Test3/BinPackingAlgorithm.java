package Test3;

import java.util.List;

public abstract class BinPackingAlgorithm implements IBinPackingAlgorithm {

	// Instances
	protected final String algorithmName;

	// Constructor
	public BinPackingAlgorithm(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	@Override
	public BinPackingResult pack(List<Order> orders, double binCapacity) throws IllegalArgumentException {
		// Validate input parameters
		validateInput(orders, binCapacity);

		// Create result object
		BinPackingResult result = new BinPackingResult(algorithmName);

		try {
			// Execute specific algorithm implementation
			packInternal(orders, binCapacity, result);
		} catch (Exception e) {
			throw new RuntimeException("Error executing " + algorithmName + " algorithm: " + e.getMessage(), e);
		}

		return result;
	}

	protected void validateInput(List<Order> orders, double binCapacity) throws IllegalArgumentException {
		if (orders == null) {
			throw new IllegalArgumentException("Orders list cannot be null");
		}

		if (binCapacity <= 0) {
			throw new IllegalArgumentException("Bin capacity must be greater than zero");
		}

		for (Order order : orders) {
			if (order.getVolume() > binCapacity) {
				throw new IllegalArgumentException("Order " + order.getOrderId() + " with volume " + order.getVolume()
						+ " exceeds bin capacity of " + binCapacity);
			}

			if (order.getVolume() <= 0) {
				throw new IllegalArgumentException(
						"Order " + order.getOrderId() + " has invalid volume: " + order.getVolume());
			}
		}
	}

	protected abstract void packInternal(List<Order> orders, double binCapacity, BinPackingResult result);
}