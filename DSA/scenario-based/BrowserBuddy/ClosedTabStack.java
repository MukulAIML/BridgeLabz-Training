/**
 * ClosedTabStack.java
 * Stack implementation for managing closed tabs
 * Uses linked nodes for memory-efficient LIFO operations
 */

public class ClosedTabStack {
    private StackNode top;
    private int size;

    /**
     * Inner class representing a node in the stack
     */
    private class StackNode {
        Tab tab;
        StackNode next;

        StackNode(Tab tab) {
            this.tab = tab;
            this.next = null;
        }
    }

    public ClosedTabStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Push a closed tab onto the stack
     */
    public void push(Tab tab) {
        StackNode newNode = new StackNode(tab);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Pop the most recently closed tab from the stack
     */
    public Tab pop() {
        if (isEmpty()) {
            return null;
        }
        Tab tab = top.tab;
        top = top.next;
        size--;
        return tab;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    /**
     * Display all closed tabs in the stack
     */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No closed tabs available");
            return;
        }

        System.out.println("\n--- Recently Closed Tabs ---");
        StackNode temp = top;
        int count = 1;
        while (temp != null) {
            System.out.println(count + ". " + temp.tab);
            temp = temp.next;
            count++;
        }
    }
}
