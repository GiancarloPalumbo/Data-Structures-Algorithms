import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithmComparison
{

    public static void main(String[] args) {
        // Array sizes to test
        int[] arraySizes = {100, 200, 500, 750};

        for (int size : arraySizes) {
            System.out.println("Array Size: " + size);
            long avgBubbleSortTime = 0;
            long avgInsertionSortTime = 0;
            long avgSelectionSortTime = 0;
            long avgMergeSortTime = 0;

            for (int i = 0; i < 10; i++) {
                int[] arrayToSort = generateRandomArray(size);

                avgBubbleSortTime += measureSortingTime(arrayToSort.clone(), "BubbleSort");
                avgInsertionSortTime += measureSortingTime(arrayToSort.clone(), "InsertionSort");
                avgSelectionSortTime += measureSortingTime(arrayToSort.clone(), "SelectionSort");
                avgMergeSortTime += measureSortingTime(arrayToSort.clone(), "MergeSort");
            }

            // Calculate average times
            avgBubbleSortTime /= 10;
            avgInsertionSortTime /= 10;
            avgSelectionSortTime /= 10;
            avgMergeSortTime /= 10;

            System.out.println("Average Time for Bubble Sort: " + avgBubbleSortTime + " ms");
            System.out.println("Average Time for Insertion Sort: " + avgInsertionSortTime + " ms");
            System.out.println("Average Time for Selection Sort: " + avgSelectionSortTime + " ms");
            System.out.println("Average Time for Merge Sort: " + avgMergeSortTime + " ms");

            System.out.println();
        }
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10); // Generating random integers
        }

        return array;
    }

    private static long measureSortingTime(int[] array, String sortingAlgorithm) {
        long startTime = System.currentTimeMillis();

        switch (sortingAlgorithm) {
            case "BubbleSort":
                bubbleSort(array);
                break;
            case "InsertionSort":
                insertionSort(array);
                break;
            case "SelectionSort":
                selectionSort(array);
                break;
            case "MergeSort":
                mergeSort(array);
                break;
            // Add cases for other sorting algorithms if needed
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    // swap arr[i-1] and arr[i]
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    private static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    private static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
