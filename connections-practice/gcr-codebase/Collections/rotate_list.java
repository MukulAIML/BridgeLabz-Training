import java.util.*;

public class RotateList {
    public static <T> void rotateList(List<T> list, int positions) {
        if (list.isEmpty()) return;
        positions = positions % list.size();
        if (positions == 0) return;
        
        List<T> rotated = new ArrayList<>();
        for (int i = positions; i < list.size(); i++) {
            rotated.add(list.get(i));
        }
        for (int i = 0; i < positions; i++) {
            rotated.add(list.get(i));
        }
        
        list.clear();
        list.addAll(rotated);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original List: " + list);
        rotateList(list, 2);
        System.out.println("Rotated by 2: " + list);
    }
}