public class SearchWordInSentences {
    
    public static String findSentenceWithWord(String[] sentences, String targetWord) {
        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].contains(targetWord)) {
                return sentences[i];
            }
        }
        return "Not Found";
    }
    
    public static void main(String[] args) {
        String[] sentences = {
            "Java is a programming language",
            "Python is easy to learn",
            "C++ is powerful"
        };
        
        String targetWord = "Java";
        String result = findSentenceWithWord(sentences, targetWord);
        System.out.println("Searching for word: " + targetWord);
        System.out.println("Result: " + result);
        
        String targetWord2 = "JavaScript";
        String result2 = findSentenceWithWord(sentences, targetWord2);
        System.out.println("Searching for word: " + targetWord2);
        System.out.println("Result: " + result2);
    }
}

