package tree;

import list.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import interfaces.Position;
import interfaces.Entry;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTimer {

    public static void multiInput(String pattern,int size, Integer[] data, List<utils.SortBenchmarkResult> results) throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        TreeMap<Integer, String> javaTree = new TreeMap<>();
        Treap<Integer, String> treapTree = new Treap<>();

        long startInsert = System.nanoTime(); //start AVL
        for(Integer i : data) {
            map.put(i, Integer.toString(i));
        }
        long endInsert = System.nanoTime(); //end AVL
        recordResult(results, pattern, size, "AVLTreeMap (Multi)", startInsert, endInsert);

        long startInsert2 = System.nanoTime(); //start javaTree
        for(Integer i : data) {
            javaTree.put(i, Integer.toString(i));
        }
        long endInsert2 = System.nanoTime(); //end javaTree
        recordResult(results, pattern, size, "TreeMap (Multi)", startInsert2, endInsert2);

        long startInsert3 = System.nanoTime(); //start javaTree
        for(Integer i : data) {
            treapTree.put(i, Integer.toString(i));
        }
        long endInsert3 = System.nanoTime(); //end javaTree
        recordResult(results, pattern, size, "Treap (Multi)", startInsert3, endInsert3);

        System.out.printf("%-20s Multi Input AVLTreeMap ---------- : %,10d ns\n", pattern, (endInsert - startInsert));
        System.out.printf("%-20s Multi Input Inbuilt Tree Map ---- : %,10d ns\n", pattern, (endInsert2 - startInsert2));
        System.out.printf("%-20s Multi Input Treap --------------- : %,10d ns\n", pattern, (endInsert3 - startInsert3));
    }

    public static void singleInput(String pattern, int size, Integer[] data, List<utils.SortBenchmarkResult> results) throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        TreeMap<Integer, String> javaTree = new TreeMap<>();
        Treap<Integer, String> treapTree = new Treap<>();

        long startInsert = System.nanoTime();
        map.put(7000, Integer.toString(7000));
        long endInsert = System.nanoTime();
        recordResult(results, pattern, size, "AVLTreeMap (Single)", startInsert, endInsert);

        long startInsert2 = System.nanoTime();
        javaTree.put(7000, Integer.toString(7000));
        long endInsert2 = System.nanoTime();
        recordResult(results, pattern, size, "TreeMap (Single)", startInsert2, endInsert2);


        long startInsert3 = System.nanoTime();
        treapTree.put(7000, Integer.toString(7000));
        long endInsert3 = System.nanoTime();
        recordResult(results, pattern, size, "Treap (Single)", startInsert3, endInsert3);

        System.out.printf("%-20s Single Inputs AVLTreeMap -------- : %,10d ns\n", pattern, (endInsert - startInsert));
        System.out.printf("%-20s Single Inputs Inbuilt Tree Map -- : %,10d ns\n", pattern, (endInsert2 - startInsert2));
        System.out.printf("%-20s Single Inputs Treap ------------- : %,10d ns\n", pattern, (endInsert3 - startInsert3));
    }

    public static void deletion(String pattern, int size, Integer[] data, List<utils.SortBenchmarkResult> results) throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        TreeMap<Integer, String> javaTree = new TreeMap<>();
        Treap<Integer, String> treapTree = new Treap<>();

        for(Integer i : data) {
            map.put(i, Integer.toString(i));
            javaTree.put(i, Integer.toString(i));
            treapTree.put(i, Integer.toString(i));
        }

        long startRemove = System.nanoTime();
        for (Integer i : data) {
            map.remove(i);
        }
        long endRemove = System.nanoTime();
        recordResult(results, pattern, size, "AVLTreeMap (Deletion)", startRemove, endRemove);

        long startRemove2 = System.nanoTime();
        for (Integer i : data) {
            javaTree.remove(i);
        }
        long endRemove2 = System.nanoTime();
        recordResult(results, pattern, size, "Tree Map (Deletion)", startRemove, endRemove);

        long startRemove3 = System.nanoTime();
        for (Integer i : data) {
            treapTree.remove(i);
        }
        long endRemove3 = System.nanoTime();
        recordResult(results, pattern, size, "Treap (Deletion)", startRemove, endRemove);

        System.out.printf("%-20s Delete Nodes AVLTreeMap --------- : %,10d ns\n", pattern, (endRemove - startRemove));
        System.out.printf("%-20s Delete Nodes In Built Tree Map -- : %,10d ns\n", pattern, (endRemove2 - startRemove2));
        System.out.printf("%-20s Delete Nodes Treap -------------- : %,10d ns\n", pattern, (endRemove3 - startRemove3));
    }

    public static void searching(String pattern, int size, Integer[] data, List<utils.SortBenchmarkResult> results) throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        TreeMap<Integer, String> javaTree = new TreeMap<>();
        Treap<Integer, String> treapTree = new Treap<>();

        for(Integer i : data) {
            map.put(i, Integer.toString(i));
            javaTree.put(i, Integer.toString(i));
            treapTree.put(i, Integer.toString(i));
        }

        long startSuccessfulSearch = System.nanoTime();
        String AVLT = map.get(9000);  //exists
        long endSuccessfulSearch = System.nanoTime();
        recordResult(results, pattern, size, "AVLTreemap (Successful Search)", startSuccessfulSearch, endSuccessfulSearch);

        long startUnsuccessfulSearch = System.nanoTime();
        String AVLF = map.get(11000);  //fake
        long endUnsuccessfulSearch = System.nanoTime();
        recordResult(results, pattern, size, "AVLTreemap (Unsuccessful Search)", startUnsuccessfulSearch, endUnsuccessfulSearch);


        long startSuccessfulSearch2 = System.nanoTime();
        String InBuiltT = map.get(9000);  //exists
        long endSuccessfulSearch2 = System.nanoTime();
        recordResult(results, pattern, size, "Tree Map (Successful Search)", startSuccessfulSearch2, endSuccessfulSearch2);

        long startUnsuccessfulSearch2 = System.nanoTime();
        String InBuiltF = map.get(-1);  //fake
        long endUnsuccessfulSearch2 = System.nanoTime();
        recordResult(results, pattern, size, "Tree Map (Unsuccessful Search)", startUnsuccessfulSearch2, endUnsuccessfulSearch2);


        long startSuccessfulSearch3 = System.nanoTime();
        String TreapT = map.get(9000);  //exists
        long endSuccessfulSearch3 = System.nanoTime();
        recordResult(results, pattern, size, "Treap (Successful Search)", startSuccessfulSearch3, endSuccessfulSearch3);

        long startUnsuccessfulSearch3 = System.nanoTime();
        String TreapF = map.get(0);  //fake
        long endUnsuccessfulSearch3 = System.nanoTime();
        recordResult(results, pattern, size, "Tree Map (Unsuccessful Search)", startUnsuccessfulSearch3, endUnsuccessfulSearch3);

        System.out.printf("%-20s Successful Search Time AVLTreeMap ----------- : %,10d ns\n", pattern, (endSuccessfulSearch - startSuccessfulSearch));
        System.out.printf("%-20s Unsuccessful Search Time AVLTreeMap --------- : %,10d ns\n", pattern, (endUnsuccessfulSearch - startUnsuccessfulSearch));
        System.out.printf("%-20s Successful Search Time Inbuilt Tree Map ----- : %,10d ns\n", pattern, (endSuccessfulSearch2 - startSuccessfulSearch2));
        System.out.printf("%-20s Unsuccessful Search Time Inbuilt Tree Map --- : %,10d ns\n", pattern, (endUnsuccessfulSearch2 - startUnsuccessfulSearch2));
        System.out.printf("%-20s Successful Search Time Treap ---------------- : %,10d ns\n", pattern, (endSuccessfulSearch3 - startSuccessfulSearch3));
        System.out.printf("%-20s Unsuccessful Search Time Treap -------------- : %,10d ns\n", pattern, (endUnsuccessfulSearch3 - startUnsuccessfulSearch3));

    }


