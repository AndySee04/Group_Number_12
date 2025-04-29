package Test3;

import java.util.List;

public interface IBinPackingAlgorithm {
	BinPackingResult pack(List<Order> orders, double binCapacity) throws IllegalArgumentException;
}