package utils;

import java.util.Arrays;

public class QuickSort {
    public static Integer[] quickSort(Integer[] array) {
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    private static void quickSortHelper(Integer[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
    }

    private static int partition(Integer[] array, int low, int high) {
        Integer pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
