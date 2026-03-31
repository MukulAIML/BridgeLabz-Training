import java.util.Scanner;

class Node {
    String title, author, genre;
    int id;
    boolean avail;
    Node next, prev;
    Node(String t, String a, String g, int i, boolean av) {
        title = t; author = a; genre = g; id = i; avail = av; next = null; prev = null;
    }
}

public class LibraryManagementSystem {
    Node head, tail;
    
    void addFirst(String t, String a, String g, int i, boolean av) {
        Node n = new Node(t, a, g, i, av);
        if (head == null) head = tail = n;
        else { n.next = head; head.prev = n; head = n; }
        System.out.println("Added!");
    }
    
    void addLast(String t, String a, String g, int i, boolean av) {
        Node n = new Node(t, a, g, i, av);
        if (head == null) head = tail = n;
        else { tail.next = n; n.prev = tail; tail = n; }
        System.out.println("Added!");
    }
    
    void addPos(int p, String t, String a, String g, int i, boolean av) {
        if (p == 1) { addFirst(t, a, g, i, av); return; }
        Node n = new Node(t, a, g, i, av);
        Node temp = head;
        for (int j = 1; j < p - 1 && temp != null; j++) temp = temp.next;
        if (temp == null) { System.out.println("Invalid!"); return; }
        n.next = temp.next; n.prev = temp;
        if (temp.next != null) temp.next.prev = n; else tail = n;
        temp.next = n;
        System.out.println("Added!");
    }
    
    void remove(int i) {
        Node t = head;
        while (t != null && t.id != i) t = t.next;
        if (t == null) { System.out.println("Not found!"); return; }
        if (t.prev != null) t.prev.next = t.next; else head = t.next;
        if (t.next != null) t.next.prev = t.prev; else tail = t.prev;
        System.out.println("Removed!");
    }
    
    void searchTitle(String t) {
        Node temp = head;
        boolean f = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(t)) {
                System.out.println("ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Available: " + temp.avail);
                f = true;
            }
            temp = temp.next;
        }
        if (!f) System.out.println("Not found!");
    }
    
    void searchAuthor(String a) {
        Node t = head;
        boolean f = false;
        while (t != null) {
            if (t.author.equalsIgnoreCase(a)) {
                System.out.println("ID: " + t.id + ", Title: " + t.title + ", Author: " + t.author + ", Available: " + t.avail);
                f = true;
            }
            t = t.next;
        }
        if (!f) System.out.println("Not found!");
    }
    
    void update(int i, boolean av) {
        Node t = head;
        while (t != null) {
            if (t.id == i) { t.avail = av; System.out.println("Updated!"); return; }
            t = t.next;
        }
        System.out.println("Not found!");
    }
    
    void showForward() {
        Node t = head;
        int i = 1;
        while (t != null) {
            System.out.println(i + ". ID: " + t.id + ", Title: " + t.title + ", Author: " + t.author + ", Available: " + t.avail);
            t = t.next; i++;
        }
    }
    
    void showReverse() {
        Node t = tail;
        int i = 1;
        while (t != null) {
            System.out.println(i + ". ID: " + t.id + ", Title: " + t.title + ", Author: " + t.author + ", Available: " + t.avail);
            t = t.prev; i++;
        }
    }
    
    void count() {
        int c = 0;
        Node t = head;
        while (t != null) { c++; t = t.next; }
        System.out.println("Total: " + c);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManagementSystem lib = new LibraryManagementSystem();
        int ch;
        do {
            System.out.println("\n1.Add First 2.Add Last 3.Add Pos 4.Remove 5.Search Title 6.Search Author 7.Update 8.Show Forward 9.Show Reverse 10.Count 11.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch >= 1 && ch <= 3) {
                System.out.print("Title Author Genre ID Available(true/false): ");
                String t = sc.nextLine();
                String a = sc.nextLine();
                String g = sc.next();
                int i = sc.nextInt();
                boolean av = sc.nextBoolean();
                if (ch == 1) lib.addFirst(t, a, g, i, av);
                else if (ch == 2) lib.addLast(t, a, g, i, av);
                else { System.out.print("Pos: "); lib.addPos(sc.nextInt(), t, a, g, i, av); sc.nextLine(); }
            } else if (ch == 4) { System.out.print("ID: "); lib.remove(sc.nextInt()); }
            else if (ch == 5) { System.out.print("Title: "); lib.searchTitle(sc.nextLine()); }
            else if (ch == 6) { System.out.print("Author: "); lib.searchAuthor(sc.nextLine()); }
            else if (ch == 7) { System.out.print("ID Available: "); lib.update(sc.nextInt(), sc.nextBoolean()); }
            else if (ch == 8) lib.showForward();
            else if (ch == 9) lib.showReverse();
            else if (ch == 10) lib.count();
        } while (ch != 11);
        sc.close();
    }
}
