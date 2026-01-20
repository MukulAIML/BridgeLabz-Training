/**
 * BrowsingHistory.java
 * Implements doubly linked list for managing browsing history
 * Supports back/forward navigation and history display
 */

public class BrowsingHistory {
    private HistoryNode current;

    public BrowsingHistory() {
        this.current = null;
    }

    public BrowsingHistory(String url, String title) {
        this.current = new HistoryNode(url, title);
    }

    /**
     * Visit a new page - adds to history and clears forward history
     */
    public void visitPage(String url, String title) {
        HistoryNode newNode = new HistoryNode(url, title);

        if (current == null) {
            current = newNode;
        } else {
            // Remove all forward history when visiting a new page
            current.next = null;
            
            // Add new page
            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }
    }

    /**
     * Navigate to previous page in history
     */
    public boolean goBack() {
        if (current == null || current.prev == null) {
            return false;
        }
        current = current.prev;
        return true;
    }

    /**
     * Navigate to next page in history
     */
    public boolean goForward() {
        if (current == null || current.next == null) {
            return false;
        }
        current = current.next;
        return true;
    }

    /**
     * Get current page details
     */
    public String getCurrentPage() {
        if (current == null) {
            return "No page loaded";
        }
        return current.toString();
    }

    /**
     * Display complete browsing history
     */
    public void displayHistory() {
        if (current == null) {
            System.out.println("No history available");
            return;
        }

        // Go to the beginning of history
        HistoryNode temp = current;
        while (temp.prev != null) {
            temp = temp.prev;
        }

        // Display all pages
        System.out.println("\n--- Browsing History ---");
        int count = 1;
        while (temp != null) {
            if (temp == current) {
                System.out.println(count + ". " + temp + " [CURRENT]");
            } else {
                System.out.println(count + ". " + temp);
            }
            temp = temp.next;
            count++;
        }
    }

    public boolean hasPages() {
        return current != null;
    }
}
