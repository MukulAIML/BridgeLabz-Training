package M1Problems;

import java.util.*;

public class TemplateProcessor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            System.out.println(processLine(line));
        }
    }

    static String processLine(String line) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            if (i + 1 < line.length() && line.charAt(i) == '$' && line.charAt(i + 1) == '{') {

                int end = line.indexOf('}', i);
                if (end == -1) {
                    result.append("INVALID");
                    break;
                }

                String content = line.substring(i + 2, end);
                String replacement = processPlaceholder(content);

                result.append(replacement);

                i = end;
            } else {
                result.append(line.charAt(i));
            }
        }

        return result.toString();
    }

    static String processPlaceholder(String content) {

        String[] parts = content.split(":", 2);
        if (parts.length != 2) return "INVALID";

        String type = parts[0];
        String value = parts[1];

        switch (type) {

            case "UPPER":
                return value.toUpperCase();

            case "LOWER":
                return value.toLowerCase();

            case "DATE":
                return processDate(value);

            case "REPEAT":
                return processRepeat(value);

            default:
                return "INVALID";
        }
    }

    static String processDate(String value) {

        String[] parts = value.split("-");
        if (parts.length != 3) return "INVALID";

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (month < 1 || month > 12) return "INVALID";

            int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};

            if (isLeap(year)) days[1] = 29;

            if (day < 1 || day > days[month - 1]) return "INVALID";

            return year + "/" + String.format("%02d", month) + "/" + String.format("%02d", day);

        } catch (Exception e) {
            return "INVALID";
        }
    }

    static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    static String processRepeat(String value) {

        String[] parts = value.split(",");
        if (parts.length != 2) return "INVALID";

        String word = parts[0];

        try {
            int count = Integer.parseInt(parts[1]);

            if (count < 0) return "INVALID";

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(word);
            }

            return sb.toString();

        } catch (Exception e) {
            return "INVALID";
        }
    }
}