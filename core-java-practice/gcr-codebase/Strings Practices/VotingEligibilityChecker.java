import java.util.Random;

public class VotingEligibilityChecker {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] ages = new int[10];

        System.out.println("Age\tEligible to Vote");
        for (int i = 0; i < ages.length; i++) {
            ages[i] = rand.nextInt(91) + 10;
            boolean canVote = ages[i] >= 18;
            System.out.println(ages[i] + "\t" + canVote);
        }
    }
}
