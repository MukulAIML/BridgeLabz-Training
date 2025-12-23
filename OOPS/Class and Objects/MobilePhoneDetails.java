public class MobilePhoneDetails {
    String brand;
    String model;
    double price;

    // Constructor to initialize phone details
    public MobilePhoneDetails(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    // Method to display phone details
    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Price: " + price);
    }

    // Main method
    public static void main(String[] args) {
        MobilePhoneDetails phone = new MobilePhoneDetails("Samsung", "Galaxy S21", 69999);
        phone.displayDetails();
    }
}