package Test3;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    // Default capacity for bins
    public static final double DEFAULT_CAPACITY = 1.0;
    
    private double capacity;
    private double remainingSpace;
    private List<Order> orders;
    
    // Default constructor
    public Bin() {
        this(DEFAULT_CAPACITY);
    }
    
    // User-specified constructor
    public Bin(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Bin capacity must be greater than zero");
        }
        this.capacity = capacity;
        this.remainingSpace = capacity;
        this.orders = new ArrayList<>();
    }
    
    public boolean addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        
        if (order.getVolume() <= remainingSpace) {
            orders.add(order);
            remainingSpace -= order.getVolume();
            return true;
        }
        return false;
    }
    
    public double getRemainingSpace() {
        return remainingSpace;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    public double getCapacity() {
        return capacity;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Used: ").append(String.format("%.2f", capacity - remainingSpace));
        sb.append(", Remaining: ").append(String.format("%.2f", remainingSpace)).append("] \n");
        
//        sb.append("Orders: ");
        for (Order order : orders) {
            sb.append("    ").append(order.getOrderId()).append(" (Volume : ").append(order.getVolume()).append(") \n");
        }
        
        return sb.toString();
    }
}