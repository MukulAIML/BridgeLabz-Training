package M1Problems;

import java.util.*;

class Student {
    String name;
    String dept;
    int q1, q2, q3;

    Student(String name, String dept, int q1, int q2, int q3) {
        this.name = name;
        this.dept = dept;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    int total() {
        return q1 + q2 + q3;
    }
}

public class QuizRankingSystem {

    static List<Student> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equals("Record")) {
                record(parts);
            } else if (parts[0].equals("Top")) {
                top(parts[1]);
            }
        }
    }

    static void record(String[] parts) {
        String name = parts[1];
        String dept = parts[2];
        int q1 = Integer.parseInt(parts[3]);
        int q2 = Integer.parseInt(parts[4]);
        int q3 = Integer.parseInt(parts[5]);

        list.add(new Student(name, dept, q1, q2, q3));

        System.out.println("Record Added: " + name);
    }

    static void top(String key) {

        if (list.isEmpty()) {
            System.out.println("No Records Available");
            return;
        }

        if (key.equals("Q1") || key.equals("Q2") || key.equals("Q3")) {
            topQuiz(key);
        } else {
            topDepartment(key);
        }
    }

    static void topDepartment(String dept) {

        int max = Integer.MIN_VALUE;
        boolean found = false;

        for (Student s : list) {
            if (s.dept.equals(dept)) {
                found = true;
                max = Math.max(max, s.total());
            }
        }

        if (!found) {
            System.out.println("Department Not Found");
            return;
        }

        for (Student s : list) {
            if (s.dept.equals(dept) && s.total() == max) {
                System.out.println(s.name + " " + s.total());
            }
        }
    }

    static void topQuiz(String quiz) {

        int max = Integer.MIN_VALUE;

        for (Student s : list) {
            if (quiz.equals("Q1")) max = Math.max(max, s.q1);
            if (quiz.equals("Q2")) max = Math.max(max, s.q2);
            if (quiz.equals("Q3")) max = Math.max(max, s.q3);
        }

        for (Student s : list) {
            if (quiz.equals("Q1") && s.q1 == max) {
                System.out.println(s.name + " " + s.q1);
            }
            if (quiz.equals("Q2") && s.q2 == max) {
                System.out.println(s.name + " " + s.q2);
            }
            if (quiz.equals("Q3") && s.q3 == max) {
                System.out.println(s.name + " " + s.q3);
            }
        }
    }
}