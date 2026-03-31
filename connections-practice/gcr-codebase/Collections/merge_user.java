import java.util.*;

public class MergeMaps {
    public static Map<String, Integer> mergeMaps(Map<String, Integer> map1, 
                                                  Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>(map1);
        
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            result.put(entry.getKey(), 
                      result.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Map<String, Integer> map1 = new HashMap<>();
        System.out.print("Enter number of entries for Map1: ");
        int n1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Map1 entries:");
        for (int i = 0; i < n1; i++) {
            System.out.print("Key: ");
            String key = sc.nextLine();
            System.out.print("Value: ");
            int value = sc.nextInt();
            sc.nextLine();
            map1.put(key, value);
        }
        
        Map<String, Integer> map2 = new HashMap<>();
        System.out.print("\nEnter number of entries for Map2: ");
        int n2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Map2 entries:");
        for (int i = 0; i < n2; i++) {
            System.out.print("Key: ");
            String key = sc.nextLine();
            System.out.print("Value: ");
            int value = sc.nextInt();
            sc.nextLine();
            map2.put(key, value);
        }
        
        System.out.println("\nMap1: " + map1);
        System.out.println("Map2: " + map2);
        System.out.println("Merged Map: " + mergeMaps(map1, map2));
        
        sc.close();
    }
}