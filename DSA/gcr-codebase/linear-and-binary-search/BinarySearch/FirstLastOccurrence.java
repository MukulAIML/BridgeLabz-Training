public class FirstLastOccurrence {
    
    public static int findFirstOccurrence(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int firstIndex = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                firstIndex = mid;
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return firstIndex;
    }
    
    public static int findLastOccurrence(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int lastIndex = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                lastIndex = mid;
                left = mid + 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return lastIndex;
    }
    
    public static void main(String[] args) {
        int[] array = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        
        int firstIndex = findFirstOccurrence(array, target);
        int lastIndex = findLastOccurrence(array, target);
        
        System.out.println("Array: " + java.util.Arrays.toString(array));
        System.out.println("Target: " + target);
        System.out.println("First occurrence at index: " + firstIndex);
        System.out.println("Last occurrence at index: " + lastIndex);
        
        int target2 = 6;
        int firstIndex2 = findFirstOccurrence(array, target2);
        int lastIndex2 = findLastOccurrence(array, target2);
        System.out.println("Target: " + target2);
        System.out.println("First occurrence at index: " + firstIndex2);
        System.out.println("Last occurrence at index: " + lastIndex2);
    }
}

