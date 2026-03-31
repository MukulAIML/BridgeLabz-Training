// Background Job Execution using Runnable
import java.util.Scanner;

public class BackgroundJobExecution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter job name: ");
        String jobName = scanner.nextLine();
        
        Runnable task1 = () -> {
            System.out.println("Task 1: Processing " + jobName + "...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 1: Completed");
        };
        
        Runnable task2 = () -> {
            System.out.println("Task 2: Sending notifications...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2: Completed");
        };
        
        Runnable task3 = () -> {
            System.out.println("Task 3: Updating database...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 3: Completed");
        };
        
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        
        System.out.println("\nStarting background jobs...");
        t1.start();
        t2.start();
        t3.start();
        
        System.out.println("All jobs started in background!");
        
        scanner.close();
    }
}
