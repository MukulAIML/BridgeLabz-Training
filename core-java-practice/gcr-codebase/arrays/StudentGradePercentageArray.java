import java.util.Scanner;

public class StudentGradePercentageArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] physics = new int[n];
        int[] chemistry = new int[n];
        int[] maths = new int[n];
        double[] percentage = new double[n];
        char[] grade = new char[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter marks for Student " + (i + 1) + ":");
            System.out.print("Physics: ");
            physics[i] = sc.nextInt();
            if (physics[i] < 0) {
                System.out.println("Invalid marks. Please enter valid marks.");
                i--;
                continue;
            }

            System.out.print("Chemistry: ");
            chemistry[i] = sc.nextInt();
            if (chemistry[i] < 0) {
                System.out.println("Invalid marks. Please enter valid marks.");
                i--;
                continue;
            }

            System.out.print("Maths: ");
            maths[i] = sc.nextInt();
            if (maths[i] < 0) {
                System.out.println("Invalid marks. Please enter valid marks.");
                i--;
                continue;
            }

            int total = physics[i] + chemistry[i] + maths[i];
            percentage[i] = total / 3.0;

            grade[i] = getGrade(percentage[i]);
        }

        System.out.println("\nStudent Results:");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ": Physics = " + physics[i] + ", Chemistry = " + chemistry[i]
                    + ", Maths = " + maths[i]);
            System.out.println("Percentage: " + String.format("%.3f", percentage[i]) + "%, Grade: " + grade[i]);
            System.out.println("-----------------------");
        }
    }

    public static char getGrade(double percent) {
        if (percent >= 80)
            return 'A';
        else if (percent >= 70)
            return 'B';
        else if (percent >= 60)
            return 'C';
        else if (percent >= 50)
            return 'D';
        else if (percent >= 40)
            return 'E';
        else
            return 'R';
    }
}
