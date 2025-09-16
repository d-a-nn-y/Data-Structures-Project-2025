package utils;
import java.util.Arrays;

import java.io.*;

public class MergeSort {
    public static Integer[] mergeSort(Integer[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        Integer[] left = Arrays.copyOfRange(array, 0, mid);
        Integer[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] result = new Integer[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }
}


