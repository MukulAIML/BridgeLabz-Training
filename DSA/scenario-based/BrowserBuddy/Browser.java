/**
 * Browser.java
 * Main controller class managing multiple tabs and closed tabs stack
 * Handles all browser operations and user interactions
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Browser {
    private ArrayList<Tab> openTabs;
    private ClosedTabStack closedTabs;
    private int currentTabIndex;

    public Browser() {
        this.openTabs = new ArrayList<>();
        this.closedTabs = new ClosedTabStack();
        this.currentTabIndex = -1;
        
        // Open initial tab with homepage
        Tab initialTab = new Tab("https://www.home.com", "Home Page");
        openTabs.add(initialTab);
        currentTabIndex = 0;
        System.out.println("Initial tab opened with Home Page");
    }

    /**
     * Open a new browser tab
     */
    public void openNewTab(Scanner scanner) {
        System.out.print("Enter URL: ");
        String url = scanner.nextLine().trim();
        System.out.print("Enter page title: ");
        String title = scanner.nextLine().trim();

        Tab newTab = new Tab(url, title);
        openTabs.add(newTab);
        currentTabIndex = openTabs.size() - 1;

        System.out.println("\n✓ New tab opened successfully!");
        System.out.println("Tab #" + newTab.getTabId() + " is now active");
    }

    /**
     * Visit a new page in the current tab
     */
    public void visitPage(Scanner scanner) {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open! Please open a new tab first.");
            return;
        }

        System.out.print("Enter URL to visit: ");
        String url = scanner.nextLine().trim();
        System.out.print("Enter page title: ");
        String title = scanner.nextLine().trim();

        Tab currentTab = openTabs.get(currentTabIndex);
        currentTab.visitPage(url, title);

        System.out.println("\n✓ Navigated to: " + title);
    }

    /**
     * Navigate back in current tab's history
     */
    public void goBack() {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        Tab currentTab = openTabs.get(currentTabIndex);
        if (currentTab.goBack()) {
            System.out.println("\n✓ Moved back to: " + currentTab.getCurrentPage());
        } else {
            System.out.println("\n✗ Cannot go back - already at the first page");
        }
    }

    /**
     * Navigate forward in current tab's history
     */
    public void goForward() {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        Tab currentTab = openTabs.get(currentTabIndex);
        if (currentTab.goForward()) {
            System.out.println("\n✓ Moved forward to: " + currentTab.getCurrentPage());
        } else {
            System.out.println("\n✗ Cannot go forward - already at the latest page");
        }
    }

    /**
     * Display current page information
     */
    public void displayCurrentPage() {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        Tab currentTab = openTabs.get(currentTabIndex);
        System.out.println("\n--- Current Page ---");
        System.out.println("Tab #" + currentTab.getTabId());
        System.out.println("Page: " + currentTab.getCurrentPage());
    }

    /**
     * Display browsing history of current tab
     */
    public void displayHistory() {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        Tab currentTab = openTabs.get(currentTabIndex);
        System.out.println("\n--- Tab #" + currentTab.getTabId() + " ---");
        currentTab.displayHistory();
    }

    /**
     * Close the current tab and add to closed tabs stack
     */
    public void closeCurrentTab() {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        Tab closedTab = openTabs.remove(currentTabIndex);
        closedTabs.push(closedTab);

        System.out.println("\n✓ Tab #" + closedTab.getTabId() + " closed");

        if (openTabs.isEmpty()) {
            currentTabIndex = -1;
            System.out.println("All tabs closed!");
        } else {
            if (currentTabIndex >= openTabs.size()) {
                currentTabIndex = openTabs.size() - 1;
            }
            System.out.println("Switched to Tab #" + openTabs.get(currentTabIndex).getTabId());
        }
    }

    /**
     * Reopen most recently closed tab from stack
     */
    public void reopenClosedTab() {
        if (closedTabs.isEmpty()) {
            System.out.println("\n✗ No closed tabs to reopen");
            return;
        }

        Tab reopenedTab = closedTabs.pop();
        openTabs.add(reopenedTab);
        currentTabIndex = openTabs.size() - 1;

        System.out.println("\n✓ Reopened Tab #" + reopenedTab.getTabId());
        System.out.println("Current page: " + reopenedTab.getCurrentPage());
    }

    /**
     * Switch to a different open tab
     */
    public void switchTab(Scanner scanner) {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        displayAllTabs();
        System.out.print("\nEnter tab number to switch to (1-" + openTabs.size() + "): ");
        
        try {
            int tabNumber = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (tabNumber < 1 || tabNumber > openTabs.size()) {
                System.out.println("Invalid tab number!");
                return;
            }

            currentTabIndex = tabNumber - 1;
            Tab currentTab = openTabs.get(currentTabIndex);
            System.out.println("\n✓ Switched to Tab #" + currentTab.getTabId());
            System.out.println("Current page: " + currentTab.getCurrentPage());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    /**
     * Display all open tabs
     */
    public void displayAllTabs() {
        if (openTabs.isEmpty()) {
            System.out.println("No tabs open!");
            return;
        }

        System.out.println("\n--- Open Tabs ---");
        for (int i = 0; i < openTabs.size(); i++) {
            Tab tab = openTabs.get(i);
            if (i == currentTabIndex) {
                System.out.println((i + 1) + ". " + tab + " [ACTIVE]");
            } else {
                System.out.println((i + 1) + ". " + tab);
            }
        }
        System.out.println("\nClosed tabs available: " + closedTabs.getSize());
    }
}
