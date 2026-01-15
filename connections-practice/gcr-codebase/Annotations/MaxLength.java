// Problem 4: Create a @MaxLength Annotation for Field Validation

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class User {
    @MaxLength(10)
    private String username;
    
    public User(String username) throws IllegalArgumentException {
        validateFields();
        if (username.length() > 10) {
            throw new IllegalArgumentException("Username exceeds max length of 10 characters");
        }
        this.username = username;
    }
    
    private void validateFields() throws IllegalArgumentException {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(MaxLength.class)) {
                MaxLength annotation = f.getAnnotation(MaxLength.class);
                System.out.println("Field '" + f.getName() + "' has max length: " + annotation.value());
            }
        }
    }
    
    public String getUsername() {
        return username;
    }
}

public class MaxLength {
    public static void main(String[] args) {
        try {
            User user1 = new User("JohnDoe");
            System.out.println("User created: " + user1.getUsername() + "\n");
            
            User user2 = new User("ThisUsernameIsTooLong");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
