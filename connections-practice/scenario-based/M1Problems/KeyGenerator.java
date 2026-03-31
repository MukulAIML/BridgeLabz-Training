package M1Problems;

import java.util.*;

public class KeyGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();

            String result = process(input);

            if (result.startsWith("Invalid")) {
                System.out.println(result);
            } else {
                System.out.println("The generated key is - " + result);
            }
        }
    }

    static String process(String str) {

        // check empty string
        if (str == null || str.length() == 0) {
            return "Invalid Input (empty string)";
        }

        // check length
        if (str.length() < 6) {
            return "Invalid Input (length < 6)";
        }

        // check characters
        for (char c : str.toCharArray()) {

            if (c == ' ') {
                return "Invalid Input (contains space)";
            }

            if (Character.isDigit(c)) {
                return "Invalid Input (contains digits)";
            }

            if (!Character.isLetter(c)) {
                return "Invalid Input (contains special character)";
            }
        }

        // convert to lowercase
        str = str.toLowerCase();

        // remove even ascii characters
        StringBuilder filtered = new StringBuilder();
        for (char c : str.toCharArray()) {
            if ((int) c % 2 != 0) {
                filtered.append(c);
            }
        }

        // reverse string
        filtered.reverse();

        // uppercase even index positions
        for (int i = 0; i < filtered.length(); i++) {
            if (i % 2 == 0) {
                filtered.setCharAt(i, Character.toUpperCase(filtered.charAt(i)));
            }
        }

        return filtered.toString();
    }
}