package Test3;

public class Order {
	// Instances
	private String orderId;
	private double volume;
	
	// Constructor
	public Order(String orderId, double volume) {
		this.orderId = orderId;
		this.volume = volume;
	}
	
	// Getters
	public String getOrderId() {
		return orderId;
	}
	
	public double getVolume() {
		return volume;
	}
	
	// Setters
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}	
}
