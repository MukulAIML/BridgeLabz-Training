import java.util.Scanner;

class ExamScores {
    private int[] scores;

    public ExamScores(int size) {
        scores = new int[size];
    }

    public void inputScores() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + scores.length + " exam scores:");
        for (int i = 0; i < scores.length; i++) {
            scores[i] = sc.nextInt();
        }
    }

    public void sortScores() {
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] < scores[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = scores[i];
            scores[i] = scores[minIndex];
            scores[minIndex] = temp;
        }
    }

    public void displayScores() {
        System.out.println("Sorted Exam Scores:");
        for (int score : scores) {
            System.out.print(score + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int size = sc.nextInt();

        ExamScores exam = new ExamScores(size);
        exam.inputScores();
        exam.sortScores();
        exam.displayScores();
    }
}
