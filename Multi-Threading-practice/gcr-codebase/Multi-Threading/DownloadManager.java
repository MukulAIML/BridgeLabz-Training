import java.util.Scanner;
import java.util.Random;

class FileDownloader extends Thread {
    private String fileName;
    private Random random = new Random();
    
    public FileDownloader(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting download: " + fileName);
        
        for (int progress = 0; progress <= 100; progress += 10) {
            System.out.println("[" + Thread.currentThread().getName() + "] Downloading " + fileName + ": " + progress + "%");
            try {
                Thread.sleep(random.nextInt(300) + 200); // Random delay between 200-500ms
            } catch (InterruptedException e) {
                System.out.println("Download interrupted: " + fileName);
            }
        }
        
        System.out.println("[" + Thread.currentThread().getName() + "] Download complete: " + fileName);
    }
}

public class DownloadManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Download Manager ===");
        System.out.print("Enter number of files to download: ");
        int numFiles = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        FileDownloader[] downloaders = new FileDownloader[numFiles];
        
        for (int i = 0; i < numFiles; i++) {
            System.out.print("Enter file name " + (i + 1) + ": ");
            String fileName = scanner.nextLine();
            downloaders[i] = new FileDownloader(fileName);
            downloaders[i].setName("Thread-" + (i + 1));
        }
        
        // Start all download threads
        for (FileDownloader downloader : downloaders) {
            downloader.start();
        }
        
        // Wait for all downloads to complete
        try {
            for (FileDownloader downloader : downloaders) {
                downloader.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        System.out.println("\n✓ All downloads complete!");
        scanner.close();
    }
}
