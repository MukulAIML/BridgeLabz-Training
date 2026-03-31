public class FirstNegativeNumber {
    
    public static int findFirstNegative(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] array1 = {1, 2, -3, 4, 5};
        int index1 = findFirstNegative(array1);
        System.out.println("Array: " + java.util.Arrays.toString(array1));
        System.out.println("First negative number at index: " + index1);
        
        int[] array2 = {1, 2, 3, 4, 5};
        int index2 = findFirstNegative(array2);
        System.out.println("Array: " + java.util.Arrays.toString(array2));
        System.out.println("First negative number at index: " + index2);
    }
}

