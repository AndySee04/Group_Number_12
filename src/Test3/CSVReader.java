package Test3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

	/**
	 * Loads orders from a CSV file
	 * 
	 * @param filename Path to the CSV file
	 * @return List of Order objects
	 * @throws FileNotFoundException    if the file cannot be found
	 * @throws IllegalArgumentException if the file format is invalid
	 */
	public static List<Order> loadOrders(String filename) throws FileNotFoundException, IllegalArgumentException {
		List<Order> orderList = new ArrayList<>();
		File file = new File(filename);

		if (!file.exists()) {
			throw new FileNotFoundException("File not found: " + filename);
		}

		try (Scanner in = new Scanner(file)) {
			boolean isHeader = true;
			int lineNumber = 0;

			while (in.hasNextLine()) {
				lineNumber++;
				String line = in.nextLine();

				if (isHeader) { // skip header
					isHeader = false;
					validateHeader(line, lineNumber);
					continue;
				}

				try {
					String[] parts = line.split(",");
					if (parts.length != 2) {
						throw new IllegalArgumentException("Line " + lineNumber
								+ " has invalid format. Expected 2 values but found " + parts.length);
					}

					String orderId = parts[0].trim();

					if (orderId.isEmpty()) {
						throw new IllegalArgumentException("Line " + lineNumber + " has empty order ID");
					}

					double volume;
					try {
						volume = Double.parseDouble(parts[1].trim());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException(
								"Line " + lineNumber + " has invalid volume format: " + parts[1].trim());
					}

					if (volume <= 0) {
						throw new IllegalArgumentException("Line " + lineNumber + " has invalid volume value: " + volume
								+ ". Must be greater than 0.");
					}

					orderList.add(new Order(orderId, volume));

				} catch (Exception e) {
					throw new IllegalArgumentException("Error processing line " + lineNumber + ": " + e.getMessage(),
							e);
				}
			}

			if (orderList.isEmpty()) {
				throw new IllegalArgumentException("No orders found in file: " + filename);
			}

			return orderList;
		}
	}

	private static void validateHeader(String header, int lineNumber) throws IllegalArgumentException {
		String[] parts = header.split(",");
		if (parts.length != 2 || !parts[0].trim().equalsIgnoreCase("orderId")
				|| !parts[1].trim().equalsIgnoreCase("volume")) {
			throw new IllegalArgumentException("Line " + lineNumber
					+ " has invalid header format. Expected 'orderId,volume' but found '" + header + "'");
		}
	}
}