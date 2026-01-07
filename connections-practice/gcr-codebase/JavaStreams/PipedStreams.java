// ========================================
// PipedStreams.java
// Piped Streams - Inter-Thread Communication
// ========================================

import java.io.*;
import java.util.Scanner;

class WriterThread extends Thread {
    private PipedOutputStream pos;
    private String message;
    
    public WriterThread(PipedOutputStream pos, String message) {
        this.pos = pos;
        this.message = message;
    }
    
    public void run() {
        DataOutputStream dos = new DataOutputStream(pos);
        
        try {
            System.out.println("Writer Thread: Writing data...");
            dos.writeUTF(message);
            dos.flush();
            System.out.println("Writer Thread: Data written successfully!");
            
        } catch (IOException e) {
            System.out.println("Writer Thread Error: " + e.getMessage());
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReaderThread extends Thread {
    private PipedInputStream pis;
    
    public ReaderThread(PipedInputStream pis) {
        this.pis = pis;
    }
    
    public void run() {
        DataInputStream dis = new DataInputStream(pis);
        
        try {
            System.out.println("Reader Thread: Waiting for data...");
            String receivedMessage = dis.readUTF();
            System.out.println("Reader Thread: Received data: " + receivedMessage);
            
        } catch (IOException e) {
            System.out.println("Reader Thread Error: " + e.getMessage());
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class PipedStreams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter message to send between threads: ");
        String message = sc.nextLine();
        
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);
            
            WriterThread writerThread = new WriterThread(pos, message);
            ReaderThread readerThread = new ReaderThread(pis);
            
            System.out.println("\n=== Starting Inter-Thread Communication ===\n");
            
            readerThread.start();
            Thread.sleep(100); // Small delay to ensure reader is ready
            writerThread.start();
            
            writerThread.join();
            readerThread.join();
            
            System.out.println("\n=== Communication Complete ===");
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        sc.close();
    }
}