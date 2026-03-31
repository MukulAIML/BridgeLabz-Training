// Exercise 4: Create a Custom Annotation and Use It

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority();
    String assignedTo();
}

class TaskManager {
    @TaskInfo(priority = "HIGH", assignedTo = "John")
    public void completeTask() {
        System.out.println("Task is being executed");
    }
}

public class CustomAnnotation {
    public static void main(String[] args) throws Exception {
        TaskManager tm = new TaskManager();
        Method method = tm.getClass().getMethod("completeTask");
        
        if (method.isAnnotationPresent(TaskInfo.class)) {
            TaskInfo info = method.getAnnotation(TaskInfo.class);
            System.out.println("Priority: " + info.priority());
            System.out.println("Assigned To: " + info.assignedTo());
        }
    }
}
