public class FibonacciComparison {
    
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
    
    public static void main(String[] args) {
        int[] values = {10, 30, 50};
        
        for (int n : values) {
            System.out.println("Fibonacci(" + n + ")");
            
            // Recursive approach
            if (n <= 30) {
                long start = System.nanoTime();
                int result = fibonacciRecursive(n);
                long time = (System.nanoTime() - start) / 1_000_000;
                System.out.println("Recursive: " + time + "ms, Result: " + result);
            } else {
                System.out.println("Recursive: Unfeasible for large values");
            }
            
            // Iterative approach
            long start = System.nanoTime();
            int result = fibonacciIterative(n);
            long time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Iterative: " + time + "ms, Result: " + result);
            System.out.println();
        }
    }
}

