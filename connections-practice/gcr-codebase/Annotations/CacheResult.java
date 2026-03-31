// Problem 7: Implement a Custom Caching System with @CacheResult

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {}

class ExpensiveCalculator {
    @CacheResult
    public int fibonacci(int n) {
        System.out.println("Computing fibonacci(" + n + ")...");
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    @CacheResult
    public long factorial(int n) {
        System.out.println("Computing factorial(" + n + ")...");
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

public class CacheResult {
    static Map<String, Object> cache = new HashMap<>();
    
    public static void main(String[] args) throws Exception {
        ExpensiveCalculator calc = new ExpensiveCalculator();
        
        System.out.println("=== First Call ===");
        System.out.println("Result: " + callWithCache(calc, "fibonacci", 10));
        
        System.out.println("\n=== Second Call (Cached) ===");
        System.out.println("Result: " + callWithCache(calc, "fibonacci", 10));
        
        System.out.println("\n=== Factorial Test ===");
        System.out.println("Result: " + callWithCache(calc, "factorial", 5));
        System.out.println("Result: " + callWithCache(calc, "factorial", 5));
    }
    
    static Object callWithCache(Object obj, String methodName, int param) throws Exception {
        Method method = obj.getClass().getMethod(methodName, int.class);
        String cacheKey = methodName + ":" + param;
        
        if (method.isAnnotationPresent(CacheResult.class)) {
            if (cache.containsKey(cacheKey)) {
                System.out.println("Returning cached result!");
                return cache.get(cacheKey);
            }
            
            Object result = method.invoke(obj, param);
            cache.put(cacheKey, result);
            return result;
        }
        
        return method.invoke(obj, param);
    }
}