/*
    public static void inOrderTraversal() throws IOException {

    }

 */



    private static void inOrder(String pattern, int size, Integer[] data, List<utils.SortBenchmarkResult> results) throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        TreeMap<Integer, String> javaTree = new TreeMap<>();
        Treap<Integer, String> treapTree = new Treap<>();

        long startInsert = System.nanoTime();
        for (Integer val : data) {
            map.put(val, val.toString());
        }
        long endInsert = System.nanoTime();
        recordResult(results, pattern, size, "AVLTreeMap Insert", startInsert, endInsert);

        long startInsert2 = System.nanoTime();
        for (Integer val : data) {
            javaTree.put(val, val.toString());
        }
        long endInsert2 = System.nanoTime();
        recordResult(results, pattern, size, "TreeMap Insert", startInsert2, endInsert2);

        long startInsert3 = System.nanoTime();
        for (Integer val : data) {
            treapTree.put(val, val.toString());
        }
        long endInsert3 = System.nanoTime();
        recordResult(results, pattern, size, "Treap Insert", startInsert3, endInsert3);


        //        Time in-order traversal
        long startTraverse = System.nanoTime();
        for (Position<Entry<Integer, String>> pos : map.tree.inorder()) {
            if (!map.tree.isExternal(pos)) {
                Entry<Integer, String> entry = pos.getElement();
                // Access to simulate use
                Integer key = entry.getKey();
                String value = entry.getValue();
            }
        }
        long endTraverse = System.nanoTime();
        recordResult(results, pattern, size, "AVLTreeMap Traverse", startTraverse, endTraverse);

        long startTraverse2 = System.nanoTime();
        for (Position<Entry<Integer, String>> pos : javaTree.tree.inorder()) {
            if (!javaTree.tree.isExternal(pos)) {
                Entry<Integer, String> entry = pos.getElement();
                // Access to simulate use
                Integer key = entry.getKey();
                String value = entry.getValue();
            }
        }
        long endTraverse2 = System.nanoTime();
        recordResult(results, pattern, size, "TreeMap Traverse", startTraverse2, endTraverse2);

        long startTraverse3 = System.nanoTime();
        for (Position<Entry<Integer, String>> pos : treapTree.tree.inorder()) {
            if (!treapTree.tree.isExternal(pos)) {
                Entry<Integer, String> entry = pos.getElement();
                // Access to simulate use
                Integer key = entry.getKey();
                String value = entry.getValue();
            }
        }
        long endTraverse3 = System.nanoTime();
        recordResult(results, pattern, size, "Treap Traverse", startTraverse3, endTraverse3);

        System.out.printf("%s AVLTreeMap Insert: %,10d ns | Traverse: %,10d ns\n", pattern, (endInsert - startInsert), (endTraverse - startTraverse));
        System.out.println();
        System.out.printf("%s Tree Map Insert: %,10d ns | Traverse: %,10d ns\n", pattern, (endInsert2 - startInsert2), (endTraverse2 - startTraverse2));
        System.out.println();
        System.out.printf("%s Treap Insert: %,10d ns | Traverse: %,10d ns\n", pattern, (endInsert3 - startInsert3), (endTraverse3 - startTraverse3));
        System.out.println();
    }



