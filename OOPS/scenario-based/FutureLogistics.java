import java.util.Scanner;

abstract class GoodsTransport {
    protected String transportId, transportDate;
    protected int transportRating;
    
    public GoodsTransport(String transportId, String transportDate, int transportRating) {
        this.transportId = transportId;
        this.transportDate = transportDate;
        this.transportRating = transportRating;
    }
    
    public String getTransportId() { return transportId; }
    public void setTransportId(String transportId) { this.transportId = transportId; }
    public String getTransportDate() { return transportDate; }
    public void setTransportDate(String transportDate) { this.transportDate = transportDate; }
    public int getTransportRating() { return transportRating; }
    public void setTransportRating(int transportRating) { this.transportRating = transportRating; }
    
    abstract public String vehicleSelection();
    abstract public float calculateTotalCharge();
}

class BrickTransport extends GoodsTransport {
    private float brickSize, brickPrice;
    private int brickQuantity;
    
    public BrickTransport(String transportId, String transportDate, int transportRating, 
                         float brickSize, int brickQuantity, float brickPrice) {
        super(transportId, transportDate, transportRating);
        this.brickSize = brickSize;
        this.brickQuantity = brickQuantity;
        this.brickPrice = brickPrice;
    }
    
    public float getBrickSize() { return brickSize; }
    public void setBrickSize(float brickSize) { this.brickSize = brickSize; }
    public int getBrickQuantity() { return brickQuantity; }
    public void setBrickQuantity(int brickQuantity) { this.brickQuantity = brickQuantity; }
    public float getBrickPrice() { return brickPrice; }
    public void setBrickPrice(float brickPrice) { this.brickPrice = brickPrice; }
    
    public String vehicleSelection() {
        return brickQuantity < 300 ? "Truck" : brickQuantity <= 500 ? "Lorry" : "MonsterLorry";
    }
    
    public float calculateTotalCharge() {
        float price = brickPrice * brickQuantity;
        float vehiclePrice = vehicleSelection().equals("Truck") ? 1000 : vehicleSelection().equals("Lorry") ? 1700 : 3000;
        float discount = price * (transportRating == 5 ? 20 : (transportRating >= 3 ? 10 : 0)) / 100;
        return price + vehiclePrice + price * 0.3f - discount;
    }
}

class TimberTransport extends GoodsTransport {
    private float timberLength, timberRadius, timberPrice;
    private String timberType;
    
    public TimberTransport(String transportId, String transportDate, int transportRating,
                          float timberLength, float timberRadius, String timberType, float timberPrice) {
        super(transportId, transportDate, transportRating);
        this.timberLength = timberLength;
        this.timberRadius = timberRadius;
        this.timberType = timberType;
        this.timberPrice = timberPrice;
    }
    
    public float getTimberLength() { return timberLength; }
    public void setTimberLength(float timberLength) { this.timberLength = timberLength; }
    public float getTimberRadius() { return timberRadius; }
    public void setTimberRadius(float timberRadius) { this.timberRadius = timberRadius; }
    public String getTimberType() { return timberType; }
    public void setTimberType(String timberType) { this.timberType = timberType; }
    public float getTimberPrice() { return timberPrice; }
    public void setTimberPrice(float timberPrice) { this.timberPrice = timberPrice; }
    
    public String vehicleSelection() {
        float area = 2 * 3.147f * timberRadius * timberLength;
        return area < 250 ? "Truck" : area <= 400 ? "Lorry" : "MonsterLorry";
    }
    
    public float calculateTotalCharge() {
        float volume = 3.147f * timberRadius * timberRadius * timberLength;
        float price = volume * timberPrice * (timberType.equalsIgnoreCase("Premium") ? 0.25f : 0.15f);
        float vehiclePrice = vehicleSelection().equals("Truck") ? 1000 : vehicleSelection().equals("Lorry") ? 1700 : 3000;
        float discount = price * (transportRating == 5 ? 20 : (transportRating >= 3 ? 10 : 0)) / 100;
        return price + vehiclePrice + price * 0.3f - discount;
    }
}

class Utility {
    public boolean validate(String transportId) {
        if (transportId == null || transportId.length() != 7) {
            System.out.println("Transport id " + transportId + " is invalid\nPlease provide a valid record");
            return false;
        }
        for (int i = 0; i < 3; i++) if (!Character.isUpperCase(transportId.charAt(i))) {
            System.out.println("Transport id " + transportId + " is invalid\nPlease provide a valid record");
            return false;
        }
        for (int i = 3; i < 6; i++) if (!Character.isDigit(transportId.charAt(i))) {
            System.out.println("Transport id " + transportId + " is invalid\nPlease provide a valid record");
            return false;
        }
        if (!Character.isUpperCase(transportId.charAt(6))) {
            System.out.println("Transport id " + transportId + " is invalid\nPlease provide a valid record");
            return false;
        }
        return true;
    }
    
    public GoodsTransport parseDetails(String details) {
        String[] p = details.split(":");
        if (!validate(p[0])) return null;
        
        if (p[3].equalsIgnoreCase("BrickTransport"))
            return new BrickTransport(p[0], p[1], Integer.parseInt(p[2]), 
                Float.parseFloat(p[4]), Integer.parseInt(p[5]), Float.parseFloat(p[6]));
        else if (p[3].equalsIgnoreCase("TimberTransport"))
            return new TimberTransport(p[0], p[1], Integer.parseInt(p[2]), 
                Float.parseFloat(p[4]), Float.parseFloat(p[5]), p[6], Float.parseFloat(p[7]));
        return null;
    }
    
    public String findObjectType(GoodsTransport g) {
        return g instanceof TimberTransport ? "TimberTransport" : g instanceof BrickTransport ? "BrickTransport" : null;
    }
}

public class FutureLogistics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Goods Transport details");
        GoodsTransport t = new Utility().parseDetails(sc.nextLine());
        
        if (t != null) {
            System.out.println("Transporter id : " + t.getTransportId());
            System.out.println("Date of transport : " + t.getTransportDate());
            System.out.println("Rating of the transport : " + t.getTransportRating());
            
            if (t instanceof BrickTransport) {
                BrickTransport bt = (BrickTransport) t;
                System.out.println("Quantity of bricks : " + bt.getBrickQuantity());
                System.out.println("Brick price : " + bt.getBrickPrice());
                System.out.println("Vehicle for transport : " + bt.vehicleSelection());
                System.out.println("Total charge : " + bt.calculateTotalCharge());
            } else if (t instanceof TimberTransport) {
                TimberTransport tt = (TimberTransport) t;
                System.out.println("Type of the timber : " + tt.getTimberType());
                System.out.println("Timber price per kilo : " + tt.getTimberPrice());
                System.out.println("Vehicle for transport : " + tt.vehicleSelection());
                System.out.println("Total charge : " + tt.calculateTotalCharge());
            }
        }
        sc.close();
    }
}
