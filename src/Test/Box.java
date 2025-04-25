package Test;

//Box class representing Shopee’s predefined shipping boxes
public class Box {
 int capacity;
 int usedVolume = 0;
 int usedWeight = 0;

 public Box(Order firstOrder) {
     if (firstOrder.volume <= BinPackingWithWeight.SMALL_BOX_CAPACITY) {
         this.capacity = BinPackingWithWeight.SMALL_BOX_CAPACITY;
     } else if (firstOrder.volume <= BinPackingWithWeight.MEDIUM_BOX_CAPACITY) {
         this.capacity = BinPackingWithWeight.MEDIUM_BOX_CAPACITY;
     } else {
         this.capacity = BinPackingWithWeight.LARGE_BOX_CAPACITY;
     }
     addOrder(firstOrder);
 }

 public boolean canFit(Order order) {
     return (usedVolume + order.volume <= capacity) && (usedWeight + order.weight <= BinPackingWithWeight.MAX_WEIGHT);
 }

 public void addOrder(Order order) {
     usedVolume += order.volume;
     usedWeight += order.weight;
 }

 public int remainingVolume() {
     return capacity - usedVolume;
 }
}

