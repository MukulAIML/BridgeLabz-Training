import java.util.Scanner;

class Node {
    int id, pri;
    String name, date;
    Node next;
    Node(int i, String n, int p, String d) {
        id = i; name = n; pri = p; date = d; next = null;
    }
}

public class TaskScheduler {
    Node head, curr;
    
    void addFirst(int i, String n, int p, String d) {
        Node n1 = new Node(i, n, p, d);
        if (head == null) { head = n1; n1.next = head; curr = head; }
        else {
            Node last = head;
            while (last.next != head) last = last.next;
            n1.next = head; last.next = n1; head = n1;
        }
        System.out.println("Added!");
    }
    
    void addLast(int i, String n, int p, String d) {
        Node n1 = new Node(i, n, p, d);
        if (head == null) { head = n1; n1.next = head; curr = head; }
        else {
            Node last = head;
            while (last.next != head) last = last.next;
            last.next = n1; n1.next = head;
        }
        System.out.println("Added!");
    }
    
    void addPos(int p, int i, String n, int pri, String d) {
        if (p == 1) { addFirst(i, n, pri, d); return; }
        Node n1 = new Node(i, n, pri, d);
        Node t = head;
        for (int j = 1; j < p - 1 && t.next != head; j++) t = t.next;
        n1.next = t.next; t.next = n1;
        System.out.println("Added!");
    }
    
    void remove(int i) {
        if (head == null) { System.out.println("Empty!"); return; }
        Node t = head, prev = null;
        do {
            if (t.id == i) {
                if (prev == null) {
                    if (head.next == head) { head = curr = null; }
                    else {
                        Node last = head;
                        while (last.next != head) last = last.next;
                        head = head.next; last.next = head;
                        if (curr == t) curr = head;
                    }
                } else { prev.next = t.next; if (curr == t) curr = prev.next != head ? prev.next : head; }
                System.out.println("Removed!"); return;
            }
            prev = t; t = t.next;
        } while (t != head);
        System.out.println("Not found!");
    }
    
    void viewNext() {
        if (head == null) { System.out.println("Empty!"); return; }
        if (curr == null) curr = head;
        System.out.println("ID: " + curr.id + ", Name: " + curr.name + ", Priority: " + curr.pri + ", Date: " + curr.date);
        curr = curr.next;
    }
    
    void display() {
        if (head == null) { System.out.println("Empty!"); return; }
        Node t = head;
        int i = 1;
        do {
            System.out.println(i + ". ID: " + t.id + ", Name: " + t.name + ", Priority: " + t.pri + ", Date: " + t.date);
            t = t.next; i++;
        } while (t != head);
    }
    
    void search(int p) {
        Node t = head;
        boolean f = false;
        do {
            if (t.pri == p) { System.out.println("ID: " + t.id + ", Name: " + t.name + ", Date: " + t.date); f = true; }
            t = t.next;
        } while (t != head);
        if (!f) System.out.println("Not found!");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler ts = new TaskScheduler();
        int ch;
        do {
            System.out.println("\n1.Add First 2.Add Last 3.Add Pos 4.Remove 5.View Next 6.Display 7.Search 8.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch >= 1 && ch <= 3) {
                System.out.print("ID Name Priority Date: ");
                int i = sc.nextInt();
                String n = sc.next();
                int p = sc.nextInt();
                String d = sc.next();
                if (ch == 1) ts.addFirst(i, n, p, d);
                else if (ch == 2) ts.addLast(i, n, p, d);
                else { System.out.print("Pos: "); ts.addPos(sc.nextInt(), i, n, p, d); }
            } else if (ch == 4) { System.out.print("ID: "); ts.remove(sc.nextInt()); }
            else if (ch == 5) ts.viewNext();
            else if (ch == 6) ts.display();
            else if (ch == 7) { System.out.print("Priority: "); ts.search(sc.nextInt()); }
        } while (ch != 8);
        sc.close();
    }
}
