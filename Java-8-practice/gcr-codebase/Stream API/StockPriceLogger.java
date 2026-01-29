import java.util.*;

class Stock {
    private String symbol;
    private double price;
    
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return symbol + ": $" + String.format("%.2f", price);
    }
}

public class StockPriceLogger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of stocks: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        List<Stock> stocks = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nStock " + (i + 1) + ":");
            System.out.print("Enter symbol: ");
            String symbol = sc.nextLine();
            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            
            stocks.add(new Stock(symbol, price));
        }
        
        System.out.println("\n=== Live Stock Price Feed ===");
        stocks.forEach(stock -> System.out.println("Update: " + stock));
        
        sc.close();
    }
}
