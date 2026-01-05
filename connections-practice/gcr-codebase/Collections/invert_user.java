import java.util.*;

public class InvertMap {
    public static <K, V> Map<V, List<K>> invertMap(Map<K, V> map) {
        Map<V, List<K>> invertedMap = new HashMap<>();
        
        for (Map.Entry<K, V> entry : map.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();
            
            if (!invertedMap.containsKey(value)) {
                invertedMap.put(value, new ArrayList<>());
            }
            invertedMap.get(value).add(key);
        }
        
        return invertedMap;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        
        System.out.print("Enter number of key-value pairs: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Enter key-value pairs:");
        for (int i = 0; i < n; i++) {
            System.out.print("Key: ");
            String key = sc.nextLine();
            System.out.print("Value: ");
            int value = sc.nextInt();
            sc.nextLine();
            map.put(key, value);
        }
        
        System.out.println("\nOriginal Map: " + map);
        Map<Integer, List<String>> inverted = invertMap(map);
        System.out.println("Inverted Map: " + inverted);
        
        sc.close();
    }
}