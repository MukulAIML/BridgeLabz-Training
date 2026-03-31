import java.util.Scanner;

class Node {
    String name;
    int id, qty;
    double price;
    Node next;
    Node(String n, int i, int q, double p) {
        name = n; id = i; qty = q; price = p; next = null;
    }
}

public class InventoryManagementSystem {
    Node head;
    
    void addFirst(String n, int i, int q, double p) {
        Node n1 = new Node(n, i, q, p);
        n1.next = head; head = n1;
        System.out.println("Added!");
    }
    
    void addLast(String n, int i, int q, double p) {
        Node n1 = new Node(n, i, q, p);
        if (head == null) head = n1;
        else {
            Node t = head;
            while (t.next != null) t = t.next;
            t.next = n1;
        }
        System.out.println("Added!");
    }
    
    void addPos(int p, String n, int i, int q, double pr) {
        if (p == 1) { addFirst(n, i, q, pr); return; }
        Node n1 = new Node(n, i, q, pr);
        Node t = head;
        for (int j = 1; j < p - 1 && t != null; j++) t = t.next;
        if (t == null) { System.out.println("Invalid!"); return; }
        n1.next = t.next; t.next = n1;
        System.out.println("Added!");
    }
    
    void remove(int i) {
        if (head == null) { System.out.println("Empty!"); return; }
        if (head.id == i) { head = head.next; System.out.println("Removed!"); return; }
        Node t = head;
        while (t.next != null && t.next.id != i) t = t.next;
        if (t.next == null) { System.out.println("Not found!"); return; }
        t.next = t.next.next;
        System.out.println("Removed!");
    }
    
    void updateQty(int i, int q) {
        Node t = head;
        while (t != null) {
            if (t.id == i) { t.qty = q; System.out.println("Updated!"); return; }
            t = t.next;
        }
        System.out.println("Not found!");
    }
    
    void searchId(int i) {
        Node t = head;
        while (t != null) {
            if (t.id == i) {
                System.out.println("ID: " + t.id + ", Name: " + t.name + ", Qty: " + t.qty + ", Price: " + t.price);
                return;
            }
            t = t.next;
        }
        System.out.println("Not found!");
    }
    
    void searchName(String n) {
        Node t = head;
        boolean f = false;
        while (t != null) {
            if (t.name.equalsIgnoreCase(n)) {
                System.out.println("ID: " + t.id + ", Name: " + t.name + ", Qty: " + t.qty + ", Price: " + t.price);
                f = true;
            }
            t = t.next;
        }
        if (!f) System.out.println("Not found!");
    }
    
    void totalValue() {
        double total = 0;
        Node t = head;
        while (t != null) { total += t.price * t.qty; t = t.next; }
        System.out.println("Total: $" + String.format("%.2f", total));
    }
    
    void sortName(boolean asc) {
        Node t1 = head;
        while (t1 != null) {
            Node t2 = t1.next;
            while (t2 != null) {
                if ((asc && t1.name.compareToIgnoreCase(t2.name) > 0) || (!asc && t1.name.compareToIgnoreCase(t2.name) < 0)) {
                    String n = t1.name; t1.name = t2.name; t2.name = n;
                    int i = t1.id; t1.id = t2.id; t2.id = i;
                    int q = t1.qty; t1.qty = t2.qty; t2.qty = q;
                    double p = t1.price; t1.price = t2.price; t2.price = p;
                }
                t2 = t2.next;
            }
            t1 = t1.next;
        }
        System.out.println("Sorted!");
    }
    
    void sortPrice(boolean asc) {
        Node t1 = head;
        while (t1 != null) {
            Node t2 = t1.next;
            while (t2 != null) {
                if ((asc && t1.price > t2.price) || (!asc && t1.price < t2.price)) {
                    String n = t1.name; t1.name = t2.name; t2.name = n;
                    int i = t1.id; t1.id = t2.id; t2.id = i;
                    int q = t1.qty; t1.qty = t2.qty; t2.qty = q;
                    double p = t1.price; t1.price = t2.price; t2.price = p;
                }
                t2 = t2.next;
            }
            t1 = t1.next;
        }
        System.out.println("Sorted!");
    }
    
    void display() {
        Node t = head;
        int i = 1;
        while (t != null) {
            System.out.println(i + ". ID: " + t.id + ", Name: " + t.name + ", Qty: " + t.qty + ", Price: $" + t.price);
            t = t.next; i++;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManagementSystem inv = new InventoryManagementSystem();
        int ch;
        do {
            System.out.println("\n1.Add First 2.Add Last 3.Add Pos 4.Remove 5.Update Qty 6.Search ID 7.Search Name 8.Total 9.Sort Name Asc 10.Sort Name Desc 11.Sort Price Asc 12.Sort Price Desc 13.Display 14.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch >= 1 && ch <= 3) {
                System.out.print("Name ID Qty Price: ");
                String n = sc.next();
                int i = sc.nextInt();
                int q = sc.nextInt();
                double p = sc.nextDouble();
                if (ch == 1) inv.addFirst(n, i, q, p);
                else if (ch == 2) inv.addLast(n, i, q, p);
                else { System.out.print("Pos: "); inv.addPos(sc.nextInt(), n, i, q, p); }
            } else if (ch == 4) { System.out.print("ID: "); inv.remove(sc.nextInt()); }
            else if (ch == 5) { System.out.print("ID Qty: "); inv.updateQty(sc.nextInt(), sc.nextInt()); }
            else if (ch == 6) { System.out.print("ID: "); inv.searchId(sc.nextInt()); }
            else if (ch == 7) { System.out.print("Name: "); inv.searchName(sc.next()); }
            else if (ch == 8) inv.totalValue();
            else if (ch == 9) inv.sortName(true);
            else if (ch == 10) inv.sortName(false);
            else if (ch == 11) inv.sortPrice(true);
            else if (ch == 12) inv.sortPrice(false);
            else if (ch == 13) inv.display();
        } while (ch != 14);
        sc.close();
    }
}
