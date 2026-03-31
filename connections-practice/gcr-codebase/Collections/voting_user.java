import java.util.*;

public class VotingSystem {
    private Map<String, Integer> votes = new HashMap<>();
    private LinkedHashMap<String, Integer> voteOrder = new LinkedHashMap<>();

    public void castVote(String candidate) {
        votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
        voteOrder.put(candidate, voteOrder.getOrDefault(candidate, 0) + 1);
    }

    public void displayResults() {
        System.out.println("\n=== Voting Results ===");
        System.out.println("\nIn voting order:");
        for (Map.Entry<String, Integer> entry : voteOrder.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
        
        System.out.println("\nSorted by votes (descending):");
        TreeMap<Integer, List<String>> sortedResults = new TreeMap<>(Collections.reverseOrder());
        
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            int voteCount = entry.getValue();
            if (!sortedResults.containsKey(voteCount)) {
                sortedResults.put(voteCount, new ArrayList<>());
            }
            sortedResults.get(voteCount).add(entry.getKey());
        }
        
        for (Map.Entry<Integer, List<String>> entry : sortedResults.entrySet()) {
            for (String candidate : entry.getValue()) {
                System.out.println(candidate + ": " + entry.getKey() + " votes");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VotingSystem system = new VotingSystem();
        
        System.out.print("Enter number of votes to cast: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Cast " + n + " votes:");
        for (int i = 0; i < n; i++) {
            System.out.print("Vote " + (i + 1) + " - Candidate name: ");
            String candidate = sc.nextLine();
            system.castVote(candidate);
        }
        
        system.displayResults();
        
        sc.close();
    }
}