public class ConcatenateStrings {
    
    public static String concatenateStrings(String[] stringArray) {
        StringBuffer stringBuffer = new StringBuffer();
        
        for (int i = 0; i < stringArray.length; i++) {
            stringBuffer.append(stringArray[i]);
        }
        
        return stringBuffer.toString();
    }
    
    public static void main(String[] args) {
        String[] array1 = {"Hello", " ", "World", "!"};
        String result1 = concatenateStrings(array1);
        System.out.println("Array: " + java.util.Arrays.toString(array1));
        System.out.println("Concatenated: " + result1);
        
        String[] array2 = {"Java", " is", " fun"};
        String result2 = concatenateStrings(array2);
        System.out.println("Array: " + java.util.Arrays.toString(array2));
        System.out.println("Concatenated: " + result2);
    }
}

