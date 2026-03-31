public class RotationPoint {
    
    public static int findRotationPoint(int[] array) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) {
        int[] array1 = {4, 5, 6, 7, 0, 1, 2};
        int index1 = findRotationPoint(array1);
        System.out.println("Array: " + java.util.Arrays.toString(array1));
        System.out.println("Rotation point at index: " + index1);
        System.out.println("Smallest element: " + array1[index1]);
        
        int[] array2 = {3, 4, 5, 1, 2};
        int index2 = findRotationPoint(array2);
        System.out.println("Array: " + java.util.Arrays.toString(array2));
        System.out.println("Rotation point at index: " + index2);
        System.out.println("Smallest element: " + array2[index2]);
    }
}

