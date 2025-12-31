import java.util.Scanner;

class EmployeeIDs {
    private int[] ids;

    public EmployeeIDs(int size) {
        ids = new int[size];
    }

    public void inputIDs() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + ids.length + " employee IDs:");
        for (int i = 0; i < ids.length; i++) {
            ids[i] = sc.nextInt();
        }
    }

    public void sortIDs() {
        for (int i = 1; i < ids.length; i++) {
            int key = ids[i];
            int j = i - 1;
            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j--;
            }
            ids[j + 1] = key;
        }
    }

    public void displayIDs() {
        System.out.println("Sorted Employee IDs:");
        for (int id : ids) {
            System.out.print(id + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int size = sc.nextInt();

        EmployeeIDs emp = new EmployeeIDs(size);
        emp.inputIDs();
        emp.sortIDs();
        emp.displayIDs();
    }
}
