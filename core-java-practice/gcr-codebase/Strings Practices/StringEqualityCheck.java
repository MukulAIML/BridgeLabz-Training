import java.util.Scanner;

public class StringEqualityCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String s1 = scanner.next();
        System.out.print("Enter second string: ");
        String s2 = scanner.next();

        boolean charMatch = true;
        if (s1.length() != s2.length()) {
            charMatch = false;
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    charMatch = false;
                    break;
                }
            }
        }

        boolean builtInEqual = s1.equals(s2);

        System.out.println("Are strings equal using charAt: " + charMatch);
        System.out.println("Are strings equal using equals(): " + builtInEqual);

        scanner.close();
    }
}
