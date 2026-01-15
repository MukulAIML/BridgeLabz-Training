// Problem 6: Implement a Custom Serialization Annotation @JsonField

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class User {
    @JsonField(name = "user_name")
    private String username;
    
    @JsonField(name = "user_email")
    private String email;
    
    @JsonField(name = "user_age")
    private int age;
    
    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}

public class JsonField {
    public static void main(String[] args) throws Exception {
        User user = new User("Alice", "alice@example.com", 25);
        String json = toJson(user);
        System.out.println(json);
    }
    
    static String toJson(Object obj) throws Exception {
        StringBuilder json = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean first = true;
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                if (!first) json.append(", ");
                
                JsonField annotation = field.getAnnotation(JsonField.class);
                field.setAccessible(true);
                Object value = field.get(obj);
                
                json.append("\"").append(annotation.name()).append("\": ");
                
                if (value instanceof String) {
                    json.append("\"").append(value).append("\"");
                } else {
                    json.append(value);
                }
                
                first = false;
            }
        }
        
        json.append("}");
        return json.toString();
    }
}