/*
    public static void writeSortResultsCSV(List<utils.SortBenchmarkResult> results, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("Pattern,Size,Algorithm,Time (ns)");
            for (utils.SortBenchmarkResult result : results) {
                writer.println(result.toCSV());
            }
        }
    }

 */




    public static void recordResult(List<utils.SortBenchmarkResult> results, String pattern, int size, String algorithm, long start, long end) {
        utils.SortBenchmarkResult result = new utils.SortBenchmarkResult();
        result.pattern = pattern;
        result.size = size;
        result.algorithm = algorithm;
        result.time = end - start;
        results.add(result);
    }




    public static void main(String[] args) throws IOException {
        //singleInput();
        //multiInput();
        //deletion();
        //searching();
        //traversal();
        //inOrderTraversal();




        int[] sizes = {100, 5000, 10000};
        Random rand = new Random();
        List<utils.SortBenchmarkResult> results = new ArrayList<>();

        for (int size : sizes) {
            System.out.println("\nSize: " + size);

            // Generate different patterns
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

            inOrder("Random", size, randomData, results);
            inOrder("Sorted", size, sortedData, results);
            inOrder("Reverse Sorted", size, reverseSortedData, results);
            inOrder("Partially Sorted", size, partiallySortedData, results);
            System.out.println();
            singleInput("Random", size, randomData, results);
            singleInput("Sorted", size, sortedData, results);
            singleInput("Reverse Sorted", size, reverseSortedData, results);
            singleInput("Partially Sorted", size, partiallySortedData, results);
            System.out.println();
            multiInput("Random", size, randomData, results);
            multiInput("Sorted", size, sortedData, results);
            multiInput("Reverse Sorted", size, reverseSortedData, results);
            multiInput("Partially Sorted", size, partiallySortedData, results);
            System.out.println();
            deletion("Random", size, randomData, results);
            deletion("Sorted", size, sortedData, results);
            deletion("Reverse Sorted", size, reverseSortedData, results);
            deletion("Partially Sorted", size, partiallySortedData, results);
            System.out.println();
            searching("Random", size, randomData, results);
            searching("Sorted", size, sortedData, results);
            searching("Reverse Sorted", size, reverseSortedData, results);
            searching("Partially Sorted", size, partiallySortedData, results);
            System.out.println("\n");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("sort_results.csv"))) {
            writer.println("Pattern,Size,Algorithm,Time (ns)");
            for (utils.SortBenchmarkResult result : results) {
                writer.printf("%s,%d,%s,%d\n", result.pattern, result.size, result.algorithm, result.time);
            }
        }




/*
        Integer[] arr1 = new Integer[]{1,2,3,4,5,6,7,8,9}; //multiple input
        long startInsert = System.nanoTime();
        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }
        long endInsert1 = System.nanoTime();



        long startRemove = System.nanoTime();
        map.remove(26);
        long endRemove = System.nanoTime();

        //System.out.println(map.toBinaryTreeString());


        long durationRemove = endRemove - startRemove;


        System.out.println("Deletion time (nanoseconds): " + durationRemove);

 */
    }

}
