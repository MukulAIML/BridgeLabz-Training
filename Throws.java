import java.util.Scanner;

class ThrowsExample {

    static void checkAge(int age) throws Exception {
        if (age < 18) {
            throw new Exception("Not eligible to vote");
        }
        System.out.println("Eligible to vote");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        try {
            checkAge(age);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        sc.close();
    }
}
