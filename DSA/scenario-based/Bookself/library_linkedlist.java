// File: bookshelf/BookLinkedList.java
package bookshelf;

public class BookLinkedList {
    private BookNode head;
    private int size;
    
    // Inner class for LinkedList Node
    private class BookNode {
        Book book;
        BookNode next;
        
        BookNode(Book book) {
            this.book = book;
            this.next = null;
        }
    }
    
    public BookLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Add book to the list
    public boolean addBook(Book book) {
        // Check for duplicate ISBN
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.println("Book with ISBN " + book.getIsbn() + " already exists!");
            return false;
        }
        
        BookNode newNode = new BookNode(book);
        
        if (head == null) {
            head = newNode;
        } else {
            BookNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        
        size++;
        System.out.println("Book added successfully: " + book.getTitle());
        return true;
    }
    
    // Remove book by ISBN
    public boolean removeBook(String isbn) {
        if (head == null) {
            System.out.println("No books in this genre!");
            return false;
        }
        
        // If head needs to be removed
        if (head.book.getIsbn().equals(isbn)) {
            System.out.println("Book removed: " + head.book.getTitle());
            head = head.next;
            size--;
            return true;
        }
        
        // Search for the book
        BookNode current = head;
        BookNode previous = null;
        
        while (current != null && !current.book.getIsbn().equals(isbn)) {
            previous = current;
            current = current.next;
        }
        
        if (current == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }
        
        previous.next = current.next;
        System.out.println("Book removed: " + current.book.getTitle());
        size--;
        return true;
    }
    
    // Find book by ISBN
    public Book findBookByIsbn(String isbn) {
        BookNode current = head;
        
        while (current != null) {
            if (current.book.getIsbn().equals(isbn)) {
                return current.book;
            }
            current = current.next;
        }
        
        return null;
    }
    
    // Borrow book
    public boolean borrowBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed!");
            return false;
        }
        
        book.setAvailable(false);
        System.out.println("Book borrowed: " + book.getTitle());
        return true;
    }
    
    // Return book
    public boolean returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        
        if (book.isAvailable()) {
            System.out.println("Book was not borrowed!");
            return false;
        }
        
        book.setAvailable(true);
        System.out.println("Book returned: " + book.getTitle());
        return true;
    }
    
    // Display all books
    public void displayBooks() {
        if (head == null) {
            System.out.println("  No books in this genre.");
            return;
        }
        
        BookNode current = head;
        int count = 1;
        
        while (current != null) {
            System.out.println("  " + count + ". " + current.book.toString());
            current = current.next;
            count++;
        }
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
}