public class CircleAreaCalculator {
    double radius;

    // Constructor to initialize radius
    public CircleAreaCalculator(double radius) {
        this.radius = radius;
    }

    // Method to calculate area of circle
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Method to calculate circumference
    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }

    // Method to display circle metrics
    public void display() {
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + calculateArea());
        System.out.println("Circumference: " + calculateCircumference());
    }

    // Main method
    public static void main(String[] args) {
        CircleAreaCalculator circle = new CircleAreaCalculator(5);
        circle.display();
    }
}