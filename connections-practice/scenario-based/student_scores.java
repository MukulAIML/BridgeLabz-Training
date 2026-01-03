import java.util.Scanner;

public class StudentScores {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        
        float[] scores = new float[n];
        float sum = 0;
        
        // Input scores with validation
        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Enter score for student " + (i + 1) + ": ");
                float score = sc.nextFloat();
                
                if (score >= 0 && score <= 100) {
                    scores[i] = score;
                    sum += score;
                    break;
                } else {
                    System.out.println("Invalid! Enter score between 0-100");
                }
            }
        }
        
        // Calculate average
        float average = sum / n;
        
        // Find highest and lowest
        float highest = scores[0];
        float lowest = scores[0];
        
        for (int i = 1; i < n; i++) {
            if (scores[i] > highest) highest = scores[i];
            if (scores[i] < lowest) lowest = scores[i];
        }
        
        // Display results
        System.out.println("\n--- Results ---");
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
        
        System.out.println("\nScores Above Average:");
        for (int i = 0; i < n; i++) {
            if (scores[i] > average) {
                System.out.println("Student " + (i + 1) + ": " + scores[i]);
            }
        }
        
        sc.close();
    }
}