// Exercise 5: Create and Use a Repeatable Annotation

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

class BuggyCode {
    @BugReport(description = "Null pointer issue in login")
    @BugReport(description = "Memory leak in data processing")
    public void problematicMethod() {
        System.out.println("Method with bugs");
    }
}

public class RepeatableAnnotation {
    public static void main(String[] args) throws Exception {
        Method method = BuggyCode.class.getMethod("problematicMethod");
        BugReport[] bugs = method.getAnnotationsByType(BugReport.class);
        
        System.out.println("Total Bugs: " + bugs.length);
        for (int i = 0; i < bugs.length; i++) {
            System.out.println("Bug " + (i+1) + ": " + bugs[i].description());
        }
    }
}
