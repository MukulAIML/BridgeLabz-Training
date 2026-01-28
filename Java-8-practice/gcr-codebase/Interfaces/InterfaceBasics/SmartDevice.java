// Smart Device Control Interface
interface SmartDevice {
    void turnOn();
    void turnOff();
}

class SmartLight implements SmartDevice {
    private String location;
    
    public SmartLight(String location) {
        this.location = location;
    }
    
    public void turnOn() {
        System.out.println(location + " Light is now ON");
    }
    
    public void turnOff() {
        System.out.println(location + " Light is now OFF");
    }
}

class SmartAC implements SmartDevice {
    private int temperature;
    
    public SmartAC(int temperature) {
        this.temperature = temperature;
    }
    
    public void turnOn() {
        System.out.println("AC is now ON at " + temperature + "°C");
    }
    
    public void turnOff() {
        System.out.println("AC is now OFF");
    }
}

class SmartTV implements SmartDevice {
    private String brand;
    
    public SmartTV(String brand) {
        this.brand = brand;
    }
    
    public void turnOn() {
        System.out.println(brand + " TV is now ON");
    }
    
    public void turnOff() {
        System.out.println(brand + " TV is now OFF");
    }
}

public class SmartDeviceDemo {
    public static void main(String[] args) {
        SmartDevice light = new SmartLight("Living Room");
        SmartDevice ac = new SmartAC(24);
        SmartDevice tv = new SmartTV("Samsung");
        
        light.turnOn();
        ac.turnOn();
        tv.turnOn();
        
        System.out.println();
        
        light.turnOff();
        ac.turnOff();
        tv.turnOff();
    }
}
