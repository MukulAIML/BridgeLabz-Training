import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class IPAddressExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== IP Address Extractor ===");
        System.out.print("Enter text containing IP addresses: ");
        String text = sc.nextLine();
        
        // Pattern: IPv4 addresses
        String regex = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        ArrayList<String> ips = new ArrayList<>();
        while (matcher.find()) {
            ips.add(matcher.group());
        }
        
        System.out.println("\nExtracted IP Addresses:");
        if (ips.isEmpty()) {
            System.out.println("No IP addresses found!");
        } else {
            for (String ip : ips) {
                System.out.println(ip);
            }
        }
        
        sc.close();
    }
}