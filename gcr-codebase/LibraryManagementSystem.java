import java.util.*;

// ==================== OBSERVER PATTERN ====================
// Interface for observers who will receive notifications
interface Observer {
    void update(String message);
}

// ==================== FACTORY PATTERN ====================
// User interface that extends Observer for notifications
interface User extends Observer {
    void showRole();
    String getName();
}

// Student user implementation
class Student implements User {
    private String name;
    
    public Student(String name) {
        this.name = name;
    }
    
    @Override
    public void showRole() {
        System.out.println(name + " is a Student.");
    }
    
    @Override
    public void update(String message) {
        System.out.println("[STUDENT] " + name + " notified: " + message);
    }
    
    @Override
    public String getName() {
        return name;
    }
}

// Faculty user implementation
class Faculty implements User {
    private String name;
    
    public Faculty(String name) {
        this.name = name;
    }
    
    @Override
    public void showRole() {
        System.out.println(name + " is a Faculty member.");
    }
    
    @Override
    public void update(String message) {
        System.out.println("[FACULTY] " + name + " notified: " + message);
    }
    
    @Override
    public String getName() {
        return name;
    }
}

// Librarian user implementation
class Librarian implements User {
    private String name;
    
    public Librarian(String name) {
        this.name = name;
    }
    
    @Override
    public void showRole() {
        System.out.println(name + " is a Librarian.");
    }
    
    @Override
    public void update(String message) {
        System.out.println("[LIBRARIAN] " + name + " notified: " + message);
    }
    
    @Override
    public String getName() {
        return name;
    }
}

// Factory class to create different types of users
class UserFactory {
    public static User createUser(String type, String name) {
        switch (type.toLowerCase()) {
            case "student":
                return new Student(name);
            case "faculty":
                return new Faculty(name);
            case "librarian":
                return new Librarian(name);
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}

// ==================== BUILDER PATTERN ====================
// Book class with optional attributes
class Book {
    private String title;          // Mandatory
    private String author;         // Optional
    private String edition;        // Optional
    private String genre;          // Optional
    
    // Private constructor - only Builder can create Book
    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.edition = builder.edition;
        this.genre = builder.genre;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book: ").append(title);
        if (author != null) sb.append(" by ").append(author);
        if (edition != null) sb.append(" (").append(edition).append(" edition)");
        if (genre != null) sb.append(" [Genre: ").append(genre).append("]");
        return sb.toString();
    }
    
    // Static inner Builder class
    public static class BookBuilder {
        private String title;      // Mandatory
        private String author;
        private String edition;
        private String genre;
        
        // Constructor with mandatory field
        public BookBuilder(String title) {
            this.title = title;
        }
        
        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }
        
        public BookBuilder edition(String edition) {
            this.edition = edition;
            return this;
        }
        
        public BookBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }
        
        public Book build() {
            return new Book(this);
        }
    }
}

// ==================== SINGLETON PATTERN ====================
// Library Catalog - Only one instance exists
class LibraryCatalog {
    private static LibraryCatalog instance;
    private List<Book> books;
    private List<Observer> observers;
    
    // Private constructor prevents direct instantiation
    private LibraryCatalog() {
        books = new ArrayList<>();
        observers = new ArrayList<>();
        System.out.println("==> Library Catalog initialized (Singleton)");
    }
    
    // Thread-safe singleton instance retrieval
    public static synchronized LibraryCatalog getInstance() {
        if (instance == null) {
            instance = new LibraryCatalog();
        }
        return instance;
    }
    
    // Add book to catalog and notify observers
    public void addBook(Book book) {
        books.add(book);
        System.out.println("\n+ Book added to catalog: " + book);
        notifyObservers("New book available: " + book.getTitle());
    }
    
    // Observer pattern methods
    public void addObserver(Observer observer) {
        observers.add(observer);
        if (observer instanceof User) {
            System.out.println("+ " + ((User) observer).getName() + " subscribed to notifications");
        }
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        if (observer instanceof User) {
            System.out.println("- " + ((User) observer).getName() + " unsubscribed from notifications");
        }
    }
    
    private void notifyObservers(String message) {
        System.out.println("\n--- Sending Notifications ---");
        for (Observer observer : observers) {
            observer.update(message);
        }
        System.out.println("-----------------------------\n");
    }
    
    public void displayCatalog() {
        System.out.println("\n========== LIBRARY CATALOG ==========");
        if (books.isEmpty()) {
            System.out.println("No books in catalog yet.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
        System.out.println("=====================================\n");
    }
}

// ==================== MAIN APPLICATION ====================
public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("  LIBRARY MANAGEMENT SYSTEM (LMS)  ");
        System.out.println("  Using GoF Design Patterns        ");
        System.out.println("====================================\n");
        
        // SINGLETON: Get the single instance of library catalog
        System.out.println("1. SINGLETON PATTERN - Creating Library Catalog");
        LibraryCatalog catalog = LibraryCatalog.getInstance();
        
        // Verify singleton - getting instance again returns same object
        LibraryCatalog catalog2 = LibraryCatalog.getInstance();
        System.out.println("Same instance? " + (catalog == catalog2) + "\n");
        
        // FACTORY: Create different types of users
        System.out.println("2. FACTORY PATTERN - Creating Users");
        User alice = UserFactory.createUser("student", "Alice");
        User bob = UserFactory.createUser("faculty", "Dr. Bob");
        User charlie = UserFactory.createUser("librarian", "Charlie");
        
        alice.showRole();
        bob.showRole();
        charlie.showRole();
        System.out.println();
        
        // OBSERVER: Subscribe users to notifications
        System.out.println("3. OBSERVER PATTERN - Subscribing Users");
        catalog.addObserver(alice);
        catalog.addObserver(bob);
        System.out.println();
        
        // BUILDER: Create books with optional attributes
        System.out.println("4. BUILDER PATTERN - Creating Books");
        
        // Book with all attributes
        Book book1 = new Book.BookBuilder("Design Patterns")
                .author("Gang of Four")
                .edition("2nd")
                .genre("Software Engineering")
                .build();
        
        // Book with only mandatory and some optional attributes
        Book book2 = new Book.BookBuilder("Clean Code")
                .author("Robert C. Martin")
                .genre("Programming")
                .build();
        
        // Book with only title (mandatory)
        Book book3 = new Book.BookBuilder("Data Structures and Algorithms")
                .build();
        
        // Add books to catalog - triggers notifications
        catalog.addBook(book1);
        catalog.addBook(book2);
        
        // Subscribe librarian after first two books
        catalog.addObserver(charlie);
        
        catalog.addBook(book3);
        
        // Display complete catalog
        catalog.displayCatalog();
        
        // Unsubscribe a user
        System.out.println("5. Unsubscribing User");
        catalog.removeObserver(alice);
        
        // Add another book - only remaining subscribers get notified
        Book book4 = new Book.BookBuilder("Introduction to Algorithms")
                .author("CLRS")
                .edition("4th")
                .genre("Computer Science")
                .build();
        
        catalog.addBook(book4);
        
        // Final catalog view
        catalog.displayCatalog();
        
        System.out.println("====================================");
        System.out.println("  All Design Patterns Demonstrated! ");
        System.out.println("  ✓ Singleton  ✓ Factory           ");
        System.out.println("  ✓ Observer   ✓ Builder           ");
        System.out.println("====================================");
    }
}
