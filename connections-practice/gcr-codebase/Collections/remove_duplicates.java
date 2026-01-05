import java.util.*;

public class RemoveDuplicates {
    public static <T> List<T> removeDuplicates(List<T> list) {
        List<T> result = new ArrayList<>();
        Set<T> seen = new HashSet<>();
        
        for (T element : list) {
            if (seen.add(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 2, 2, 3, 4);
        System.out.println("Original List: " + list);
        List<Integer> result = removeDuplicates(list);
        System.out.println("After Removing Duplicates: " + result);
    }
}