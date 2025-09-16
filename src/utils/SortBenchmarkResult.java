package utils;

public class SortBenchmarkResult {
    public String pattern;
    public int size;
    public String algorithm;
    public long time;

    public String toCSV() {
        return String.format("%s,%d,%s,%d", pattern, size, algorithm, time);
    }
}
