public class PerformanceComparison {
    
    public static long testStringBuffer(int numberOfIterations, String testString) {
        long startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        
        for (int i = 0; i < numberOfIterations; i++) {
            stringBuffer.append(testString);
        }
        
        String result = stringBuffer.toString();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    public static long testStringBuilder(int numberOfIterations, String testString) {
        long startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < numberOfIterations; i++) {
            stringBuilder.append(testString);
        }
        
        String result = stringBuilder.toString();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    public static void main(String[] args) {
        int numberOfIterations = 1000000;
        String testString = "hello";
        
        System.out.println("Testing StringBuffer...");
        long stringBufferTime = testStringBuffer(numberOfIterations, testString);
        System.out.println("StringBuffer time: " + (stringBufferTime / 1_000_000.0) + " milliseconds");
        
        System.out.println("Testing StringBuilder...");
        long stringBuilderTime = testStringBuilder(numberOfIterations, testString);
        System.out.println("StringBuilder time: " + (stringBuilderTime / 1_000_000.0) + " milliseconds");
        
        if (stringBuilderTime < stringBufferTime) {
            System.out.println("StringBuilder is faster");
        } else {
            System.out.println("StringBuffer is faster");
        }
    }
}

