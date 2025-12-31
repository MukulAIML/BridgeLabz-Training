import java.util.Scanner;

class Node {
    int roll, age;
    String name;
    double grade;
    Node next;
    Node(int r, String n, int a, double g) {
        roll = r; name = n; age = a; grade = g; next = null;
    }
}

public class StudentRecordManager {
    Node head;
    
    void addFirst(int r, String n, int a, double g) {
        Node n1 = new Node(r, n, a, g);
        n1.next = head;
        head = n1;
        System.out.println("Added!");
    }
    
    void addLast(int r, String n, int a, double g) {
        Node n1 = new Node(r, n, a, g);
        if (head == null) head = n1;
        else {
            Node t = head;
            while (t.next != null) t = t.next;
            t.next = n1;
        }
        System.out.println("Added!");
    }
    
    void addPos(int p, int r, String n, int a, double g) {
        if (p == 1) { addFirst(r, n, a, g); return; }
        Node n1 = new Node(r, n, a, g);
        Node t = head;
        for (int i = 1; i < p - 1 && t != null; i++) t = t.next;
        if (t == null) { System.out.println("Invalid!"); return; }
        n1.next = t.next;
        t.next = n1;
        System.out.println("Added!");
    }
    
    void delete(int r) {
        if (head == null) { System.out.println("Empty!"); return; }
        if (head.roll == r) { head = head.next; System.out.println("Deleted!"); return; }
        Node t = head;
        while (t.next != null && t.next.roll != r) t = t.next;
        if (t.next == null) { System.out.println("Not found!"); return; }
        t.next = t.next.next;
        System.out.println("Deleted!");
    }
    
    void search(int r) {
        Node t = head;
        while (t != null) {
            if (t.roll == r) {
                System.out.println("Roll: " + t.roll + ", Name: " + t.name + ", Age: " + t.age + ", Grade: " + t.grade);
                return;
            }
            t = t.next;
        }
        System.out.println("Not found!");
    }
    
    void display() {
        if (head == null) { System.out.println("Empty!"); return; }
        Node t = head;
        int i = 1;
        while (t != null) {
            System.out.println(i + ". Roll: " + t.roll + ", Name: " + t.name + ", Age: " + t.age + ", Grade: " + t.grade);
            t = t.next; i++;
        }
    }
    
    void update(int r, double g) {
        Node t = head;
        while (t != null) {
            if (t.roll == r) { t.grade = g; System.out.println("Updated!"); return; }
            t = t.next;
        }
        System.out.println("Not found!");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentRecordManager s = new StudentRecordManager();
        int ch;
        do {
            System.out.println("\n1.Add First 2.Add Last 3.Add Pos 4.Delete 5.Search 6.Display 7.Update 8.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            if (ch == 1 || ch == 2 || ch == 3) {
                System.out.print("Roll Name Age Grade: ");
                int r = sc.nextInt();
                String n = sc.next();
                int a = sc.nextInt();
                double g = sc.nextDouble();
                if (ch == 1) s.addFirst(r, n, a, g);
                else if (ch == 2) s.addLast(r, n, a, g);
                else { System.out.print("Pos: "); s.addPos(sc.nextInt(), r, n, a, g); }
            } else if (ch == 4) { System.out.print("Roll: "); s.delete(sc.nextInt()); }
            else if (ch == 5) { System.out.print("Roll: "); s.search(sc.nextInt()); }
            else if (ch == 6) s.display();
            else if (ch == 7) { System.out.print("Roll NewGrade: "); s.update(sc.nextInt(), sc.nextDouble()); }
        } while (ch != 8);
        sc.close();
    }
}
