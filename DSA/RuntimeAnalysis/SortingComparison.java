import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    private static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
    
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            System.out.println("Size: " + size);
            
            // Bubble Sort
            if (size <= 10000) {
                int[] arr1 = generateArray(size);
                long start = System.nanoTime();
                bubbleSort(arr1);
                long time = (System.nanoTime() - start) / 1_000_000;
                System.out.println("Bubble Sort: " + time + "ms");
            } else {
                System.out.println("Bubble Sort: Unfeasible for large size");
            }
            
            // Merge Sort
            int[] arr2 = generateArray(size);
            long start = System.nanoTime();
            mergeSort(arr2, 0, arr2.length - 1);
            long time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Merge Sort: " + time + "ms");
            
            // Quick Sort
            int[] arr3 = generateArray(size);
            start = System.nanoTime();
            quickSort(arr3, 0, arr3.length - 1);
            time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Quick Sort: " + time + "ms");
            System.out.println();
        }
    }
}

