package M1Problems;

import java.util.*;

public class EmailValidator {

    static Set<String> validDepartments = Set.of(
            "sales", "marketing", "IT", "product"
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String email = sc.nextLine();

            if (isValid(email)) {
                System.out.println("Access Granted");
            } else {
                System.out.println("Access Denied");
            }
        }
    }

    static boolean isValid(String email) {

        // check space
        if (email.contains(" ")) return false;

        // split by @
        String[] parts = email.split("@");
        if (parts.length != 2) return false;

        String left = parts[0];
        String right = parts[1];

        // split left by dot
        String[] leftParts = left.split("\\.");
        if (leftParts.length != 2) return false;

        String firstName = leftParts[0];
        String lastWithDigits = leftParts[1];

        // validate firstname
        if (firstName.length() < 3) return false;
        for (char c : firstName.toCharArray()) {
            if (!Character.isLowerCase(c)) return false;
        }

        // separate lastname and digits
        int i = 0;
        while (i < lastWithDigits.length() && Character.isLowerCase(lastWithDigits.charAt(i))) {
            i++;
        }

        String lastName = lastWithDigits.substring(0, i);
        String digits = lastWithDigits.substring(i);

        // validate lastname
        if (lastName.length() < 3) return false;
        for (char c : lastName.toCharArray()) {
            if (!Character.isLowerCase(c)) return false;
        }

        // validate digits
        if (digits.length() < 4) return false;
        for (char c : digits.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        // split right part
        String[] rightParts = right.split("\\.");
        if (rightParts.length != 3) return false;

        String dept = rightParts[0];
        String company = rightParts[1];
        String domain = rightParts[2];

        // validate department
        if (!validDepartments.contains(dept)) return false;

        // validate domain
        if (!company.equals("company") || !domain.equals("com")) return false;

        return true;
    }
}
