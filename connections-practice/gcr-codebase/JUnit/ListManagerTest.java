import java.util.List;

public class ListManager {
    
    public void addElement(List<Integer> list, int element) {
        if (list != null) {
            list.add(element);
        }
    }
    
    public boolean removeElement(List<Integer> list, int element) {
        if (list != null) {
            return list.remove(Integer.valueOf(element));
        }
        return false;
    }
    
    public int getSize(List<Integer> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}

// ListManagerTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class ListManagerTest {
    
    private ListManager manager;
    private List<Integer> list;
    
    @BeforeEach
    void setUp() {
        manager = new ListManager();
        list = new ArrayList<>();
    }
    
    @Test
    void testAddElement() {
        manager.addElement(list, 10);
        manager.addElement(list, 20);
        manager.addElement(list, 30);
        
        assertEquals(3, list.size());
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertTrue(list.contains(30));
    }
    
    @Test
    void testRemoveElement() {
        list.add(5);
        list.add(15);
        list.add(25);
        
        assertTrue(manager.removeElement(list, 15));
        assertEquals(2, list.size());
        assertFalse(list.contains(15));
        
        assertFalse(manager.removeElement(list, 100));
    }
    
    @Test
    void testGetSize() {
        assertEquals(0, manager.getSize(list));
        
        list.add(1);
        list.add(2);
        list.add(3);
        
        assertEquals(3, manager.getSize(list));
    }
    
    @Test
    void testSizeAfterOperations() {
        manager.addElement(list, 10);
        manager.addElement(list, 20);
        assertEquals(2, manager.getSize(list));
        
        manager.removeElement(list, 10);
        assertEquals(1, manager.getSize(list));
    }
}
