// Exercise 2: Use @Deprecated to Mark an Old Method

class LegacyAPI {
    @Deprecated
    public void oldFeature() {
        System.out.println("This is the old feature - do not use!");
    }
    
    public void newFeature() {
        System.out.println("This is the new improved feature!");
    }
}

public class Deprecated {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        
        api.oldFeature();  // Compiler will show warning
        api.newFeature();  // Recommended to use
    }
}
