import java.util.Scanner;

public class FlipKey {
    
    public static String CleanseAndInvert(String input) {
        // Check if input is null or less than 6 characters
        if (input == null || input.length() < 6) {
            return "";
        }
        
        // Check for spaces, digits, or special characters
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                return "";
            }
        }
        
        // Convert to lowercase
        String lowercase = input.toLowerCase();
        
        // Remove characters with even ASCII values
        StringBuilder filtered = new StringBuilder();
        for (char c : lowercase.toCharArray()) {
            if ((int) c % 2 != 0) {
                filtered.append(c);
            }
        }
        
        // Reverse the string
        String reversed = filtered.reverse().toString();
        
        // Convert characters at even positions to uppercase
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(reversed.charAt(i)));
            } else {
                result.append(reversed.charAt(i));
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the word");
        String input = scanner.nextLine();
        
        String generatedKey = CleanseAndInvert(input);
        
        if (generatedKey.isEmpty()) {
            System.out.println("Invalid Input");
        } else {
            System.out.println("The generated key is - " + generatedKey);
        }
        
        scanner.close();
    }
}
