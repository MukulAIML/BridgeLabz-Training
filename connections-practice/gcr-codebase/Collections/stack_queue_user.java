import java.util.*;

public class StackUsingQueues {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(int x) {
        queue2.add(x);
        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        if (queue1.isEmpty()) {
            throw new EmptyStackException();
        }
        return queue1.remove();
    }

    public int top() {
        if (queue1.isEmpty()) {
            throw new EmptyStackException();
        }
        return queue1.peek();
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StackUsingQueues stack = new StackUsingQueues();
        
        System.out.print("Enter number of elements to push: ");
        int n = sc.nextInt();
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            stack.push(val);
            System.out.println("Pushed: " + val);
        }
        
        System.out.println("\nTop element: " + stack.top());
        
        System.out.print("How many elements to pop? ");
        int popCount = sc.nextInt();
        
        System.out.println("\nPopping elements:");
        for (int i = 0; i < popCount && !stack.isEmpty(); i++) {
            System.out.println("Popped: " + stack.pop());
        }
        
        if (!stack.isEmpty()) {
            System.out.println("\nNew top element: " + stack.top());
        } else {
            System.out.println("\nStack is now empty.");
        }
        
        sc.close();
    }
}