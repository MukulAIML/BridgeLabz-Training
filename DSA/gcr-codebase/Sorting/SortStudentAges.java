import java.util.Scanner;

class SortStudentAges {
    private int[] ages;

    public SortStudentAges(int size) {
        ages = new int[size];
    }

    public void inputAges() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + ages.length + " student ages (between 10 and 18):");
        for (int i = 0; i < ages.length; i++) {
            ages[i] = sc.nextInt();
        }
    }

    public void sortAges() {
        int max = 18;
        int min = 10;
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[ages.length];

        for (int age : ages) {
            count[age - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i] - min] - 1] = ages[i];
            count[ages[i] - min]--;
        }

        for (int i = 0; i < ages.length; i++) {
            ages[i] = output[i];
        }
    }

    public void displayAges() {
        System.out.println("Sorted Student Ages:");
        for (int age : ages) {
            System.out.print(age + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int size = sc.nextInt();

        SortStudentAges sa = new SortStudentAges(size);
        sa.inputAges();
        sa.sortAges();
        sa.displayAges();
    }
}
