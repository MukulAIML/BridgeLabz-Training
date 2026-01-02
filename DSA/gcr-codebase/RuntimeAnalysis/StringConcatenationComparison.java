public class StringConcatenationComparison {
    
    public static void stringConcatenation(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "Hello";
        }
    }
    
    public static void stringBuilderConcatenation(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("Hello");
        }
        String result = sb.toString();
    }
    
    public static void stringBufferConcatenation(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("Hello");
        }
        String result = sb.toString();
    }
    
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            System.out.println("Operations: " + size);
            
            // String concatenation
            if (size <= 10000) {
                long start = System.nanoTime();
                stringConcatenation(size);
                long time = (System.nanoTime() - start) / 1_000_000;
                System.out.println("String: " + time + "ms");
            } else {
                System.out.println("String: Unfeasible for large operations");
            }
            
            // StringBuilder concatenation
            long start = System.nanoTime();
            stringBuilderConcatenation(size);
            long time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("StringBuilder: " + time + "ms");
            
            // StringBuffer concatenation
            start = System.nanoTime();
            stringBufferConcatenation(size);
            time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("StringBuffer: " + time + "ms");
            System.out.println();
        }
    }
}

