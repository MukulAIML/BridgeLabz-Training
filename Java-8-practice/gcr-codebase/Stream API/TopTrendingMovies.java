import java.util.*;
import java.util.stream.*;

class Movie {
    private String title;
    private double rating;
    private int releaseYear;
    
    public Movie(String title, double rating, int releaseYear) {
        this.title = title;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }
    
    public String getTitle() { return title; }
    public double getRating() { return rating; }
    public int getReleaseYear() { return releaseYear; }
    
    @Override
    public String toString() {
        return title + " (Rating: " + rating + ", Year: " + releaseYear + ")";
    }
}

public class TopTrendingMovies {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of movies: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<Movie> movies = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nMovie " + (i + 1) + ":");
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            System.out.print("Enter rating (0-10): ");
            double rating = sc.nextDouble();
            System.out.print("Enter release year: ");
            int year = sc.nextInt();
            sc.nextLine();
            
            movies.add(new Movie(title, rating, year));
        }
        
        System.out.println("\n=== Top 5 Trending Movies ===");
        movies.stream()
            .filter(m -> m.getRating() >= 7.0)
            .sorted(Comparator.comparingDouble(Movie::getRating)
                .reversed()
                .thenComparing(Comparator.comparingInt(Movie::getReleaseYear).reversed()))
            .limit(5)
            .forEach(System.out::println);
        
        sc.close();
    }
}
