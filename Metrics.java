import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private int maxDepth = 0;
    private int currentDepth = 0;
    private long comparisons = 0;
    private long allocations = 0;

    public void reset() {
        maxDepth = 0;
        currentDepth = 0;
        comparisons = 0;
        allocations = 0;
    }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void leave() {
        currentDepth--;
    }

    public void countComparison(long count) {
        comparisons += count;
    }

    public void countAllocation(long count) {
        allocations += count;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public void writeCsv(String filename, String header, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            // Write header if file is empty
            java.io.File file = new java.io.File(filename);
            if (file.length() == 0) {
                fw.write(header + "\n");
            }
            fw.write(data + "\n");
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
