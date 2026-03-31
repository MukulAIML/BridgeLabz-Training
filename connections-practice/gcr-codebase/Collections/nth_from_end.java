import java.util.*;

public class NthFromEnd {
    public static <T> T findNthFromEnd(LinkedList<T> list, int n) {
        if (n <= 0 || list.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        int index = 0;
        int targetIndex = -1;
        
        for (T element : list) {
            if (index >= n) {
                targetIndex++;
            }
            index++;
        }
        
        if (targetIndex == -1 || n > index) {
            throw new IllegalArgumentException("N is larger than list size");
        }
        
        return list.get(targetIndex + 1);
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("List: " + list);
        System.out.println("2nd element from end: " + findNthFromEnd(list, 2));
    }
}