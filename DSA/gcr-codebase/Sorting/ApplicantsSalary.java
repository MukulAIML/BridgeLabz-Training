import java.util.Scanner;

class ApplicantsSalary {
    private int[] salaries;

    public ApplicantsSalary(int size) {
        salaries = new int[size];
    }

    public void inputSalaries() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + salaries.length + " salary demands:");
        for (int i = 0; i < salaries.length; i++) {
            salaries[i] = sc.nextInt();
        }
    }

    public void sortSalaries() {
        int n = salaries.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = salaries[0];
            salaries[0] = salaries[i];
            salaries[i] = temp;
            heapify(i, 0);
        }
    }

    private void heapify(int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && salaries[left] > salaries[largest]) {
            largest = left;
        }

        if (right < size && salaries[right] > salaries[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = salaries[i];
            salaries[i] = salaries[largest];
            salaries[largest] = swap;
            heapify(size, largest);
        }
    }

    public void displaySalaries() {
        System.out.println("Sorted Salary Demands:");
        for (int salary : salaries) {
            System.out.print(salary + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of job applicants: ");
        int size = sc.nextInt();

        ApplicantsSalary sorter = new ApplicantsSalary(size);
        sorter.inputSalaries();
        sorter.sortSalaries();
        sorter.displaySalaries();
    }
}
