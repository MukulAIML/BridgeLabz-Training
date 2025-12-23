import java.util.Scanner;

/**
 * Problem 6: Find Substring Occurrences
 * This program counts how many times a substring occurs in a string.
 */
public class SubstringOccurrences {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the main string: ");
        String mainString = scanner.nextLine();
        
        System.out.print("Enter the substring to find: ");
        String substring = scanner.nextLine();
        
        int count = countSubstringOccurrences(mainString, substring);
        
        System.out.println("The substring appears " + count + " time(s).");
        
        scanner.close();
    }
    
    /**
     * Counts occurrences of a substring in a string
     * @param mainString The main string to search in
     * @param substring The substring to find
     * @return Number of occurrences
     */
    public static int countSubstringOccurrences(String mainString, String substring) {
        if (substring.length() == 0) {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        
        while ((index = mainString.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        
        return count;
    }
}

