import java.util.Scanner;

class PrintJob implements Runnable {
    private String jobName;
    private int numPages;
    private int priority;
    
    public PrintJob(String jobName, int numPages, int priority) {
        this.jobName = jobName;
        this.numPages = numPages;
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }
    
    @Override
    public void run() {
        String priorityLevel = (priority >= 7) ? "High Priority" : (priority >= 4) ? "Medium Priority" : "Low Priority";
        
        System.out.println("[" + priorityLevel + "] Starting " + jobName + " - " + numPages + " pages");
        
        for (int page = 1; page <= numPages; page++) {
            System.out.println("[" + priorityLevel + "] Printing " + jobName + " - Page " + page + " of " + numPages);
            try {
                Thread.sleep(100); // 100ms per page
            } catch (InterruptedException e) {
                System.out.println(jobName + " was interrupted");
            }
        }
        
        System.out.println("✓ " + jobName + " completed!");
    }
}

public class PrintShopScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Print Shop Job Scheduler ===");
        System.out.print("Enter number of print jobs: ");
        int numJobs = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        PrintJob[] jobs = new PrintJob[numJobs];
        Thread[] threads = new Thread[numJobs];
        
        for (int i = 0; i < numJobs; i++) {
            System.out.print("\nEnter job name " + (i + 1) + ": ");
            String jobName = scanner.nextLine();
            System.out.print("Enter number of pages for " + jobName + ": ");
            int numPages = scanner.nextInt();
            System.out.print("Enter priority (1-10) for " + jobName + ": ");
            int priority = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            jobs[i] = new PrintJob(jobName, numPages, priority);
            threads[i] = new Thread(jobs[i]);
            threads[i].setName(jobName);
            
            // Set thread priority based on job priority
            // Map job priority (1-10) to thread priority (1-10)
            threads[i].setPriority(Math.min(10, Math.max(1, priority)));
        }
        
        System.out.println("\n--- Starting print jobs... ---\n");
        long startTime = System.currentTimeMillis();
        
        // Start all print job threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all jobs to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("\n=== All jobs completed in " + totalTime + "ms ===");
        System.out.println("Note: Higher priority jobs may execute first based on thread scheduler");
        
        scanner.close();
    }
}
