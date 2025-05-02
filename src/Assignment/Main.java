package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            String filename = getFileName(args);
            validateFile(filename);
            
            double binCapacity = getBinCapacity(args);
            
            System.out.println("");
            System.out.println("-------------------------------");
            System.out.println(" Bin Packing Algorithm Program ");
            System.out.println("------------------------------");
            System.out.println("File         : " + filename);
            List<Order> orders = loadOrders(filename);
            
            System.out.println("Bin capacity : " + binCapacity);
            System.out.println("Loaded " + orders.size() + " orders.\n");

            BinPackingAlgorithm[] algorithms = {
                new NextFitAlgorithm(),
                new BestFitAlgorithm()
            };
            
            System.out.println("-------------------");
            System.out.println(" 1. Original Order ");
            System.out.println("-------------------");
            packWithOrderings(algorithms, orders, binCapacity);

            System.out.println("---------------------");
            System.out.println(" 2. Decreasing Order ");
            System.out.println("---------------------");
            packWithOrderings(algorithms, sortedOrders(orders, Comparator.comparing(Order::getVolume).reversed()), binCapacity);

            System.out.println("----------------------");
            System.out.println(" 3. increasing Order ");
            System.out.println("----------------------");
            packWithOrderings(algorithms, sortedOrders(orders, Comparator.comparing(Order::getVolume)), binCapacity);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getFileName(String[] args) {
        if (args.length > 0) return args[0];

        String defaultFileName = "src/Test3/orders.csv";
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter orders CSV file path (press Enter for default '" + defaultFileName + "'): ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultFileName : input;
    }

    private static double getBinCapacity(String[] args) {
        if (args.length > 1) {
            try {
                double cap = Double.parseDouble(args[1]);
                if (cap > 0) return cap;
            } catch (NumberFormatException ignored) {}
            System.err.println("Invalid or non-positive bin capacity. Using default.");
        }

        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter bin capacity (press Enter for default " + Bin.DEFAULT_CAPACITY + "): ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    double userCap = Double.parseDouble(input);
                    if (userCap > 0) return userCap;
                } catch (NumberFormatException ignored) {}
                System.err.println("Invalid input. Using default.");
            }
        }

        return Bin.DEFAULT_CAPACITY;
    }
    

    private static void validateFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) System.err.println("Warning: File '" + filename + "' does not exist.");
        else if (!file.isFile()) System.err.println("Warning: '" + filename + "' is not a regular file.");
        else if (!file.canRead()) System.err.println("Warning: File '" + filename + "' cannot be read.");
    }

    private static List<Order> loadOrders(String filename) {
        try {
            return CSVReader.loadOrders(filename);
        } catch (FileNotFoundException e) {
        	// If cannot find orders file, create sample order data instead
            System.err.println("'" + filename + "' file not found.");
            System.err.println("Creating sample orders instead.");
            return Arrays.asList(
                    new Order("Sample1", 0.5),
                    new Order("Sample2", 0.7),
                    new Order("Sample3", 0.3),
                    new Order("Sample4", 0.2),
                    new Order("Sample5", 0.4)
            );
        }
    }

    private static void packWithOrderings(BinPackingAlgorithm[] algorithms,
                                          List<Order> orders, double binCapacity) {
        for (BinPackingAlgorithm algorithm : algorithms) {
            try {
                BinPackingResult result = algorithm.pack(orders, binCapacity);
                System.out.println(result);
                validateResult(result, orders, binCapacity);
            } catch (Exception e) {
                System.err.println("Error running " + algorithm.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    private static List<Order> sortedOrders(List<Order> orders, Comparator<Order> comparator) {
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(comparator);
        return sorted;
    }

    private static void validateResult(BinPackingResult result, List<Order> orders, double binCapacity) {
        int placedOrders = result.getBins().stream().mapToInt(bin -> bin.getOrders().size()).sum();

        if (placedOrders != orders.size()) {
            System.err.println("Validation failed: Not all orders were placed. " +
                    placedOrders + " out of " + orders.size() + " orders were placed.");
            return;
        }

        boolean valid = true;
        for (int i = 0; i < result.getBins().size(); i++) {
            Bin bin = result.getBins().get(i);
            double totalVolume = bin.getOrders().stream().mapToDouble(Order::getVolume).sum();

            if (totalVolume > binCapacity) {
                System.err.println("Validation failed: Bin " + (i + 1) + " is overfilled. " +
                        "Total volume: " + totalVolume + ", Capacity: " + binCapacity);
                valid = false;
            }
        }

        if (valid) {
            System.out.println("Validation passed. All orders placed correctly.\n");
        }
    }
}
