/**
 * HistoryNode.java
 * Represents a single node in the browsing history doubly linked list
 * Each node stores URL, title, and references to previous and next pages
 */

public class HistoryNode {
    String url;
    String title;
    HistoryNode prev;
    HistoryNode next;

    public HistoryNode(String url, String title) {
        this.url = url;
        this.title = title;
        this.prev = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return title + " (" + url + ")";
    }
}
