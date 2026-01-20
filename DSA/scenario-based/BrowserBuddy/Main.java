/**
 * Main.java
 * Entry point for BrowserBuddy application
 * Provides menu-driven interface for all browser operations
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Browser browser = new Browser();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to BrowserBuddy ===");
        System.out.println("Your custom tab history manager\n");

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        browser.openNewTab(scanner);
                        break;
                    case 2:
                        browser.visitPage(scanner);
                        break;
                    case 3:
                        browser.goBack();
                        break;
                    case 4:
                        browser.goForward();
                        break;
                    case 5:
                        browser.displayCurrentPage();
                        break;
                    case 6:
                        browser.displayHistory();
                        break;
                    case 7:
                        browser.closeCurrentTab();
                        break;
                    case 8:
                        browser.reopenClosedTab();
                        break;
                    case 9:
                        browser.switchTab(scanner);
                        break;
                    case 10:
                        browser.displayAllTabs();
                        break;
                    case 11:
                        running = false;
                        System.out.println("\nThank you for using BrowserBuddy!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Display the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("               BROWSER MENU");
        System.out.println("=".repeat(50));
        System.out.println("1.  Open New Tab");
        System.out.println("2.  Visit Page");
        System.out.println("3.  Go Back");
        System.out.println("4.  Go Forward");
        System.out.println("5.  Display Current Page");
        System.out.println("6.  Display History");
        System.out.println("7.  Close Current Tab");
        System.out.println("8.  Reopen Closed Tab");
        System.out.println("9.  Switch Tab");
        System.out.println("10. Display All Tabs");
        System.out.println("11. Exit");
        System.out.println("=".repeat(50));
    }
}
