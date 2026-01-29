import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String genre;
    private int pages;
    
    public Book(String title, String genre, int pages) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public int getPages() {
        return pages;
    }
    
    @Override
    public String toString() {
        return title + " (" + genre + ", " + pages + " pages)";
    }
}

public class LibraryBookStatistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        
        System.out.print("Enter number of books: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter number of pages: ");
            int pages = scanner.nextInt();
            scanner.nextLine();
            books.add(new Book(title, genre, pages));
        }
        
        Map<String, IntSummaryStatistics> statsByGenre = books.stream()
            .collect(Collectors.groupingBy(
                Book::getGenre,
                Collectors.summarizingInt(Book::getPages)
            ));
        
        System.out.println("\nBook Statistics by Genre:");
        statsByGenre.forEach((genre, stats) -> {
            System.out.println("\nGenre: " + genre);
            System.out.println("  Total Pages: " + stats.getSum());
            System.out.printf("  Average Pages: %.2f%n", stats.getAverage());
            System.out.println("  Maximum Pages: " + stats.getMax());
            System.out.println("  Minimum Pages: " + stats.getMin());
            System.out.println("  Book Count: " + stats.getCount());
        });
        
        scanner.close();
    }
}
