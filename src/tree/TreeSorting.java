package tree;

import list.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import interfaces.Position;
import interfaces.Entry;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;




public class TreeSorting {

    public static void runTreapSort(String pattern, Integer[] data, int size, List<utils.SortBenchmarkResult> results) throws IOException {
        long start = System.nanoTime();
        Integer[] sorted = Treap.treapSort(data);
        long end = System.nanoTime();
        System.out.printf("%-20s TreapSort: %,10d ns\n", pattern, (end - start));
        TreeTimer.recordResult(results, pattern, size, "TreapSort", start, end);
    }


    public class PQSort {
        public static Integer[] pqSort(Integer[] array) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (Integer num : array) {
                pq.add(num);
            }
            Integer[] sorted = new Integer[array.length];
            int i = 0;
            while (!pq.isEmpty()) {
                sorted[i++] = pq.poll();  //this removes and returns smallest element
            }

            return sorted;
        }
    }

    public static void runPQSort(String pattern, Integer[] data, int size, List<utils.SortBenchmarkResult> results) throws IOException {
        long start = System.nanoTime();
        Integer[] sorted = PQSort.pqSort(data);
        long end = System.nanoTime();
        System.out.printf("%-20s PQSort time for size %d: %,d ns\n",pattern, size, (end - start));
        TreeTimer.recordResult(results, pattern, size, "PQSort", start, end);
    }

    public class JavaCollectionsSort {
        public static Integer[] collectionSort(Integer[] array) {
            List<Integer> list = new ArrayList<>(Arrays.asList(array));
            Collections.sort(list);
            return list.toArray(new Integer[0]);
        }
    }

    public static void runCollectionSort(String pattern, Integer[] data, int size, List<utils.SortBenchmarkResult> results) throws IOException {
        long start = System.nanoTime();
        Integer[] sorted = JavaCollectionsSort.collectionSort(data);
        long end = System.nanoTime();
        System.out.printf("%-20s In built collections sort time for size %d: %,d ns\n",pattern, size, (end - start));
        TreeTimer.recordResult(results, pattern, size, "Collection Sort", start, end);
    }

    public static void runQuickSort(String pattern, Integer[] data, int size, List<utils.SortBenchmarkResult> results) throws IOException {
        long start = System.nanoTime();
        Integer[] sorted = utils.QuickSort.quickSort(data);
        long end = System.nanoTime();
        System.out.printf("%-20s Quick Sort time for size %d: %,d ns\n",pattern, size, (end - start));
        TreeTimer.recordResult(results, pattern, size, "Quick Sort", start, end);
    }

    public static void runMergeSort(String pattern, Integer[] data, int size, List<utils.SortBenchmarkResult> results) throws IOException {
        long start = System.nanoTime();
        Integer[] sorted = utils.MergeSort.mergeSort(data);
        long end = System.nanoTime();
        System.out.printf("%-20s Merge Sort time for size %d: %,d ns\n",pattern, size, (end - start));
        TreeTimer.recordResult(results, pattern, size, "Merge Sort", start, end);
    }

    /*
    private static void recordResult(List<utils.SortBenchmarkResult> results, String pattern, int size, String algorithm, long start, long end) {
        utils.SortBenchmarkResult result = new utils.SortBenchmarkResult();
        result.pattern = pattern;
        result.size = size;
        result.algorithm = algorithm;
        result.time = end - start;
        results.add(result);
    }

     */



    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 5000, 10000};
        Random rand = new Random();
        List<utils.SortBenchmarkResult> results = new ArrayList<>();

        for (int size : sizes) {
            System.out.println("\nSize: " + size);

            Integer[] randomData = new Integer[size];
            Integer[] sortedData = new Integer[size];
            Integer[] reverseSortedData = new Integer[size];
            Integer[] partiallySortedData = new Integer[size];

            for (int i = 0; i < size; i++) {
                randomData[i] = rand.nextInt(size * 10);  // avoid too many duplicates
                sortedData[i] = i;
                reverseSortedData[i] = size - i;
                partiallySortedData[i] = (i < size / 2) ? i : rand.nextInt(size * 10);
            }

            runTreapSort("Random", randomData, size, results);
            runTreapSort("Sorted", sortedData, size, results);
            runTreapSort("Reverse Sorted", reverseSortedData, size, results);
            runTreapSort("Partially Sorted", partiallySortedData, size, results);
            System.out.println();
            runPQSort("Random", randomData, size, results);
            runPQSort("Sorted", sortedData, size, results);
            runPQSort("Reverse Sorted", reverseSortedData, size, results);
            runPQSort("Partially Sorted", partiallySortedData, size, results);
            System.out.println();
            runCollectionSort("Random", randomData, size, results);
            runCollectionSort("Sorted", sortedData, size, results);
            runCollectionSort("Reverse Sorted", reverseSortedData, size, results);
            runCollectionSort("Partially Sorted", partiallySortedData, size, results);
            System.out.println();
            runQuickSort("Random", randomData, size, results);
            runQuickSort("Sorted", sortedData, size, results);
            runQuickSort("Reverse Sorted", reverseSortedData, size, results);
            runQuickSort("Partially Sorted", partiallySortedData, size, results);
            System.out.println();
            runMergeSort("Random", randomData, size, results);
            runMergeSort("Sorted", sortedData, size, results);
            runMergeSort("Reverse Sorted", reverseSortedData, size, results);
            runMergeSort("Partially Sorted", partiallySortedData, size, results);
            System.out.println("\n");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter("sorting_results.csv"))) {
            writer.println("Pattern,Size,Algorithm,Time (ns)");
            for (utils.SortBenchmarkResult result : results) {
                writer.printf("%s,%d,%s,%d\n", result.pattern, result.size, result.algorithm, result.time);
            }
        }

    }
}

















