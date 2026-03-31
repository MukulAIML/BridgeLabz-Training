import java.util.Scanner;

class Node {
    String title, dir;
    int year;
    double rate;
    Node next, prev;
    Node(String t, String d, int y, double r) {
        title = t; dir = d; year = y; rate = r; next = null; prev = null;
    }
}

public class MovieManagementSystem {
    Node head, tail;
    
    void addFirst(String t, String d, int y, double r) {
        Node n = new Node(t, d, y, r);
        if (head == null) head = tail = n;
        else { n.next = head; head.prev = n; head = n; }
        System.out.println("Added!");
    }
    
    void addLast(String t, String d, int y, double r) {
        Node n = new Node(t, d, y, r);
        if (head == null) head = tail = n;
        else { tail.next = n; n.prev = tail; tail = n; }
        System.out.println("Added!");
    }
    
    void addPos(int p, String t, String d, int y, double r) {
        if (p == 1) { addFirst(t, d, y, r); return; }
        Node n = new Node(t, d, y, r);
        Node temp = head;
        for (int i = 1; i < p - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) { System.out.println("Invalid!"); return; }
        n.next = temp.next; n.prev = temp;
        if (temp.next != null) temp.next.prev = n; else tail = n;
        temp.next = n;
        System.out.println("Added!");
    }
    
    void remove(String t) {
        Node temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(t)) temp = temp.next;
        if (temp == null) { System.out.println("Not found!"); return; }
        if (temp.prev != null) temp.prev.next = temp.next; else head = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev; else tail = temp.prev;
        System.out.println("Removed!");
    }
    
    void searchDir(String d) {
        Node t = head;
        boolean f = false;
        while (t != null) {
            if (t.dir.equalsIgnoreCase(d)) {
                System.out.println(t.title + " (" + t.year + ") - " + t.rate);
                f = true;
            }
            t = t.next;
        }
        if (!f) System.out.println("Not found!");
    }
    
    void searchRate(double r) {
        Node t = head;
        boolean f = false;
        while (t != null) {
            if (t.rate == r) {
                System.out.println(t.title + " - " + t.dir + " (" + t.year + ")");
                f = true;
            }
            t = t.next;
        }
        if (!f) System.out.println("Not found!");
    }
    
    void showForward() {
        Node t = head;
        int i = 1;
        while (t != null) {
            System.out.println(i + ". " + t.title + " - " + t.dir + " (" + t.year + ") - " + t.rate);
            t = t.next; i++;
        }
    }
    
    void showReverse() {
        Node t = tail;
        int i = 1;
        while (t != null) {
            System.out.println(i + ". " + t.title + " - " + t.dir + " (" + t.year + ") - " + t.rate);
            t = t.prev; i++;
        }
    }
    
    void update(String t, double r) {
        Node temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(t)) { temp.rate = r; System.out.println("Updated!"); return; }
            temp = temp.next;
        }
        System.out.println("Not found!");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieManagementSystem m = new MovieManagementSystem();
        int ch;
        do {
            System.out.println("\n1.Add First 2.Add Last 3.Add Pos 4.Remove 5.Search Dir 6.Search Rate 7.Show Forward 8.Show Reverse 9.Update 10.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch >= 1 && ch <= 3) {
                System.out.print("Title Director Year Rating: ");
                String t = sc.nextLine();
                String d = sc.nextLine();
                int y = sc.nextInt();
                double r = sc.nextDouble();
                if (ch == 1) m.addFirst(t, d, y, r);
                else if (ch == 2) m.addLast(t, d, y, r);
                else { System.out.print("Pos: "); m.addPos(sc.nextInt(), t, d, y, r); sc.nextLine(); }
            } else if (ch == 4) { System.out.print("Title: "); m.remove(sc.nextLine()); }
            else if (ch == 5) { System.out.print("Director: "); m.searchDir(sc.nextLine()); }
            else if (ch == 6) { System.out.print("Rating: "); m.searchRate(sc.nextDouble()); }
            else if (ch == 7) m.showForward();
            else if (ch == 8) m.showReverse();
            else if (ch == 9) { System.out.print("Title NewRating: "); m.update(sc.nextLine(), sc.nextDouble()); }
        } while (ch != 10);
        sc.close();
    }
}
