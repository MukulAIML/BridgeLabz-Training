import java.util.Arrays;

public class SearchProblems {
    
    public static int findFirstMissingPositive(int[] array) {
        int n = array.length;
        boolean[] present = new boolean[n + 1];
        
        for (int i = 0; i < n; i++) {
            if (array[i] > 0 && array[i] <= n) {
                present[array[i]] = true;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }
        
        return n + 1;
    }
    
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int[] array1 = {3, 4, -1, 1};
        int missing = findFirstMissingPositive(array1);
        System.out.println("Array: " + Arrays.toString(array1));
        System.out.println("First missing positive: " + missing);
        
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.sort(array2);
        int target = 5;
        int index = binarySearch(array2, target);
        System.out.println("\nSorted array: " + Arrays.toString(array2));
        System.out.println("Target: " + target);
        System.out.println("Found at index: " + index);
        
        int target2 = 11;
        int index2 = binarySearch(array2, target2);
        System.out.println("Target: " + target2);
        System.out.println("Found at index: " + index2);
    }
}

