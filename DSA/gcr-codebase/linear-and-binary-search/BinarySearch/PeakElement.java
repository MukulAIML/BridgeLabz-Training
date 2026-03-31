public class PeakElement {
    
    public static int findPeakElement(int[] array) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] > array[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 1};
        int index1 = findPeakElement(array1);
        System.out.println("Array: " + java.util.Arrays.toString(array1));
        System.out.println("Peak element at index: " + index1);
        System.out.println("Peak element value: " + array1[index1]);
        
        int[] array2 = {1, 2, 1, 3, 5, 6, 4};
        int index2 = findPeakElement(array2);
        System.out.println("Array: " + java.util.Arrays.toString(array2));
        System.out.println("Peak element at index: " + index2);
        System.out.println("Peak element value: " + array2[index2]);
    }
}

