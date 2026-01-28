// Password Strength Validator with Static Interface Method
import java.util.Scanner;

interface SecurityUtils {
    static boolean isStrongPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    
    static String getPasswordStrength(String password) {
        if (isStrongPassword(password)) {
            return "Strong";
        } else if (password.length() >= 6) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter password to validate: ");
        String password = scanner.nextLine();
        
        if (SecurityUtils.isStrongPassword(password)) {
            System.out.println("Password is STRONG ✓");
        } else {
            System.out.println("Password is WEAK ✗");
            System.out.println("Requirements:");
            System.out.println("- Minimum 8 characters");
            System.out.println("- At least one uppercase letter");
            System.out.println("- At least one lowercase letter");
            System.out.println("- At least one digit");
            System.out.println("- At least one special character");
        }
        
        System.out.println("Strength: " + SecurityUtils.getPasswordStrength(password));
        
        scanner.close();
    }
}
