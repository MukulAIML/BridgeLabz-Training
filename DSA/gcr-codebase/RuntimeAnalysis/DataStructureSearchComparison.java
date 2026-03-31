import java.util.*;

public class DataStructureSearchComparison {
    
    public static boolean arraySearch(int[] arr, int target) {
        for (int value : arr) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] sizes = {1000, 100000, 1000000};
        
        for (int size : sizes) {
            System.out.println("Dataset Size: " + size);
            
            // Prepare data
            int[] array = new int[size];
            HashSet<Integer> hashSet = new HashSet<>();
            TreeSet<Integer> treeSet = new TreeSet<>();
            
            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                int value = rand.nextInt(1000000);
                array[i] = value;
                hashSet.add(value);
                treeSet.add(value);
            }
            
            int target = array[rand.nextInt(size)];
            
            // Array search
            long start = System.nanoTime();
            arraySearch(array, target);
            long time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Array Search: " + time + "ms");
            
            // HashSet search
            start = System.nanoTime();
            hashSet.contains(target);
            time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("HashSet Search: " + time + "ms");
            
            // TreeSet search
            start = System.nanoTime();
            treeSet.contains(target);
            time = (System.nanoTime() - start) / 1_000_000;
            System.out.println("TreeSet Search: " + time + "ms");
            System.out.println();
        }
    }
}

