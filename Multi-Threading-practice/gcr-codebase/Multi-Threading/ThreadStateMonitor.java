import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class TaskRunner extends Thread {
    private String taskName;
    private int sleepDuration;
    
    public TaskRunner(String taskName, int sleepDuration) {
        this.taskName = taskName;
        this.sleepDuration = sleepDuration;
        this.setName(taskName);
    }
    
    @Override
    public void run() {
        System.out.println("[" + taskName + "] Task execution started");
        
        try {
            // TIMED_WAITING state
            System.out.println("[" + taskName + "] Entering sleep for " + sleepDuration + " seconds");
            Thread.sleep(sleepDuration * 1000);
            
            // RUNNABLE state - performing computation
            System.out.println("[" + taskName + "] Performing computation");
            for (int i = 0; i < 5; i++) {
                // Simulate computation
                Math.pow(2, i);
            }
            
        } catch (InterruptedException e) {
            System.out.println("[" + taskName + "] Task was interrupted");
        }
        
        System.out.println("[" + taskName + "] Task execution completed");
    }
}

class StateMonitor extends Thread {
    private TaskRunner[] tasks;
    private DateTimeFormatter timeFormatter;
    private int[] stateChangeCount;
    
    public StateMonitor(TaskRunner[] tasks) {
        this.tasks = tasks;
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.stateChangeCount = new int[tasks.length];
    }
    
    @Override
    public void run() {
        boolean allTerminated = false;
        Thread.State[] previousStates = new Thread.State[tasks.length];
        
        // Initialize previous states
        for (int i = 0; i < tasks.length; i++) {
            previousStates[i] = tasks[i].getState();
            System.out.println("[Monitor] " + tasks[i].getName() + " is in " + previousStates[i] + " state at " + LocalTime.now().format(timeFormatter));
            stateChangeCount[i] = 1;
        }
        
        while (!allTerminated) {
            try {
                Thread.sleep(500); // Check every 500ms
                
                allTerminated = true;
                for (int i = 0; i < tasks.length; i++) {
                    Thread.State currentState = tasks[i].getState();
                    
                    // Check if state changed
                    if (currentState != previousStates[i]) {
                        System.out.println("[Monitor] " + tasks[i].getName() + " is in " + currentState + " state at " + LocalTime.now().format(timeFormatter));
                        previousStates[i] = currentState;
                        stateChangeCount[i]++;
                    }
                    
                    if (currentState != Thread.State.TERMINATED) {
                        allTerminated = false;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("[Monitor] Monitoring interrupted");
                break;
            }
        }
        
        // Display summary
        System.out.println("\n=== Monitoring Summary ===");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(tasks[i].getName() + " went through " + stateChangeCount[i] + " states");
        }
    }
}

public class ThreadStateMonitor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Thread State Monitoring System ===");
        System.out.print("Enter number of tasks to monitor: ");
        int numTasks = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        TaskRunner[] tasks = new TaskRunner[numTasks];
        
        for (int i = 0; i < numTasks; i++) {
            System.out.print("Enter task name " + (i + 1) + ": ");
            String taskName = scanner.nextLine();
            System.out.print("Enter sleep duration (seconds) for " + taskName + ": ");
            int sleepDuration = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            tasks[i] = new TaskRunner(taskName, sleepDuration);
        }
        
        // Create and start the monitor
        StateMonitor monitor = new StateMonitor(tasks);
        monitor.start();
        
        // Small delay to let monitor capture NEW state
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Start all task threads
        for (TaskRunner task : tasks) {
            task.start();
        }
        
        // Wait for monitor to complete
        try {
            monitor.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        System.out.println("\n✓ Monitoring completed");
        scanner.close();
    }
}
