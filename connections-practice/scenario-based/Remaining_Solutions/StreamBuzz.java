import java.util.*;

class CreatorStats {
    private String creatorName;
    private double[] weeklyLikes;
    public static List<CreatorStats> engagementBoard = new ArrayList<>();
    
    public CreatorStats(String creatorName, double[] weeklyLikes) {
        this.creatorName = creatorName;
        this.weeklyLikes = weeklyLikes;
    }
    
    public String getCreatorName() {
        return creatorName;
    }
    
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    
    public double[] getWeeklyLikes() {
        return weeklyLikes;
    }
    
    public void setWeeklyLikes(double[] weeklyLikes) {
        this.weeklyLikes = weeklyLikes;
    }
}

public class StreamBuzz {
    
    public void registerCreator(CreatorStats record) {
        CreatorStats.engagementBoard.add(record);
    }
    
    public Map<String, Integer> getTopPostCounts(List<CreatorStats> records, double likeThreshold) {
        Map<String, Integer> result = new LinkedHashMap<>();
        
        for (CreatorStats creator : records) {
            int count = 0;
            for (double likes : creator.getWeeklyLikes()) {
                if (likes >= likeThreshold) {
                    count++;
                }
            }
            if (count > 0) {
                result.put(creator.getCreatorName(), count);
            }
        }
        
        return result;
    }
    
    public double calculateAverageLikes() {
        if (CreatorStats.engagementBoard.isEmpty()) {
            return 0.0;
        }
        
        double total = 0.0;
        int count = 0;
        
        for (CreatorStats creator : CreatorStats.engagementBoard) {
            for (double likes : creator.getWeeklyLikes()) {
                total += likes;
                count++;
            }
        }
        
        return total / count;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StreamBuzz app = new StreamBuzz();
        
        while (true) {
            System.out.println("1. Register Creator");
            System.out.println("2. Show Top Posts");
            System.out.println("3. Calculate Average Likes");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                System.out.println("Enter Creator Name:");
                String name = scanner.nextLine();
                
                System.out.println("Enter weekly likes (Week 1 to 4):");
                double[] likes = new double[4];
                for (int i = 0; i < 4; i++) {
                    likes[i] = scanner.nextDouble();
                }
                scanner.nextLine();
                
                CreatorStats creator = new CreatorStats(name, likes);
                app.registerCreator(creator);
                System.out.println("Creator registered successfully");
                
            } else if (choice == 2) {
                System.out.println("Enter like threshold:");
                double threshold = scanner.nextDouble();
                scanner.nextLine();
                
                Map<String, Integer> topPosts = app.getTopPostCounts(CreatorStats.engagementBoard, threshold);
                
                if (topPosts.isEmpty()) {
                    System.out.println("No top-performing posts this week");
                } else {
                    for (Map.Entry<String, Integer> entry : topPosts.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                    }
                }
                
            } else if (choice == 3) {
                double average = app.calculateAverageLikes();
                System.out.println("Overall average weekly likes: " + (int)average);
                
            } else if (choice == 4) {
                System.out.println("Logging off - Keep Creating with StreamBuzz!");
                break;
            }
        }
        
        scanner.close();
    }
}
