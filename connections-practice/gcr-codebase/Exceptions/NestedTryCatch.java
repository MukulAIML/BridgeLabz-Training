import java.util.Scanner;

public class NestedTryCatch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {10, 20, 30, 40};
        
        try {
            System.out.println("Array: {10, 20, 30, 40}");
            System.out.print("Enter index (0-3): ");
            int index = sc.nextInt();
            int element = arr[index];
            System.out.println("Element at index " + index + ": " + element);
            
            try {
                System.out.print("Enter divisor: ");
                int divisor = sc.nextInt();
                int result = element / divisor;
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero!");
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index!");
        } finally {
            sc.close();
        }
    }
}