import java.util.HashSet;

public class RemoveDuplicates {
    
    public static String removeDuplicates(String input) {
        StringBuilder result = new StringBuilder();
        HashSet<Character> seenCharacters = new HashSet<>();
        
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (!seenCharacters.contains(currentChar)) {
                result.append(currentChar);
                seenCharacters.add(currentChar);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        String input1 = "programming";
        String result1 = removeDuplicates(input1);
        System.out.println("Original: " + input1);
        System.out.println("Without duplicates: " + result1);
        
        String input2 = "hello";
        String result2 = removeDuplicates(input2);
        System.out.println("Original: " + input2);
        System.out.println("Without duplicates: " + result2);
    }
}

