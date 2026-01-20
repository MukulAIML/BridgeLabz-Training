// File: bookshelf/LibraryCatalog.java
package bookshelf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LibraryCatalog {
    private HashMap<String, BookLinkedList> catalog;
    private HashSet<String> isbnSet; // To avoid duplication across all genres
    
    public LibraryCatalog() {
        this.catalog = new HashMap<>();
        this.isbnSet = new HashSet<>();
    }
    
    // Add a book to specific genre
    public boolean addBook(String genre, Book book) {
        // Check for duplicate ISBN across entire library
        if (isbnSet.contains(book.getIsbn())) {
            System.out.println("ERROR: Book with ISBN " + book.getIsbn() + 
                             " already exists in the library!");
            return false;
        }
        
        // Get or create genre list
        BookLinkedList genreList = catalog.get(genre);
        
        if (genreList == null) {
            genreList = new BookLinkedList();
            catalog.put(genre, genreList);
            System.out.println("New genre created: " + genre);
        }
        
        // Add book to the genre list
        boolean added = genreList.addBook(book);
        
        if (added) {
            isbnSet.add(book.getIsbn());
        }
        
        return added;
    }
    
    // Remove book from catalog
    public boolean removeBook(String genre, String isbn) {
        BookLinkedList genreList = catalog.get(genre);
        
        if (genreList == null) {
            System.out.println("Genre '" + genre + "' not found!");
            return false;
        }
        
        boolean removed = genreList.removeBook(isbn);
        
        if (removed) {
            isbnSet.remove(isbn);
            
            // Remove genre if empty
            if (genreList.isEmpty()) {
                catalog.remove(genre);
                System.out.println("Genre '" + genre + "' removed (no books left).");
            }
        }
        
        return removed;
    }
    
    // Borrow a book
    public boolean borrowBook(String isbn) {
        for (Map.Entry<String, BookLinkedList> entry : catalog.entrySet()) {
            Book book = entry.getValue().findBookByIsbn(isbn);
            if (book != null) {
                return entry.getValue().borrowBook(isbn);
            }
        }
        
        System.out.println("Book with ISBN " + isbn + " not found in library!");
        return false;
    }
    
    // Return a book
    public boolean returnBook(String isbn) {
        for (Map.Entry<String, BookLinkedList> entry : catalog.entrySet()) {
            Book book = entry.getValue().findBookByIsbn(isbn);
            if (book != null) {
                return entry.getValue().returnBook(isbn);
            }
        }
        
        System.out.println("Book with ISBN " + isbn + " not found in library!");
        return false;
    }
    
    // Display all books by genre
    public void displayGenre(String genre) {
        BookLinkedList genreList = catalog.get(genre);
        
        if (genreList == null) {
            System.out.println("Genre '" + genre + "' not found!");
            return;
        }
        
        System.out.println("\n===== Genre: " + genre + " =====");
        System.out.println("Total books: " + genreList.getSize());
        genreList.displayBooks();
        System.out.println("================================\n");
    }
    
    // Display entire catalog
    public void displayCatalog() {
        if (catalog.isEmpty()) {
            System.out.println("Library catalog is empty!");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       COMPLETE LIBRARY CATALOG        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Total genres: " + catalog.size());
        System.out.println("Total unique books: " + isbnSet.size());
        System.out.println();
        
        for (Map.Entry<String, BookLinkedList> entry : catalog.entrySet()) {
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("Genre: " + entry.getKey() + 
                             " (" + entry.getValue().getSize() + " books)");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            entry.getValue().displayBooks();
            System.out.println();
        }
    }
    
    // Search book by ISBN across all genres
    public void searchBook(String isbn) {
        for (Map.Entry<String, BookLinkedList> entry : catalog.entrySet()) {
            Book book = entry.getValue().findBookByIsbn(isbn);
            if (book != null) {
                System.out.println("\nBook found in genre: " + entry.getKey());
                System.out.println(book.toString());
                return;
            }
        }
        
        System.out.println("Book with ISBN " + isbn + " not found!");
    }
    
    public int getTotalGenres() {
        return catalog.size();
    }
    
    public int getTotalBooks() {
        return isbnSet.size();
    }
}