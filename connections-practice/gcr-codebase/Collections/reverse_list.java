import java.util.*;

public class ReverseList {
    public static <T> void reverseList(List<T> list) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            T temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        // ArrayList example
        List<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Original ArrayList: " + arrayList);
        reverseList(arrayList);
        System.out.println("Reversed ArrayList: " + arrayList);

        // LinkedList example
        List<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\nOriginal LinkedList: " + linkedList);
        reverseList(linkedList);
        System.out.println("Reversed LinkedList: " + linkedList);
    }
}