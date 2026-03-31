/**
 * Tab.java
 * Represents a browser tab with its own browsing history
 * Each tab has a unique ID and maintains its navigation state
 */

public class Tab {
    private static int tabCounter = 0;
    private int tabId;
    private BrowsingHistory history;

    public Tab() {
        this.tabId = ++tabCounter;
        this.history = new BrowsingHistory();
    }

    public Tab(String url, String title) {
        this.tabId = ++tabCounter;
        this.history = new BrowsingHistory(url, title);
    }

    public int getTabId() {
        return tabId;
    }

    public BrowsingHistory getHistory() {
        return history;
    }

    public void visitPage(String url, String title) {
        history.visitPage(url, title);
    }

    public boolean goBack() {
        return history.goBack();
    }

    public boolean goForward() {
        return history.goForward();
    }

    public String getCurrentPage() {
        return history.getCurrentPage();
    }

    public void displayHistory() {
        history.displayHistory();
    }

    @Override
    public String toString() {
        return "Tab #" + tabId + ": " + history.getCurrentPage();
    }
}
