public class ExceptionPropagation {
    static void method1() {
        System.out.println("Inside method1");
        int result = 10 / 0; // This throws ArithmeticException
    }
    
    static void method2() {
        System.out.println("Inside method2");
        method1(); // Exception propagates from method1
    }
    
    public static void main(String[] args) {
        System.out.println("Inside main");
        try {
            method2(); // Exception propagates from method2
        } catch (ArithmeticException e) {
            System.out.println("Handled exception in main");
        }
    }
}