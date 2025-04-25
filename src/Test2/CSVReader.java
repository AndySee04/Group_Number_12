package Test2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static List<Order> loadOrders(String filename) {
        List<Order> orderList = new ArrayList<>();

        try {
            Scanner in = new Scanner(new File(filename));
            boolean isHeader = true;

            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (isHeader) { // skip header
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String orderId = parts[0].trim();
                    double volume = Double.parseDouble(parts[1].trim());

                    orderList.add(new Order(orderId, volume));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error reading CSV: " + ex.getMessage());
        }

        return orderList;
    }
}

