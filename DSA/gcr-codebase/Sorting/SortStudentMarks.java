import java.util.Scanner;

class StudentMarks {

    private int[] marks;

    public StudentMarks(int size) {
        marks = new int[size];
    }

    public void inputMarks() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + marks.length + " student marks:");
        for (int i = 0; i < marks.length; i++) {
            marks[i] = sc.nextInt();
        }
    }

    public void sortMarks() {
        int n = marks.length;
        for (int i = 0; i < n - 1; i++) { // ✅ Fixed missing semicolon
            boolean swap = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (marks[j] > marks[j + 1]) {
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }

    public void displayMarks() {
        System.out.println("Sorted marks:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println(); 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of students: "); 
        int size = sc.nextInt();
        StudentMarks student = new StudentMarks(size);
        student.inputMarks();
        student.sortMarks();
        student.displayMarks();
    }
}
