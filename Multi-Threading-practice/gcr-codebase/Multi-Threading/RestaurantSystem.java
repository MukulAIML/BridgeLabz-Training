import java.util.Scanner;

class Chef extends Thread {
    private String chefName;
    private String dishName;
    private int preparationTime; // in seconds
    
    public Chef(String chefName, String dishName, int preparationTime) {
        this.chefName = chefName;
        this.dishName = dishName;
        this.preparationTime = preparationTime;
    }
    
    @Override
    public void run() {
        System.out.println("Chef-" + chefName + " started preparing " + dishName);
        
        int sleepInterval = (preparationTime * 1000) / 4; // Divide into 4 parts for progress display
        
        try {
            for (int progress = 25; progress <= 100; progress += 25) {
                Thread.sleep(sleepInterval);
                System.out.println("Chef-" + chefName + " preparing " + dishName + ": " + progress + "% complete");
            }
        } catch (InterruptedException e) {
            System.out.println("Chef-" + chefName + " was interrupted");
        }
        
        System.out.println("✓ Chef-" + chefName + " finished preparing " + dishName);
    }
}

public class RestaurantSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Restaurant Order Processing System ===");
        System.out.print("Enter number of chefs: ");
        int numChefs = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Chef[] chefs = new Chef[numChefs];
        
        for (int i = 0; i < numChefs; i++) {
            System.out.print("Enter chef name " + (i + 1) + ": ");
            String chefName = scanner.nextLine();
            System.out.print("Enter dish name for Chef-" + chefName + ": ");
            String dishName = scanner.nextLine();
            System.out.print("Enter preparation time in seconds for " + dishName + ": ");
            int prepTime = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            chefs[i] = new Chef(chefName, dishName, prepTime);
        }
        
        System.out.println("\n--- Kitchen Opening ---\n");
        
        // Start all chef threads
        for (Chef chef : chefs) {
            chef.start();
        }
        
        // Manager waits for all chefs to complete
        try {
            for (Chef chef : chefs) {
                chef.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Manager was interrupted");
        }
        
        System.out.println("\n=== Kitchen closed - All orders completed ===");
        scanner.close();
    }
}
