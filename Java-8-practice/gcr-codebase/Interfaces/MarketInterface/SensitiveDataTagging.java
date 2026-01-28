// Sensitive Data Tagging with Custom Marker Interface
import java.util.Scanner;

interface SensitiveData {
    // Custom marker interface for sensitive data
}

class CreditCardInfo implements SensitiveData {
    private String cardNumber;
    private String cvv;
    
    public CreditCardInfo(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }
    
    public String toString() {
        return "Credit Card: " + maskCardNumber(cardNumber);
    }
    
    private String maskCardNumber(String number) {
        return "XXXX-XXXX-XXXX-" + number.substring(number.length() - 4);
    }
}

class PersonalInfo implements SensitiveData {
    private String aadharNumber;
    private String panNumber;
    
    public PersonalInfo(String aadharNumber, String panNumber) {
        this.aadharNumber = aadharNumber;
        this.panNumber = panNumber;
    }
    
    public String toString() {
        return "Aadhar: XXXX-XXXX-" + aadharNumber.substring(aadharNumber.length() - 4) +
               ", PAN: " + panNumber;
    }
}

class PublicInfo {
    private String name;
    private String city;
    
    public PublicInfo(String name, String city) {
        this.name = name;
        this.city = city;
    }
    
    public String toString() {
        return "Name: " + name + ", City: " + city;
    }
}

class DataProcessor {
    public static void processData(Object data) {
        if (data instanceof SensitiveData) {
            System.out.println("[ENCRYPTED] " + encrypt(data.toString()));
        } else {
            System.out.println("[PLAIN] " + data);
        }
    }
    
    private static String encrypt(String data) {
        return "***ENCRYPTED_DATA***";
    }
}

public class SensitiveDataTagging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter card number (16 digits): ");
        String cardNumber = scanner.nextLine();
        
        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        
        System.out.print("Enter Aadhar number (12 digits): ");
        String aadhar = scanner.nextLine();
        
        System.out.print("Enter PAN number: ");
        String pan = scanner.nextLine();
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        
        CreditCardInfo cardInfo = new CreditCardInfo(cardNumber, cvv);
        PersonalInfo personalInfo = new PersonalInfo(aadhar, pan);
        PublicInfo publicInfo = new PublicInfo(name, city);
        
        System.out.println("\nProcessing data:");
        DataProcessor.processData(cardInfo);
        DataProcessor.processData(personalInfo);
        DataProcessor.processData(publicInfo);
        
        scanner.close();
    }
}
