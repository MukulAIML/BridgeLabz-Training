import java.util.Scanner;

public class FootballTeamHeightStats {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] heights = new double[11];
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;

        for (int i = 0; i < 11; i++) {
            System.out.print("Enter height of player " + (i + 1) + ": ");
            heights[i] = sc.nextDouble();
            if (heights[i] < min) min = heights[i];
            if (heights[i] > max) max = heights[i];
            sum += heights[i];
        }

        double avg = sum / 11;
        System.out.printf("Average height: %.2f, Min height: %.2f, Max height: %.2f%n", avg, min, max);
    }
}