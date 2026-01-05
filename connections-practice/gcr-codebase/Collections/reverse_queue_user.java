import java.util.*;

public class ReverseQueue {
    public static <T> void reverseQueue(Queue<T> queue) {
        Stack<T> stack = new Stack<>();
        
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        
        Queue<Integer> queue = new LinkedList<>();
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }
        
        System.out.println("Original Queue: " + queue);
        reverseQueue(queue);
        System.out.println("Reversed Queue: " + queue);
        
        sc.close();
    }
}