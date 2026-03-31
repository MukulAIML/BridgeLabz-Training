// File: bookshelf/LibraryMain.java
package bookshelf;

import java.util.Scanner;

public class LibraryMain {
    private LibraryCatalog catalog;
    private Scanner scanner;
    
    public LibraryMain() {
        this.catalog = new LibraryCatalog();
        this.scanner = new Scanner(System.in);
    }
    
    public void showMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     LIBRARY MANAGEMENT SYSTEM         ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Add a new book");
        System.out.println("2. Remove a book");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Display books by genre");
        System.out.println("6. Display complete catalog");
        System.out.println("7. Search book by ISBN");
        System.out.println("8. Display library statistics");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
    
    public void addBook() {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        Book book = new Book(title, author, isbn);
        catalog.addBook(genre, book);
    }
    
    public void removeBook() {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine().trim();
        
        catalog.removeBook(genre, isbn);
    }
    
    public void borrowBook() {
        System.out.print("Enter ISBN of book to borrow: ");
        String isbn = scanner.nextLine().trim();
        
        catalog.borrowBook(isbn);
    }
    
    public void returnBook() {
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine().trim();
        
        catalog.returnBook(isbn);
    }
    
    public void displayGenre() {
        System.out.print("Enter genre to display: ");
        String genre = scanner.nextLine().trim();
        
        catalog.displayGenre(genre);
    }
    
    public void searchBook() {
        System.out.print("Enter ISBN to search: ");
        String isbn = scanner.nextLine().trim();
        
        catalog.searchBook(isbn);
    }
    
    public void displayStatistics() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       LIBRARY STATISTICS              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Total Genres: " + catalog.getTotalGenres());
        System.out.println("Total Books: " + catalog.getTotalBooks());
        System.out.println("========================================\n");
    }
    
    public void run() {
        System.out.println("═══════════════════════════════════════════");
        System.out.println("   Welcome to Library Management System   ");
        System.out.println("═══════════════════════════════════════════");
        
        boolean running = true;
        
        while (running) {
            showMenu();
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    displayGenre();
                    break;
                case 6:
                    catalog.displayCatalog();
                    break;
                case 7:
                    searchBook();
                    break;
                case 8:
                    displayStatistics();
                    break;
                case 9:
                    System.out.println("Thank you for using Library Management System!");
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        LibraryMain system = new LibraryMain();
        system.run();
    }
}