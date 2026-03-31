import java.util.Scanner;

/**
 * Problem 8: Compare Two Strings
 * This program compares two strings lexicographically without using built-in compare methods.
 */
public class CompareStrings {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();
        
        int result = compareStrings(str1, str2);
        
        if (result < 0) {
            System.out.println("\"" + str1 + "\" comes before \"" + str2 + "\" in lexicographical order");
        } else if (result > 0) {
            System.out.println("\"" + str2 + "\" comes before \"" + str1 + "\" in lexicographical order");
        } else {
            System.out.println("Both strings are equal");
        }
        
        scanner.close();
    }
    
    /**
     * Compares two strings lexicographically
     * @param str1 First string
     * @param str2 Second string
     * @return Negative if str1 < str2, positive if str1 > str2, 0 if equal
     */
    public static int compareStrings(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        
        for (int i = 0; i < minLength; i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            
            if (ch1 != ch2) {
                return ch1 - ch2;
            }
        }
        
        return str1.length() - str2.length();
    }
}

