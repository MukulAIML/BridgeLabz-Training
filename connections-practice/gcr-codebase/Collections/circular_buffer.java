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
        CircularBuffer buffer = new CircularBuffer(3);
        buffer.insert(1);
        buffer.insert(2);
        buffer.insert(3);
        buffer.display();
        
        buffer.insert(4);
        buffer.display();
    }
}