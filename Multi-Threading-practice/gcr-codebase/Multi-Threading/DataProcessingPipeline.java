import java.util.Scanner;

class DataProcessor extends Thread {
    private String processorName;
    private int[] data;
    private String operation;
    private int result;
    
    public DataProcessor(String processorName, int[] data, String operation) {
        this.processorName = processorName;
        this.data = data;
        this.operation = operation;
        this.setName(processorName);
    }
    
    @Override
    public void run() {
        System.out.println("[" + processorName + "] Starting " + operation + " operation on " + data.length + " elements");
        
        try {
            switch (operation.toLowerCase()) {
                case "sum":
                    result = calculateSum();
                    break;
                case "average":
                    result = calculateAverage();
                    break;
                case "max":
                    result = findMax();
                    break;
                case "min":
                    result = findMin();
                    break;
                default:
                    result = 0;
                    System.out.println("[" + processorName + "] Unknown operation");
                    return;
            }
            
            // Simulate processing time
            Thread.sleep(500);
            
            System.out.println("✓ [" + processorName + "] " + operation + " result: " + result);
            
        } catch (InterruptedException e) {
            System.out.println("[" + processorName + "] Processing interrupted");
        }
    }
    
    private int calculateSum() {
        int sum = 0;
        for (int num : data) {
            sum += num;
        }
        return sum;
    }
    
    private int calculateAverage() {
        if (data.length == 0) return 0;
        return calculateSum() / data.length;
    }
    
    private int findMax() {
        if (data.length == 0) return 0;
        int max = data[0];
        for (int num : data) {
            if (num > max) max = num;
        }
        return max;
    }
    
    private int findMin() {
        if (data.length == 0) return 0;
        int min = data[0];
        for (int num : data) {
            if (num < min) min = num;
        }
        return min;
    }
    
    public int getResult() {
        return result;
    }
}

public class DataProcessingPipeline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Data Processing Pipeline ===");
        System.out.print("Enter number of elements in dataset: ");
        int numElements = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        int[] dataset = new int[numElements];
        System.out.println("Enter " + numElements + " numbers:");
        for (int i = 0; i < numElements; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            dataset[i] = scanner.nextInt();
        }
        scanner.nextLine(); // Consume newline
        
        System.out.print("\nEnter number of processing operations: ");
        int numOperations = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        DataProcessor[] processors = new DataProcessor[numOperations];
        
        System.out.println("\nAvailable operations: sum, average, max, min");
        for (int i = 0; i < numOperations; i++) {
            System.out.print("Enter processor name " + (i + 1) + ": ");
            String processorName = scanner.nextLine();
            System.out.print("Enter operation for " + processorName + ": ");
            String operation = scanner.nextLine();
            
            processors[i] = new DataProcessor(processorName, dataset, operation);
        }
        
        System.out.println("\n--- Starting data processing pipeline ---\n");
        long startTime = System.currentTimeMillis();
        
        // Start all processor threads
        for (DataProcessor processor : processors) {
            processor.start();
        }
        
        // Wait for all processors to complete
        try {
            for (DataProcessor processor : processors) {
                processor.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("\n=== Processing Summary ===");
        for (DataProcessor processor : processors) {
            System.out.println(processor.getName() + " final result: " + processor.getResult());
        }
        System.out.println("Total processing time: " + totalTime + "ms");
        System.out.println("\n✓ All processing operations completed!");
        
        scanner.close();
    }
}
