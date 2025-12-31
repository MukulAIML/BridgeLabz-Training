import java.util.Scanner;

class ProductPrices {
    private double[] prices;

    public ProductPrices(int size) {
        prices = new double[size];
    }

    public void inputPrices() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + prices.length + " product prices:");
        for (int i = 0; i < prices.length; i++) {
            prices[i] = sc.nextDouble();
        }
    }

    public void sortPrices() {
        quickSort(0, prices.length - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = prices[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (prices[j] < pivot) {
                i++;
                double temp = prices[i];
                prices[i] = prices[j];
                prices[j] = temp;
            }
        }
        double temp = prices[i + 1];
        prices[i + 1] = prices[high];
        prices[high] = temp;
        return i + 1;
    }

    public void displayPrices() {
        System.out.println("Sorted Product Prices:");
        for (double price : prices) {
            System.out.print(price + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of products: ");
        int size = sc.nextInt();

        ProductPrices product = new ProductPrices(size);
        product.inputPrices();
        product.sortPrices();
        product.displayPrices();
    }
}
