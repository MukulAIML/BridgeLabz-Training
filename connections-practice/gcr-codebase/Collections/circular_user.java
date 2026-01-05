import java.util.*;

public class CircularBuffer {
    private int[] buffer;
    private int size;
    private int head = 0;
    private int count = 0;

    public CircularBuffer(int size) {
        this.size = size;
        this.buffer = new int[size];
    }

    public void insert(int value) {
        buffer[head] = value;
        head = (head + 1) % size;
        if (count < size) {
            count++;
        }
    }

    public void display() {
        System.out.print("Buffer: [");
        int start = count < size ? 0 : head;
        for (int i = 0; i < count; i++) {
            System.out.print(buffer[(start + i) % size]);
            if (i < count - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter buffer size: ");
        int size = sc.nextInt();
        CircularBuffer buffer = new CircularBuffer(size);
        
        System.out.print("How many elements to insert? ");
        int n = sc.nextInt();
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            buffer.insert(val);
            System.out.print("After inserting " + val + " - ");
            buffer.display();
        }
        
        sc.close();
    }
}