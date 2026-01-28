// Data Export Feature with Default Method
import java.util.Scanner;

interface DataExporter {
    void exportToCSV(String data);
    void exportToPDF(String data);
    
    default void exportToJSON(String data) {
        System.out.println("Exporting to JSON format:");
        System.out.println("{\"data\": \"" + data + "\"}");
    }
}

class ReportExporter implements DataExporter {
    public void exportToCSV(String data) {
        System.out.println("CSV Export:");
        System.out.println("Data," + data);
    }
    
    public void exportToPDF(String data) {
        System.out.println("PDF Export:");
        System.out.println("[PDF Document] " + data);
    }
}

class AnalyticsExporter implements DataExporter {
    public void exportToCSV(String data) {
        System.out.println("Analytics CSV:");
        System.out.println("Column1,Column2");
        System.out.println(data + ",Analytics Data");
    }
    
    public void exportToPDF(String data) {
        System.out.println("Analytics PDF:");
        System.out.println("[Formatted PDF] " + data);
    }
    
    @Override
    public void exportToJSON(String data) {
        System.out.println("Custom JSON Export:");
        System.out.println("{\"analytics\": {\"value\": \"" + data + "\"}}");
    }
}

public class DataExportFeature {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter data to export: ");
        String data = scanner.nextLine();
        
        DataExporter reportExporter = new ReportExporter();
        DataExporter analyticsExporter = new AnalyticsExporter();
        
        System.out.println("\n--- Report Exporter ---");
        reportExporter.exportToCSV(data);
        System.out.println();
        reportExporter.exportToPDF(data);
        System.out.println();
        reportExporter.exportToJSON(data);
        
        System.out.println("\n--- Analytics Exporter ---");
        analyticsExporter.exportToCSV(data);
        System.out.println();
        analyticsExporter.exportToPDF(data);
        System.out.println();
        analyticsExporter.exportToJSON(data);
        
        scanner.close();
    }
}
