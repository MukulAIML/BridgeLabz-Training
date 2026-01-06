import java.util.*;

// Custom Exception
class ExamTimeExpiredException extends Exception {
    public ExamTimeExpiredException(String msg) {
        super(msg);
    }
}

// Interface for evaluation
interface EvaluationStrategy {
    int evaluate(String correct, String answer, int marks);
}

// Objective evaluation
class ObjectiveEval implements EvaluationStrategy {
    public int evaluate(String correct, String answer, int marks) {
        return correct.equalsIgnoreCase(answer) ? marks : 0;
    }
}

// Descriptive evaluation
class DescriptiveEval implements EvaluationStrategy {
    public int evaluate(String correct, String answer, int marks) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Award marks (max " + marks + "): ");
        return sc.nextInt();
    }
}

// Question class
class Question {
    String text, answer, type;
    int marks;

    public Question(String text, String answer, int marks, String type) {
        this.text = text;
        this.answer = answer;
        this.marks = marks;
        this.type = type;
    }
}

// Generic Result class
class Result<T> {
    T studentName;
    int score, total;

    public Result(T studentName, int score, int total) {
        this.studentName = studentName;
        this.score = score;
        this.total = total;
    }

    void display() {
        System.out.println("\n--- RESULT ---");
        System.out.println("Student: " + studentName);
        System.out.println("Score: " + score + "/" + total);
        System.out.println("Percentage: " + (score * 100.0 / total) + "%");
    }
}

// Exam class
class Exam {
    int id;
    String name;
    ArrayList<Question> questions = new ArrayList<>();

    public Exam(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// Main System
public class OnlineExamSystem {
    static HashMap<Integer, Exam> exams = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static int examId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Create Exam  2.Take Exam  3.Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) createExam();
            else if (choice == 2) takeExam();
            else break;
        }
    }

    static void createExam() {
        System.out.print("Exam name: ");
        String name = sc.nextLine();
        Exam exam = new Exam(examId, name);

        System.out.print("Number of questions: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Question " + (i + 1) + ": ");
            String qText = sc.nextLine();

            System.out.print("Type (obj/desc): ");
            String type = sc.nextLine();

            System.out.print("Correct answer: ");
            String ans = sc.nextLine();

            System.out.print("Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            exam.questions.add(new Question(qText, ans, marks, type));
        }

        exams.put(examId, exam);
        System.out.println("Exam created! ID: " + examId);
        examId++;
    }

    static void takeExam() {
        try {
            System.out.print("Exam ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Student Name: ");
            String studentName = sc.nextLine();

            if (!exams.containsKey(id)) {
                System.out.println("Invalid Exam!");
                return;
            }

            System.out.print("Time expired? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                throw new ExamTimeExpiredException("Time's Up!");
            }

            Exam exam = exams.get(id);
            int score = 0, total = 0;

            for (Question q : exam.questions) {
                System.out.println("\n" + q.text);
                System.out.print("Answer: ");
                String ans = sc.nextLine();

                EvaluationStrategy eval =
                        q.type.equalsIgnoreCase("obj")
                                ? new ObjectiveEval()
                                : new DescriptiveEval();

                score += eval.evaluate(q.answer, ans, q.marks);
                total += q.marks;
            }

            new Result<>(studentName, score, total).display();

        } catch (ExamTimeExpiredException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }
}
