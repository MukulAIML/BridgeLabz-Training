public class ReverseString {
    
    public static String reverseString(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) {
        String input1 = "hello";
        String result1 = reverseString(input1);
        System.out.println("Original: " + input1);
        System.out.println("Reversed: " + result1);
        
        String input2 = "Java";
        String result2 = reverseString(input2);
        System.out.println("Original: " + input2);
        System.out.println("Reversed: " + result2);
    }
}

