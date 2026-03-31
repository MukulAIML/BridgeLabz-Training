import java.util.Arrays;

public class SearchComparison {
    
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};
        
        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = i;
            }
            
            int target = size - 1;
            
            // Linear Search
            long start = System.nanoTime();
            linearSearch(data, target);
            long linearTime = (System.nanoTime() - start) / 1_000_000;
            
            // Binary Search (requires sorted data)
            Arrays.sort(data);
            start = System.nanoTime();
            binarySearch(data, target);
            long binaryTime = (System.nanoTime() - start) / 1_000_000;
            
            System.out.println("Size: " + size);
            System.out.println("Linear Search: " + linearTime + "ms");
            System.out.println("Binary Search: " + binaryTime + "ms");
            System.out.println();
        }
    }
}

