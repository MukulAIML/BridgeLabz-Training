// Exercise 3: Suppress Unchecked Warnings

import java.util.ArrayList;

public class SuppressWarnings {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList list = new ArrayList();  // Raw type without generics
        
        list.add("Hello");
        list.add(100);
        list.add(45.5);
        
        System.out.println("List items: " + list);
    }
}
