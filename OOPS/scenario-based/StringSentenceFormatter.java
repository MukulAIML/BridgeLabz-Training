/*Strings – Sentence Formatter
1. Scenario: A text editing tool receives poorly formatted input from users. Your task is to auto-correct formatting by fixing spacing and capitalizing the first letter of each sentence.
Problem:
Write a method that takes a paragraph as input and returns a corrected version with:
● One space after punctuation,
● Capital letter after period/question/exclamation marks,
● Trimmed extra space 
*/

public class StringSentenceFormatter {
    public static void main(String[] args) {
        String text = "this is a test.  this is only a test!is it working? yes,it is.   ";
        String result = formatText(text);
        System.out.println(result);
    }

    public static String formatText(String text) {
        // Remove extra whitespace from start and end
        text = text.trim();

        // Normalize whitespace to single spaces
        text = text.replaceAll("\\s+", " ");

        // Add space after sentence-ending punctuation if missing
        text = text.replaceAll("([.!?])(\\S)", "$1 $2");

        // Make first letter of sentences uppercase
        StringBuilder output = new StringBuilder();
        boolean nextIsStart = true;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (nextIsStart && Character.isLetter(ch)) {
                output.append(Character.toUpperCase(ch));
                nextIsStart = false;
            } else {
                output.append(ch);
            }
            if (ch == '.' || ch == '!' || ch == '?') {
                nextIsStart = true;
            }
        }

        return output.toString().trim();
    }
}