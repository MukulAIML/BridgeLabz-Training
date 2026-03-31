package M1Problems;

import java.util.*;

// base class
abstract class Festival {
    String name;
    String location;
    String date;

    Festival(String name, String location, String date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    abstract void displayDetails();
}

// music festival
class MusicFestival extends Festival {
    String headliner;
    String genre;
    int price;

    MusicFestival(String name, String location, String date,
                  String headliner, String genre, int price) {
        super(name, location, date);
        this.headliner = headliner;
        this.genre = genre;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Festival Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Headliner: " + headliner);
        System.out.println("Music Genre: " + genre);
        System.out.println("Ticket Price: " + price);
    }
}

// food festival
class FoodFestival extends Festival {
    String cuisine;
    int stalls;
    int fee;

    FoodFestival(String name, String location, String date,
                 String cuisine, int stalls, int fee) {
        super(name, location, date);
        this.cuisine = cuisine;
        this.stalls = stalls;
        this.fee = fee;
    }

    void displayDetails() {
        System.out.println("Festival Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Cuisine: " + cuisine);
        System.out.println("Number of Stalls: " + stalls);
        System.out.println("Entry Fee: " + fee);
    }
}

// art festival
class ArtFestival extends Festival {
    String artType;
    int artists;
    int fee;

    ArtFestival(String name, String location, String date,
                String artType, int artists, int fee) {
        super(name, location, date);
        this.artType = artType;
        this.artists = artists;
        this.fee = fee;
    }

    void displayDetails() {
        System.out.println("Festival Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Art Type: " + artType);
        System.out.println("Number of Artists: " + artists);
        System.out.println("Exhibition Fee: " + fee);
    }
}

// main class
public class FestivalManagementSystem {

    static Map<String, Festival> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");

            String command = parts[0];

            if (command.equals("EXIT")) {
                break;
            }

            if (command.equals("ADD_FESTIVAL")) {
                addFestival(parts);
            }

            if (command.equals("DISPLAY_DETAILS")) {
                display(parts[1]);
            }
        }
    }

    static void addFestival(String[] parts) {
        String type = parts[1];

        if (type.equals("MUSIC")) {
            Festival f = new MusicFestival(
                    parts[2], parts[3], parts[4],
                    parts[5], parts[6], Integer.parseInt(parts[7])
            );
            map.put(parts[2], f);
        }

        else if (type.equals("FOOD")) {
            Festival f = new FoodFestival(
                    parts[2], parts[3], parts[4],
                    parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[7])
            );
            map.put(parts[2], f);
        }

        else if (type.equals("ART")) {
            Festival f = new ArtFestival(
                    parts[2], parts[3], parts[4],
                    parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[7])
            );
            map.put(parts[2], f);
        }
    }

    static void display(String name) {
        if (map.containsKey(name)) {
            map.get(name).displayDetails();
        }
    }
}