package M1Problems;

import java.util.*;

class Version {
    String name;
    int size;

    Version(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

public class VersionControlSystem {

    static Map<String, List<Version>> storage = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");

            String operation = parts[0];

            switch (operation) {

                case "UPLOAD":
                    upload(parts[1], parts[2], Integer.parseInt(parts[3]));
                    break;

                case "FETCH":
                    fetch(parts[1]);
                    break;

                case "LATEST":
                    latest(parts[1]);
                    break;

                case "TOTAL_STORAGE":
                    totalStorage(parts[1]);
                    break;
            }
        }
    }

    //  UPLOAD
    static void upload(String fileName, String version, int size) {
        storage.putIfAbsent(fileName, new ArrayList<>());

        List<Version> versions = storage.get(fileName);

        // Check duplicate version
        for (Version v : versions) {
            if (v.name.equals(version)) {
                return; // ignore
            }
        }

        versions.add(new Version(version, size));
    }

    //  FETCH
    static void fetch(String fileName) {
        if (!storage.containsKey(fileName)) {
            System.out.println("File Not Found");
            return;
        }

        List<Version> versions = new ArrayList<>(storage.get(fileName));

        // Sort by size, then version name
        Collections.sort(versions, (a, b) -> {
            if (a.size != b.size) {
                return a.size - b.size;
            }
            return a.name.compareTo(b.name);
        });

        for (Version v : versions) {
            System.out.println(fileName + " " + v.name + " " + v.size);
        }
    }

    //  LATEST
    static void latest(String fileName) {
        if (!storage.containsKey(fileName) || storage.get(fileName).isEmpty()) {
            System.out.println("File Not Found");
            return;
        }

        List<Version> versions = storage.get(fileName);
        Version last = versions.get(versions.size() - 1);

        System.out.println(fileName + " " + last.name + " " + last.size);
    }

    //  TOTAL STORAGE
    static void totalStorage(String fileName) {
        if (!storage.containsKey(fileName)) {
            System.out.println("File Not Found");
            return;
        }

        int sum = 0;
        for (Version v : storage.get(fileName)) {
            sum += v.size;
        }

        System.out.println(fileName + " " + sum);
    }
}
