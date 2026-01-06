class Animal {

    // Method Overriding will happen for this method
    void sound() {
        System.out.println("Animal makes sound");
    }

    // Method Overloading (same class)
    void eat() {
        System.out.println("Animal eats food");
    }

    void eat(String food) {
        System.out.println("Animal eats " + food);
    }
}

class Dog extends Animal {

    // Method Overriding
    void sound() {
        System.out.println("Dog barks");
    }

    public static void main(String[] args) {
        Dog d = new Dog();

        // Overloading
        d.eat();
        d.eat("Bones");

        // Overriding
        Animal a = new Dog();
        a.sound();
    }
}
