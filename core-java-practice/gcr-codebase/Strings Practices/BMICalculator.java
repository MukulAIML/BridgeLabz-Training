import java.util.Scanner;

/**
 * Program to calculate BMI for 10 team members
 * and display height, weight, BMI, and status
 */
public class BMICalculator {
    
    private static final int TEAM_SIZE = 10;
    private static final double UNDERWEIGHT_THRESHOLD = 18.5;
    private static final double NORMAL_THRESHOLD = 25.0;
    private static final double OVERWEIGHT_THRESHOLD = 30.0;
    
    /**
     * Calculates BMI and determines status for a person
     * @param weightInKg Weight in kilograms
     * @param heightInCm Height in centimeters
     * @return String array containing BMI and status
     */
    public static String[] calculateBMIAndStatus(double weightInKg, double heightInCm) {
        double heightInMeters = heightInCm / 100.0;
        double bmi = weightInKg / (heightInMeters * heightInMeters);
        
        String status;
        if (bmi < UNDERWEIGHT_THRESHOLD) {
            status = "Underweight";
        } else if (bmi < NORMAL_THRESHOLD) {
            status = "Normal";
        } else if (bmi < OVERWEIGHT_THRESHOLD) {
            status = "Overweight";
        } else {
            status = "Obese";
        }
        
        return new String[]{String.format("%.2f", bmi), status};
    }
    
    /**
     * Processes all team members' data and computes BMI and status
     * @param teamData 2D array with weight and height data
     * @return 2D String array with height, weight, BMI, and status
     */
    public static String[][] processTeamData(double[][] teamData) {
        String[][] result = new String[TEAM_SIZE][4];
        
        for (int i = 0; i < TEAM_SIZE; i++) {
            double weight = teamData[i][0];
            double height = teamData[i][1];
            String[] bmiData = calculateBMIAndStatus(weight, height);
            
            result[i][0] = String.format("%.2f", height);
            result[i][1] = String.format("%.2f", weight);
            result[i][2] = bmiData[0];
            result[i][3] = bmiData[1];
        }
        
        return result;
    }
    
    /**
     * Displays the team data in tabular format
     * @param teamResults 2D String array with height, weight, BMI, and status
     */
    public static void displayTeamResults(String[][] teamResults) {
        System.out.println("\n================================================");
        System.out.println("           TEAM BMI REPORT");
        System.out.println("================================================");
        System.out.printf("%-8s %-10s %-10s %-10s %-12s%n", 
                         "Person", "Height(cm)", "Weight(kg)", "BMI", "Status");
        System.out.println("------------------------------------------------");
        
        for (int i = 0; i < TEAM_SIZE; i++) {
            System.out.printf("%-8d %-10s %-10s %-10s %-12s%n",
                             (i + 1),
                             teamResults[i][0],
                             teamResults[i][1],
                             teamResults[i][2],
                             teamResults[i][3]);
        }
        
        System.out.println("================================================\n");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] teamData = new double[TEAM_SIZE][2];
        
        try {
            System.out.println("Enter height and weight for " + TEAM_SIZE + " team members:");
            System.out.println("(Height in cm, Weight in kg)");
            
            for (int i = 0; i < TEAM_SIZE; i++) {
                System.out.print("Person " + (i + 1) + " - Weight (kg): ");
                teamData[i][0] = sc.nextDouble();
                
                System.out.print("Person " + (i + 1) + " - Height (cm): ");
                teamData[i][1] = sc.nextDouble();
                
                if (teamData[i][0] <= 0 || teamData[i][1] <= 0) {
                    System.out.println("Error: Weight and height must be positive values.");
                    i--;
                    continue;
                }
            }
            
            String[][] results = processTeamData(teamData);
            displayTeamResults(results);
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid numbers.");
        } finally {
            sc.close();
        }
    }
}

