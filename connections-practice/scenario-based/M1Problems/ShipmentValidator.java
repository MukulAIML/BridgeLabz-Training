package M1Problems;

import java.util.*;

public class ShipmentValidator {

    static Set<String> validModes = Set.of(
            "AIR", "SEA", "ROAD", "RAIL", "EXPRESS", "FREIGHT"
    );

    static Set<String> validStatus = Set.of(
            "DELIVERED", "CANCELLED", "IN_TRANSIT"
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String record = sc.nextLine();

            if (isValid(record)) {
                System.out.println("COMPLIANT RECORD");
            } else {
                System.out.println("NON-COMPLIANT RECORD");
            }
        }
    }

    static boolean isValid(String record) {
        String[] parts = record.split("\\|");
        if (parts.length != 5) return false;

        return validateCode(parts[0])
                && validateDate(parts[1])
                && validateMode(parts[2])
                && validateWeight(parts[3])
                && validateStatus(parts[4]);
    }

    // 🔹 1. Code Validation (Manual)
    static boolean validateCode(String code) {
        if (code.length() != 11) return false;

        if (!code.startsWith("SHIP-")) return false;

        String digits = code.substring(5);

        // Check digits
        for (char c : digits.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        // First digit not zero
        if (digits.charAt(0) == '0') return false;

        // No more than 3 consecutive same digits
        int count = 1;
        for (int i = 1; i < digits.length(); i++) {
            if (digits.charAt(i) == digits.charAt(i - 1)) {
                count++;
                if (count > 3) return false;
            } else {
                count = 1;
            }
        }

        return true;
    }

    // 🔹 2. Date Validation (Manual)
    static boolean validateDate(String date) {
        if (date.length() != 10) return false;

        // Format check YYYY-MM-DD
        if (date.charAt(4) != '-' || date.charAt(7) != '-') return false;

        try {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));

            if (year < 2000 || year > 2099) return false;

            if (month < 1 || month > 12) return false;

            int[] daysInMonth = {
                    31, 28, 31, 30, 31, 30,
                    31, 31, 30, 31, 30, 31
            };

            // Leap year
            if (isLeapYear(year)) {
                daysInMonth[1] = 29;
            }

            if (day < 1 || day > daysInMonth[month - 1]) return false;

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 🔹 3. Mode Validation
    static boolean validateMode(String mode) {
        return validModes.contains(mode);
    }

    // 🔹 4. Weight Validation (Manual)
    static boolean validateWeight(String weight) {

        // Check characters
        int dotCount = 0;
        for (char c : weight.toCharArray()) {
            if (c == '.') dotCount++;
            else if (!Character.isDigit(c)) return false;
        }

        if (dotCount > 1) return false;

        String[] parts = weight.split("\\.");

        String intPart = parts[0];

        // No leading zero unless exactly "0"
        if (intPart.length() > 1 && intPart.startsWith("0")) return false;

        // Decimal check
        if (parts.length == 2) {
            if (parts[1].length() > 2) return false;
        }

        double val;
        try {
            val = Double.parseDouble(weight);
        } catch (Exception e) {
            return false;
        }

        return val > 0 && val <= 999999.99;
    }

    // 🔹 5. Status Validation
    static boolean validateStatus(String status) {
        return validStatus.contains(status);
    }
}
