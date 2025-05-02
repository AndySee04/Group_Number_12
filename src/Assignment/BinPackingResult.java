package Assignment;

import java.util.List;
import java.util.ArrayList;

public class BinPackingResult {
	private int binCount;
	private List<Bin> bins;
	private String algorithmName;

	public BinPackingResult(String algorithmName) {
		this.algorithmName = algorithmName;
		this.bins = new ArrayList<>();
		this.binCount = 0;
	}

	public void addBin(Bin bin) {
		bins.add(bin);
		binCount++;
	}

	public int getBinCount() {
		return binCount;
	}

	public List<Bin> getBins() {
		return bins;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------------------------").append("\n");
		sb.append("Algorithm used\t\t: ").append(algorithmName).append("\n");
		sb.append("Number of bins required\t: ").append(binCount).append("\n");
		sb.append("--------------------------------------").append("\n");
		sb.append("Bin contents:\n");

		for (int i = 0; i < bins.size(); i++) {
			sb.append("Bin ").append(i + 1).append(" ");
			sb.append(bins.get(i).toString()).append("\n");
		}

		return sb.toString();
	}
}